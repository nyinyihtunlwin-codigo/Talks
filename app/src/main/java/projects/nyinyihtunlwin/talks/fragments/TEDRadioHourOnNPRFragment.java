package projects.nyinyihtunlwin.talks.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.talks.R;
import projects.nyinyihtunlwin.talks.adapters.RadioHourOnNPRAdapter;
import projects.nyinyihtunlwin.talks.components.EmptyViewPod;
import projects.nyinyihtunlwin.talks.components.SmartRecyclerView;
import projects.nyinyihtunlwin.talks.components.SmartScrollListener;
import projects.nyinyihtunlwin.talks.data.model.PodcastsModel;
import projects.nyinyihtunlwin.talks.data.model.TalksModel;
import projects.nyinyihtunlwin.talks.data.vo.PodcastsVO;
import projects.nyinyihtunlwin.talks.mvp.presenters.PodcastsPresenter;
import projects.nyinyihtunlwin.talks.mvp.presenters.TalksPresenter;
import projects.nyinyihtunlwin.talks.mvp.views.PodcastsView;

/**
 * Created by Dell on 1/24/2018.
 */

public class TEDRadioHourOnNPRFragment extends BaseFragment implements PodcastsView {

    @BindView(R.id.rv_radio_hour_on_npr)
    SmartRecyclerView rvRadioHourOnNPR;

    @BindView(R.id.vp_empty_movie)
    EmptyViewPod emptyViewPod;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private RadioHourOnNPRAdapter mAdapter;


    private PodcastsModel mPodcastModel;
    private PodcastsPresenter mPresenter;

    private SmartScrollListener mSmartScrollListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ted_radio_hour_on_npr, container, false);
        ButterKnife.bind(this, view);

        mPodcastModel = ViewModelProviders.of(this).get(PodcastsModel.class);
        mPodcastModel.initDatabase(getActivity());
        mPresenter = new PodcastsPresenter(this, mPodcastModel);
        mPresenter.onCreate(this);


        rvRadioHourOnNPR.setHasFixedSize(true);
        mAdapter = new RadioHourOnNPRAdapter(getContext());
        rvRadioHourOnNPR.setEmptyView(emptyViewPod);
        rvRadioHourOnNPR.setAdapter(mAdapter);
        rvRadioHourOnNPR.setLayoutManager(new LinearLayoutManager(container.getContext()));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.forceRefreshPodcasts(getActivity());
            }
        });

        mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReached() {
                mPresenter.laodMorePodcasts(getActivity());
            }
        });
        rvRadioHourOnNPR.addOnScrollListener(mSmartScrollListener);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void displayPodcasts(List<PodcastsVO> podcastsVOList) {
        mAdapter.setNewData(podcastsVOList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }
}
