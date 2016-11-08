package aroen.com.studiopushtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhanghongyu on 16/11/8.
 */

public class DataAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<Student> list = new ArrayList<>();
    private Context mContext;
    private ViewHolder holder;

    public DataAdapter(Context context, List<Student> list) {
        layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.list=list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.sex = (TextView) convertView.findViewById(R.id.sex);
            holder.age = (TextView) convertView.findViewById(R.id.age);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(list.get(position).getName());
        holder.age.setText(list.get(position).getAge());
        holder.sex.setText(list.get(position).getSex());
        return convertView;
    }
    private class ViewHolder{
        TextView name,sex,age;
    }
}


