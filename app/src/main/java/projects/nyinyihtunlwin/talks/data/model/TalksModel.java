package projects.nyinyihtunlwin.talks.data.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

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
import projects.nyinyihtunlwin.talks.TalkApp;
import projects.nyinyihtunlwin.talks.data.db.AppDatabase;
import projects.nyinyihtunlwin.talks.data.vo.TalksVO;
import projects.nyinyihtunlwin.talks.network.TalksApi;
import projects.nyinyihtunlwin.talks.network.responses.GetTalksResponse;
import projects.nyinyihtunlwin.talks.utils.AppConstants;
import projects.nyinyihtunlwin.talks.utils.ConfigUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dell on 1/25/2018.
 */

public class TalksModel extends BaseModel {

    private MutableLiveData<List<TalksVO>> mTalksVOList;

    public TalksModel() {
        mTalksVOList = new MutableLiveData<>();
        initTedTalksApi();
    }

    public void initDatabase(Context context) {
        mAppDatabase = AppDatabase.getInMemoryDatabase(context);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        AppDatabase.destroyInstance();
    }

    public LiveData<List<TalksVO>> loadMoreTedTalks() {
        int i = ConfigUtils.getInstance().loadPageIndex();
        return loadTedTalks(ConfigUtils.getInstance().loadPageIndex(), AppConstants.ACCESS_TOKEN);
    }

    public LiveData<List<TalksVO>> onForceRefreshTedTalks() {
        ConfigUtils.getInstance().savePageIndex(1);
        mTalksVOList = new MutableLiveData<>();
        return loadTedTalks(ConfigUtils.getInstance().loadPageIndex(), AppConstants.ACCESS_TOKEN);
    }

    public LiveData<List<TalksVO>> startLoadingTedTalks() {
        return loadTedTalks(ConfigUtils.getInstance().loadPageIndex(), AppConstants.ACCESS_TOKEN);
    }

    public LiveData<List<TalksVO>> getmTalksVOList() {
        return mAppDatabase.tedTalksDao().getTedTalks();
    }

    public LiveData<List<TalksVO>> loadTedTalks(int pageIndex, String accessToken) {
        Observable<GetTalksResponse> talksResponseObservable = mTalkApi.getTedTalkList(pageIndex, accessToken);
        talksResponseObservable
                .subscribeOn(Schedulers.io()) //run value creation code on a specific thread (non-UI thread)
                .observeOn(AndroidSchedulers.mainThread()) //observe the emitted value of the Observable on an appropriate thread
                .subscribe(new Observer<GetTalksResponse>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GetTalksResponse getTalksResponse) {
                        if (getTalksResponse.getTalksVOList() != null && getTalksResponse.getTalksVOList().size() > 0) {
                            mTalksVOList.setValue(getTalksResponse.getTalksVOList());
                            ConfigUtils.getInstance().savePageIndex(getTalksResponse.getPage() + 1);
                            mAppDatabase.tedTalksDao().deleteAll();
                            long[] insertedIds = mAppDatabase.tedTalksDao().insertTalks(getTalksResponse.getTalksVOList());
                            Log.d(TalkApp.TAG, "Total inserted count : " + insertedIds.length);
                        }
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
