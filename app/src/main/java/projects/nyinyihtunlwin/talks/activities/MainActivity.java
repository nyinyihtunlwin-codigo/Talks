package projects.nyinyihtunlwin.talks.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.talks.R;
import projects.nyinyihtunlwin.talks.adapters.SectionPagerAdapter;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.vp_talks)
    ViewPager vpTalks;

    @BindView(R.id.tv_section_name)
    TextView tvSectionName;

    @BindView(R.id.tv_app_name)
    TextView tvAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this, this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvAppName.setTypeface(Typeface.createFromAsset(getAssets(), "entsans.ttf"));

        final SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());

        vpTalks.setAdapter(sectionPagerAdapter);

        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.primary);
        tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);

        vpTalks.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout) {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setSectionTitle(position);
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(vpTalks) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.primary);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                vpTalks.setCurrentItem(tab.getPosition());
                setSectionTitle(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.tab_color);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = SearchActivity.newIntent(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    private void setSectionTitle(int position) {
        String sectionTitle = "";
        switch (position) {
            case 0:
                sectionTitle = "Talks";
                break;
            case 1:
                sectionTitle = "Playlists";
                break;
            case 2:
                sectionTitle = "Podcasts";
                break;
            case 3:
                sectionTitle = "Surprise Me";
                break;
            case 4:
                sectionTitle = "My talks";
                break;
        }
        tvSectionName.setText(sectionTitle);
    }
}
