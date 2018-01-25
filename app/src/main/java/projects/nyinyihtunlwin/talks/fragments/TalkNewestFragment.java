package projects.nyinyihtunlwin.talks.fragments;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.talks.R;
import projects.nyinyihtunlwin.talks.adapters.TalksNewestAdapter;
import projects.nyinyihtunlwin.talks.components.EmptyViewPod;
import projects.nyinyihtunlwin.talks.components.SmartRecyclerView;
import projects.nyinyihtunlwin.talks.data.model.TalksModel;

/**
 * Created by Dell on 1/23/2018.
 */

public class TalkNewestFragment extends BaseFragment {

    @BindView(R.id.rv_newest)
    SmartRecyclerView rvNewest;

    @BindView(R.id.vp_empty_movie)
    EmptyViewPod emptyViewPod;

    private TalksNewestAdapter mAdapter;

    private TalksModel mTalksModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_talk_newest, container, false);
        ButterKnife.bind(this, view);

        mTalksModel=new TalksModel(getActivity().getApplicationContext());
        mTalksModel.getTedTalks();

        rvNewest.setHasFixedSize(true);
        mAdapter = new TalksNewestAdapter(getContext());
        rvNewest.setEmptyView(emptyViewPod);
        rvNewest.setAdapter(mAdapter);
        rvNewest.setLayoutManager(new LinearLayoutManager(container.getContext()));

        return view;
    }
}
