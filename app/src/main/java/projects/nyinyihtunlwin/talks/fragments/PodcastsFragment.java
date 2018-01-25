package projects.nyinyihtunlwin.talks.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.talks.R;

/**
 * Created by Dell on 1/23/2018.
 */

public class PodcastsFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.tv_npr)
    TextView tvNPR;

    @BindView(R.id.tv_x)
    TextView tvX;

    @BindView(R.id.ll_top_bar)
    LinearLayout llTopBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_podcasts, container, false);
        ButterKnife.bind(this, view);

        tvNPR.setTextColor(getResources().getColor(R.color.primary));
        setFragment(new TalkNewestFragment());


        tvNPR.setOnClickListener(this);
        tvX.setOnClickListener(this);

        return view;
    }

    private void setFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fl_talk_container, fragment).commit();
    }


    private void changeTextColor(int position) {
        for (int index = 0; index < llTopBar.getChildCount(); index++) {
            TextView textView = (TextView) llTopBar.getChildAt(index);
            textView.setTextColor(getResources().getColor(R.color.secondary_text));
        }
        TextView tvCurrent = (TextView) llTopBar.getChildAt(position);
        tvCurrent.setTextColor(getResources().getColor(R.color.primary));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_npr:
                changeTextColor(0);
                setFragment(new TEDRadioHourOnNPRFragment());
                break;
            case R.id.tv_x:
                changeTextColor(1);
                setFragment(new TalkTrendingFragment());
                break;
        }
    }
}
