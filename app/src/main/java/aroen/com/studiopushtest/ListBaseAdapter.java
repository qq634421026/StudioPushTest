package aroen.com.studiopushtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zhanghongyu on 16/11/8.
 */

public class ListBaseAdapter<T extends Object> extends RecyclerView.Adapter {
    protected Context mContext;
    protected int mScreenWidth;

    public void setmScreenWidth(int width) {
        this.mScreenWidth = width;
    }
    protected ArrayList<T> mDataList = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public List<T> getmDataList() {
        return mDataList;
    }

    public void setmDataList(Collection<T> list) {
        this.mDataList.clear();
        this.mDataList.addAll(list);
        notifyDataSetChanged();

    }
    public void addAll(Collection<T> list){
        int lastIndex = this.mDataList.size();
        if (this.mDataList.addAll(list)) {
            notifyItemRangeInserted(lastIndex,list.size());
        }
    }
    public void clear(){
        mDataList.clear();
        notifyDataSetChanged();
    }
}
