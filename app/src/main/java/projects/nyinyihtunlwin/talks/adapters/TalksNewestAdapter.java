package projects.nyinyihtunlwin.talks.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import projects.nyinyihtunlwin.talks.R;
import projects.nyinyihtunlwin.talks.data.vo.TalksVO;
import projects.nyinyihtunlwin.talks.viewholders.TalksNewestViewHolder;

/**
 * Created by Dell on 1/23/2018.
 */

public class TalksNewestAdapter extends BaseAdapter<TalksNewestViewHolder, TalksVO> {

    public TalksNewestAdapter(Context context) {
        super(context);
    }

    @Override
    public TalksNewestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_talks_newest, parent, false);
        return new TalksNewestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TalksNewestViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 16;
    }
}
