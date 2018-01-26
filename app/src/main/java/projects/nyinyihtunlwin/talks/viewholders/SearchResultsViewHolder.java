package projects.nyinyihtunlwin.talks.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.talks.R;
import projects.nyinyihtunlwin.talks.data.vo.SearchResultVO;

/**
 * Created by Dell on 1/26/2018.
 */

public class SearchResultsViewHolder extends BaseViewHolder<SearchResultVO> {

    @BindView(R.id.iv_search_result)
    ImageView ivSearchResult;

    @BindView(R.id.tv_search_result_speaker)
    TextView tvSearchResultSpeaker;

    @BindView(R.id.tv_search_result_title)
    TextView tvSearchResultTitle;

    @BindView(R.id.tv_talked_time)
    TextView tvTalkedTime;

    SearchResultVO mData;

    public SearchResultsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(SearchResultVO data) {
        mData = data;

        tvSearchResultTitle.setText(mData.getTitle());

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.img_ted_talk_placeholder)
                .centerCrop();
        Glide.with(itemView.getRootView().getContext()).load(mData.getImageUrl()).apply(requestOptions).into(ivSearchResult);
    }
}
