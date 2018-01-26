package projects.nyinyihtunlwin.talks.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import projects.nyinyihtunlwin.talks.R;
import projects.nyinyihtunlwin.talks.data.vo.PlaylistsVO;
import projects.nyinyihtunlwin.talks.viewholders.PlaylistsViewHolder;

/**
 * Created by Dell on 1/24/2018.
 */

public class PlaylistsAdapter extends BaseAdapter<PlaylistsViewHolder, PlaylistsVO> {


    public PlaylistsAdapter(Context context) {
        super(context);
    }

    @Override
    public PlaylistsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.view_item_playlists, parent, false);
        return new PlaylistsViewHolder(view);
    }

}
