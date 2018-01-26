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

public class TalkFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.tv_newest)
    TextView tvNewest;

    @BindView(R.id.tv_trending)
    TextView tvTrending;

    @BindView(R.id.tv_most_viewed)
    TextView tvMostViewed;

    @BindView(R.id.tv_hidden_gems)
    TextView tvHiddenGems;

    @BindView(R.id.ll_top_bar)
    LinearLayout llTopBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_talk, container, false);
        ButterKnife.bind(this, view);

        tvNewest.setTextColor(getResources().getColor(R.color.primary));
        setFragment(new TalkNewestFragment());


        tvNewest.setOnClickListener(this);
        tvTrending.setOnClickListener(this);
        tvMostViewed.setOnClickListener(this);
        tvHiddenGems.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_newest:
                changeTextColor(0);
                setFragment(new TalkNewestFragment());
                break;
            case R.id.tv_trending:
                changeTextColor(1);
                setFragment(new TalkTrendingFragment());
                break;
            case R.id.tv_most_viewed:
                changeTextColor(2);
                setFragment(new TalkMostViewedFragment());
                break;
            case R.id.tv_hidden_gems:
                setFragment(new TalkHiddenGemsFragment());
                changeTextColor(3);
                break;
        }
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
}
