package projects.nyinyihtunlwin.talks.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import projects.nyinyihtunlwin.talks.viewholders.BaseViewHolder;

/**
 * Created by Dell on 1/23/2018.
 */

public abstract class BaseAdapter<T extends BaseViewHolder, D> extends RecyclerView.Adapter<T> {

    protected List<D> mData;
    protected LayoutInflater mLayoutInflater;

    public BaseAdapter(Context context) {
        mData = new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setNewData(List<D> newData) {
        mData = newData;
        notifyDataSetChanged();
    }

    public void appendNewData(List<D> newData) {
        mData.addAll(newData);
        notifyDataSetChanged();
    }

    public D getItemAt(int position) {
        if (position < mData.size() - 1)
            return mData.get(position);

        return null;
    }

    public List<D> getItems() {
        if (mData == null)
            return new ArrayList<>();

        return mData;
    }

    public void removeData(D data) {
        mData.remove(data);
        notifyDataSetChanged();
    }

    public void addNewData(D data) {
        mData.add(data);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData = new ArrayList<>();
        notifyDataSetChanged();
    }
}
