package projects.nyinyihtunlwin.talks.data.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import projects.nyinyihtunlwin.talks.data.vo.TalksVO;
import projects.nyinyihtunlwin.talks.network.TalksApi;
import projects.nyinyihtunlwin.talks.network.responses.GetTalksResponse;
import projects.nyinyihtunlwin.talks.utils.AppConstants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dell on 1/25/2018.
 */

public class TalksModel extends ViewModel {

    private int mPageIndex = 1;
    private TalksApi mTalkApi;

    private MutableLiveData<List<TalksVO>> mTalksVOList;

    public TalksModel() {
        mTalksVOList = new MutableLiveData<>();
        initTedTalksApi();
    }

    private void initTedTalksApi() {
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

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<List<TalksVO>> getTedTalks() {
        Observable<GetTalksResponse> talksResponseObservable = mTalkApi.getTedTalkList(mPageIndex, AppConstants.ACCESS_TOKEN);
        talksResponseObservable
                .subscribeOn(Schedulers.io()) //run value creation code on a specific thread (non-UI thread)
                .observeOn(AndroidSchedulers.mainThread()) //observe the emitted value of the Observable on an appropriate thread
                .subscribe(new Observer<GetTalksResponse>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GetTalksResponse getTalksResponse) {
                        mTalksVOList.setValue(getTalksResponse.getTalksVOList());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return mTalksVOList;
    }
}
