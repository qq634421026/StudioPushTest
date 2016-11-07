package aroen.com.studiopushtest.ui;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by zhanghongyu on 16/11/7.
 */

public class ByeBurgerBottomBehavior extends CoordinatorLayout.Behavior<View> {
    private final int mTouchSlop;
    private boolean isFirstMove = true;
    private AnimateHelper mAnimateHelper;

    public ByeBurgerBottomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
                                  int dx, int dy, int[] consumed) {

        if (isFirstMove) {
            isFirstMove = false;
            mAnimateHelper = AnimateHelper.get(child);
            mAnimateHelper.setStartY(child.getY());
            mAnimateHelper.setMode(AnimateHelper.MODE_BOTTOM);
        }
        if (Math.abs(dy) > mTouchSlop) {
            if (dy < 0) {

                if (mAnimateHelper.getState() == AnimateHelper.STATE_HIDE) {
                    mAnimateHelper.show();
                }
            } else if (dy > 0) {
                if (mAnimateHelper.getState() == AnimateHelper.STATE_SHOW) {
                    mAnimateHelper.hide();
                }
            }
        }
    }
}