package projects.nyinyihtunlwin.talks;

import android.app.Application;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import projects.nyinyihtunlwin.talks.network.TalksApi;
import projects.nyinyihtunlwin.talks.utils.AppConstants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dell on 1/23/2018.
 */

public class TalkApp extends Application {

    private TalksApi mTalkApi;

    @Override
    public void onCreate() {
        super.onCreate();
        initRestaurantApi();
    }

    public TalksApi getTalkApi() {
        return mTalkApi;
    }

    private void initRestaurantApi() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        mTalkApi = retrofit.create(TalksApi.class);
    }
}
