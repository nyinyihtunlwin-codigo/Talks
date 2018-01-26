package projects.nyinyihtunlwin.talks.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import projects.nyinyihtunlwin.talks.fragments.PlaylistsFragment;
import projects.nyinyihtunlwin.talks.fragments.PodcastsFragment;
import projects.nyinyihtunlwin.talks.fragments.TalkFragment;

/**
 * Created by Dell on 1/23/2018.
 */

public class SectionPagerAdapter extends FragmentPagerAdapter {

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new TalkFragment();
                break;
            case 1:
                fragment = new PlaylistsFragment();
                break;
            case 2:
                fragment = new PodcastsFragment();
                break;
            case 3:
                break;
            case 4:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
