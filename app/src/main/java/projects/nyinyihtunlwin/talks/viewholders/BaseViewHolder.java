package projects.nyinyihtunlwin.talks.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Dell on 1/23/2018.
 */

public abstract class BaseViewHolder<D> extends RecyclerView.ViewHolder {

    private D data;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setData(D data);
}
