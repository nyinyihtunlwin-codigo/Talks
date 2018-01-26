package projects.nyinyihtunlwin.talks.data.model;

import android.arch.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import projects.nyinyihtunlwin.talks.data.db.AppDatabase;
import projects.nyinyihtunlwin.talks.network.TalksApi;
import projects.nyinyihtunlwin.talks.utils.AppConstants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dell on 1/26/2018.
 */

public class BaseModel extends ViewModel {

    protected TalksApi mTalkApi;

    protected AppDatabase mAppDatabase;


    protected void initTedTalksApi() {
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
