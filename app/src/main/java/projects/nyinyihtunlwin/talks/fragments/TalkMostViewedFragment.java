package projects.nyinyihtunlwin.talks.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import projects.nyinyihtunlwin.talks.R;

/**
 * Created by Dell on 1/23/2018.
 */

public class TalkMostViewedFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_talk_most_viewed,container,false);
        return view;
    }
}
