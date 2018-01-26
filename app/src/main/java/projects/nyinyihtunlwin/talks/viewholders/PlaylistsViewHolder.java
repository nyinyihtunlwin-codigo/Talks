package projects.nyinyihtunlwin.talks.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.talks.R;
import projects.nyinyihtunlwin.talks.data.vo.PlaylistsVO;

/**
 * Created by Dell on 1/24/2018.
 */

public class PlaylistsViewHolder extends BaseViewHolder<PlaylistsVO> {

    @BindView(R.id.tv_playlist_title)
    TextView tvPlaylistTitle;

    @BindView(R.id.tv_talked_time)
    TextView tvTalkedTime;

    @BindView(R.id.tv_talk_count)
    TextView tvTalkCount;

    @BindView(R.id.iv_playlists)
    ImageView ivPlaylists;

    PlaylistsVO mData;

    public PlaylistsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(PlaylistsVO data) {
        mData = data;

        tvPlaylistTitle.setText(mData.getTitle());
        tvTalkCount.setText(String.valueOf(mData.getTotalTalks()));


        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.img_ted_talk_placeholder)
                .centerCrop();
        Glide.with(itemView.getRootView().getContext()).load(mData.getImageUrl()).apply(requestOptions).into(ivPlaylists);
    }
}
