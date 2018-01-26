package projects.nyinyihtunlwin.talks.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import projects.nyinyihtunlwin.talks.R;
import projects.nyinyihtunlwin.talks.data.vo.SearchResultVO;
import projects.nyinyihtunlwin.talks.viewholders.SearchResultsViewHolder;

/**
 * Created by Dell on 1/26/2018.
 */

public class SearchResultsAdapter extends BaseAdapter<SearchResultsViewHolder, SearchResultVO> {

    public SearchResultsAdapter(Context context) {
        super(context);
    }

    @Override
    public SearchResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_search_result, parent, false);
        return new SearchResultsViewHolder(view);
    }

}
