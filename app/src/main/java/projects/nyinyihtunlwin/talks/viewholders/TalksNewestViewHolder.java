package projects.nyinyihtunlwin.talks.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.talks.R;
import projects.nyinyihtunlwin.talks.data.vo.TalksVO;

/**
 * Created by Dell on 1/23/2018.
 */

public class TalksNewestViewHolder extends BaseViewHolder<TalksVO> {

    @BindView(R.id.iv_talk_background)
    ImageView ivTalk;

    @BindView(R.id.tv_talk_title)
    TextView tvTalkTitle;

    @BindView(R.id.tv_talker)
    TextView tvTalker;

    @BindView(R.id.tv_talked_time)
    TextView tvTalkedTime;

    TalksVO mData;

    public TalksNewestViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(TalksVO data) {
        mData = data;

        tvTalker.setText(mData.getSpeaker().getName());
        tvTalkTitle.setText(mData.getTitle());

        int tot_seconds = mData.getDurationInSec();
        int hours = tot_seconds / 3600;
        int minutes = (tot_seconds % 3600) / 60;
        int seconds = tot_seconds % 60;

        tvTalkedTime.setText(hours + ":" + minutes + ":" + seconds);

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.img_ted_talk_placeholder)
                .centerCrop();
        Glide.with(itemView.getRootView().getContext()).load(mData.getImageUrl()).apply(requestOptions).into(ivTalk);

    }
}
