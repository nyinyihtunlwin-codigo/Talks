package projects.nyinyihtunlwin.talks.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.talks.R;
import projects.nyinyihtunlwin.talks.adapters.RadioHourOnNPRAdapter;
import projects.nyinyihtunlwin.talks.components.EmptyViewPod;
import projects.nyinyihtunlwin.talks.components.SmartRecyclerView;

/**
 * Created by Dell on 1/24/2018.
 */

public class TEDRadioHourOnNPRFragment extends BaseFragment {

    @BindView(R.id.rv_radio_hour_on_npr)
    SmartRecyclerView rvRadioHourOnNPR;

    @BindView(R.id.vp_empty_movie)
    EmptyViewPod emptyViewPod;

    private RadioHourOnNPRAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ted_radio_hour_on_npr, container, false);
        ButterKnife.bind(this, view);

        rvRadioHourOnNPR.setHasFixedSize(true);
        mAdapter = new RadioHourOnNPRAdapter(getContext());
        rvRadioHourOnNPR.setEmptyView(emptyViewPod);
        rvRadioHourOnNPR.setAdapter(mAdapter);
        rvRadioHourOnNPR.setLayoutManager(new LinearLayoutManager(container.getContext()));

        return view;
    }
}
