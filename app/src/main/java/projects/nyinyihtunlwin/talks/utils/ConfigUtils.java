package projects.nyinyihtunlwin.talks.utils;

import android.content.Context;
import android.content.SharedPreferences;

import projects.nyinyihtunlwin.talks.TalkApp;

/**
 * Created by Dell on 1/26/2018.
 */

public class ConfigUtils {

    private static final String KEY_PAGE_INDEX = "KEY_PAGE_INDEX";

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


    public void savePageIndex(int pageIndex) {
        mSharedPreferences.edit().putInt(KEY_PAGE_INDEX, pageIndex).apply();
    }

    public int loadPageIndex() {
        return mSharedPreferences.getInt(KEY_PAGE_INDEX, 1);
    }
}
