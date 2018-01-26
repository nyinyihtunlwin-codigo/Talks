package projects.nyinyihtunlwin.talks.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.talks.R;
import projects.nyinyihtunlwin.talks.data.vo.PodcastsVO;

/**
 * Created by Dell on 1/24/2018.
 */

public class RadioHourOnNPRViewHolder extends BaseViewHolder<PodcastsVO> {

    @BindView(R.id.iv_radio_hour_back)
    ImageView ivRadioHourBack;

    @BindView(R.id.tv_radio_hour_title)
    TextView tvRadioHourTitle;

    @BindView(R.id.tv_radio_hour_desc)
    TextView tvRadioHourDesc;

    @BindView(R.id.iv_radio_hour_host)
    ImageView ivRadioHourHost;

    PodcastsVO mData;

    public RadioHourOnNPRViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(PodcastsVO data) {
        mData = data;

        tvRadioHourTitle.setText(mData.getTitle());
        tvRadioHourDesc.setText(mData.getDescription());

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.img_ted_talk_placeholder)
                .centerCrop();
        Glide.with(itemView.getRootView().getContext()).load(mData.getImageUrl()).apply(requestOptions).into(ivRadioHourBack);
    }
}
