package projects.nyinyihtunlwin.talks;

import android.app.Application;
import android.content.Context;

/**
 * Created by Dell on 1/23/2018.
 */

public class TalkApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
