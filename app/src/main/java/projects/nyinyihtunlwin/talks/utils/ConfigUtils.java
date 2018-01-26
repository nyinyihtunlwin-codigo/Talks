package projects.nyinyihtunlwin.talks.utils;

import android.content.Context;
import android.content.SharedPreferences;

import projects.nyinyihtunlwin.talks.TalkApp;

/**
 * Created by Dell on 1/26/2018.
 */

public class ConfigUtils {
    private static final String KEY_PAGE_TALK_INDEX = "KEY_PAGE_TALK_INDEX";
    private static final String KEY_PAGE_PLAYLIST_INDEX = "KEY_PAGE_PLAYLIST_INDEX";
    private static final String KEY_PAGE_PODCASTS_INDEX = "KEY_PAGE_PODCASTS_INDEX";
    private static final String KEY_PAGE_SEARCH_INDEX = "KEY_PAGE_SEARCH_INDEX";

    private static ConfigUtils mObjInstance;

    private SharedPreferences mSharedPreferences;

    private ConfigUtils() {
        mSharedPreferences = TalkApp.getContext().getSharedPreferences("ConfigUtils", Context.MODE_PRIVATE);
    }

    public static ConfigUtils getInstance() {
        if (mObjInstance == null) {
            mObjInstance = new ConfigUtils();
        }
        return mObjInstance;
    }

    public void saveTalksPageIndex(int pageIndex) {
        mSharedPreferences.edit().putInt(KEY_PAGE_TALK_INDEX, pageIndex).apply();
    }

    public int loadTalksPageIndex() {
        return mSharedPreferences.getInt(KEY_PAGE_TALK_INDEX, 1);
    }

    public void savePlaylistsPageIndex(int pageIndex) {
        mSharedPreferences.edit().putInt(KEY_PAGE_PLAYLIST_INDEX, pageIndex).apply();
    }

    public int loadPlaylistsPageIndex() {
        return mSharedPreferences.getInt(KEY_PAGE_PLAYLIST_INDEX, 1);
    }

    public void savePodcastsPageIndex(int pageIndex) {
        mSharedPreferences.edit().putInt(KEY_PAGE_PODCASTS_INDEX, pageIndex).apply();
    }

    public int loadPodcastsIndex() {
        return mSharedPreferences.getInt(KEY_PAGE_PODCASTS_INDEX, 1);
    }

    public void saveSearchResultPageIndex(int pageIndex) {
        mSharedPreferences.edit().putInt(KEY_PAGE_SEARCH_INDEX, pageIndex).apply();
    }

    public int loadSearchResultsIndex() {
        return mSharedPreferences.getInt(KEY_PAGE_SEARCH_INDEX, 1);
    }
}
