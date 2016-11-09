package aroen.com.studiopushtest;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView list;
    private DataAdapter mDataAdapter;
    private ArrayList<Student> dataList = new ArrayList<>();
    private RelativeLayout top_root;
    private SampleHeader header;
    private EditText location, search;
    private float end, end2;
    private float start;
    private boolean isfirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initStatu();
        initDatas();
        initViews();
    }

    private void initDatas() {
        for (int i = 0; i < 20; i++) {
            Student s = new Student();
            s.setAge((i + 1) + "");
            s.setName("Aroen");
            s.setSex("男");
            dataList.add(s);
        }
    }

    private void initViews() {
        list = (ListView) findViewById(R.id.list);
        top_root = (RelativeLayout) findViewById(R.id.top_root);
        location = (EditText) findViewById(R.id.location);
        search = (EditText) findViewById(R.id.search);
        int screenWith = getScreenWith();
        start = screenWith - dip2px(MainActivity.this, 10) - dip2px(MainActivity.this, 40);
        end = dip2px(MainActivity.this, 10);
        end2 = screenWith - dip2px(MainActivity.this, 10) - dip2px(MainActivity.this, 40);
        Log.i("tag", "========" + start + " end=" + end + "end2=" + end2 + "width" + search.getWidth());
        mDataAdapter = new DataAdapter(MainActivity.this, dataList);
        list.setAdapter(mDataAdapter);
        header = new SampleHeader(this);
        list.addHeaderView(header);
        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://空闲状态\\
                        Log.i("header_botttom", "==aroen" + header.getBottom());
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING://滚动状态
                        Log.i("header_botttom", "==aroen" + header.getBottom());
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://触摸后滚动

                        break;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//                Log.i("onScroll", "aroen" + "i= " + i + " i1= " + i1 + " i2= " + i2);

                Log.i("header_botttom", "");
                top_root.setAlpha((1 - ((header.getBottom() - top_root.getHeight()) / (float) (header.getHeight() - top_root.getHeight()))));

                if (top_root.getAlpha() >= 0.8) {
                    location.setVisibility(View.GONE);
                    if (!isfirst) {
                        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
                        anim.setDuration(300);
                        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                float currentValue = (float) animation.getAnimatedValue();
                                Log.d("TAG", "open " + currentValue);
                                search.setLeft((int) currentValue);
                            }
                        });
                        ObjectAnimator.ofFloat(search, "scaleY", 1, 1.5f).setDuration(300).start();
//                        ObjectAnimator.ofInt(search, "backgroundColor", drawable.getb,drawable2).setDuration(300).start();
                        search.setBackgroundResource(R.drawable.ed_bg_write);
                        search.setTextColor(Color.parseColor("#000000"));
                        search.setHintTextColor(Color.parseColor("#000000"));
                        anim.start();
                        isfirst = true;
                        if (anim.isRunning()) {
                            location.setVisibility(View.GONE);
                        }
                    }
                } else {
//                    location.setVisibility(View.VISIBLE);
                    if (isfirst) {
                        final ValueAnimator anim = ValueAnimator.ofFloat(end, end2);
                        anim.setDuration(300);
                        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                float currentValue = (float) animation.getAnimatedValue();
                                float animatedFraction = animation.getAnimatedFraction();
                                Log.d("TAG", "close " + currentValue);
                                search.setLeft((int) currentValue);
                            }
                        });
                        ObjectAnimator.ofFloat(search, "scaleY", 1.5f, 1).setDuration(300).start();
                        search.setBackgroundResource(R.drawable.ed_bg);
                        search.setTextColor(Color.parseColor("#ffffff"));
                        search.setHintTextColor(Color.parseColor("#ffffff"));
                        anim.start();
                        isfirst = false;
                        anim.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                location.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        });
                    }
                }
            }

        });
    }

    private int getScreenWith() {
        WindowManager wm = MainActivity.this.getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        return width;
    }

    private void initStatu() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags =
                    (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                            | localLayoutParams.flags);
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
