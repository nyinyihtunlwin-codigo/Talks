package projects.nyinyihtunlwin.talks.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.talks.R;
import projects.nyinyihtunlwin.talks.adapters.PlaylistsAdapter;
import projects.nyinyihtunlwin.talks.components.EmptyViewPod;
import projects.nyinyihtunlwin.talks.components.SmartRecyclerView;

/**
 * Created by Dell on 1/23/2018.
 */

public class PlaylistsFragment extends BaseFragment {

    @BindView(R.id.rv_playlist)
    SmartRecyclerView rvPlaylists;

    @BindView(R.id.vp_empty_movie)
    EmptyViewPod emptyViewPod;

    private PlaylistsAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        ButterKnife.bind(this, view);


        rvPlaylists.setHasFixedSize(true);
        mAdapter = new PlaylistsAdapter(getContext());
        rvPlaylists.setEmptyView(emptyViewPod);
        rvPlaylists.setAdapter(mAdapter);
        rvPlaylists.setLayoutManager(new GridLayoutManager(container.getContext(), 2));

        return view;
    }
}
