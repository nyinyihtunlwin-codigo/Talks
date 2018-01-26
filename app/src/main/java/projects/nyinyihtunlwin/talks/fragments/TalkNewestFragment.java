package projects.nyinyihtunlwin.talks.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.talks.R;
import projects.nyinyihtunlwin.talks.adapters.TalksNewestAdapter;
import projects.nyinyihtunlwin.talks.components.EmptyViewPod;
import projects.nyinyihtunlwin.talks.components.SmartRecyclerView;
import projects.nyinyihtunlwin.talks.data.model.TalksModel;
import projects.nyinyihtunlwin.talks.data.vo.TalksVO;

/**
 * Created by Dell on 1/23/2018.
 */

public class TalkNewestFragment extends BaseFragment {

    @BindView(R.id.rv_newest)
    SmartRecyclerView rvNewest;

    @BindView(R.id.vp_empty_movie)
    EmptyViewPod emptyViewPod;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private TalksNewestAdapter mAdapter;

    private TalksModel mTalksModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_talk_newest, container, false);
        ButterKnife.bind(this, view);


        rvNewest.setHasFixedSize(true);
        mAdapter = new TalksNewestAdapter(getContext());
        rvNewest.setEmptyView(emptyViewPod);
        rvNewest.setAdapter(mAdapter);
        rvNewest.setLayoutManager(new LinearLayoutManager(container.getContext()));

        swipeRefreshLayout.setRefreshing(true);


        mTalksModel = ViewModelProviders.of(this).get(TalksModel.class);
        mTalksModel.getTedTalks().observe(this, new Observer<List<TalksVO>>() {
            @Override
            public void onChanged(@Nullable List<TalksVO> talksVOS) {
                mAdapter.setNewData(talksVOS);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

        return view;
    }
}
