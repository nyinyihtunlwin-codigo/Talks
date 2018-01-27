package projects.nyinyihtunlwin.talks.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.talks.R;
import projects.nyinyihtunlwin.talks.adapters.PlaylistsAdapter;
import projects.nyinyihtunlwin.talks.components.EmptyViewPod;
import projects.nyinyihtunlwin.talks.components.SmartRecyclerView;
import projects.nyinyihtunlwin.talks.components.SmartScrollListener;
import projects.nyinyihtunlwin.talks.data.model.PlaylistsModel;
import projects.nyinyihtunlwin.talks.data.model.TalksModel;
import projects.nyinyihtunlwin.talks.data.vo.PlaylistsVO;
import projects.nyinyihtunlwin.talks.mvp.presenters.PlaylistsPresenter;
import projects.nyinyihtunlwin.talks.mvp.presenters.TalksPresenter;
import projects.nyinyihtunlwin.talks.mvp.views.PlaylistsView;

/**
 * Created by Dell on 1/23/2018.
 */

public class PlaylistsFragment extends BaseFragment implements PlaylistsView {

    @BindView(R.id.rv_playlist)
    SmartRecyclerView rvPlaylists;

    @BindView(R.id.vp_empty_movie)
    EmptyViewPod emptyViewPod;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private PlaylistsAdapter mAdapter;


    private PlaylistsModel mPlaylistsModel;
    private PlaylistsPresenter mPresenter;

    private SmartScrollListener mSmartScrollListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        ButterKnife.bind(this, view);

        mPlaylistsModel = ViewModelProviders.of(this).get(PlaylistsModel.class);
        mPlaylistsModel.initDatabase(getActivity());
        mPresenter = new PlaylistsPresenter(this, mPlaylistsModel);
        mPresenter.onCreate(this);


        rvPlaylists.setHasFixedSize(true);
        mAdapter = new PlaylistsAdapter(getContext());
        rvPlaylists.setEmptyView(emptyViewPod);
        rvPlaylists.setAdapter(mAdapter);
        rvPlaylists.setLayoutManager(new GridLayoutManager(container.getContext(), 2));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.forceRefreshPlaylists(getActivity());
            }
        });

        mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReached() {
                mPresenter.laodMorePlaylists(getActivity());
            }
        });
        rvPlaylists.addOnScrollListener(mSmartScrollListener);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void displayPlaylists(List<PlaylistsVO> playlistsVOList) {
        mAdapter.setNewData(playlistsVOList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }
}
