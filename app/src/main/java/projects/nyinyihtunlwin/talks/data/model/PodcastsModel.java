package projects.nyinyihtunlwin.talks.data.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import projects.nyinyihtunlwin.talks.TalkApp;
import projects.nyinyihtunlwin.talks.data.db.AppDatabase;
import projects.nyinyihtunlwin.talks.data.vo.PodcastsVO;
import projects.nyinyihtunlwin.talks.network.responses.GetPodcastsResponse;
import projects.nyinyihtunlwin.talks.utils.AppConstants;
import projects.nyinyihtunlwin.talks.utils.ConfigUtils;

/**
 * Created by Dell on 1/26/2018.
 */

public class PodcastsModel extends BaseModel {

    private MutableLiveData<List<PodcastsVO>> mPodcastList;

    public PodcastsModel() {
        mPodcastList = new MutableLiveData<>();
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

    public LiveData<List<PodcastsVO>> loadMorePodcasts() {
        int i = ConfigUtils.getInstance().loadPodcastsIndex();
        return loadPodcasts(ConfigUtils.getInstance().loadPodcastsIndex(), AppConstants.ACCESS_TOKEN);
    }

    public LiveData<List<PodcastsVO>> onForceRefreshPodcasts() {
        ConfigUtils.getInstance().savePodcastsPageIndex(1);
        mPodcastList = new MutableLiveData<>();
        return loadPodcasts(ConfigUtils.getInstance().loadPodcastsIndex(), AppConstants.ACCESS_TOKEN);
    }

    public LiveData<List<PodcastsVO>> startLoadingPodcasts() {
        return loadPodcasts(ConfigUtils.getInstance().loadPodcastsIndex(), AppConstants.ACCESS_TOKEN);
    }

    public LiveData<List<PodcastsVO>> getPodcasts() {
        return mAppDatabase.podcastsDao().getPodcastsVOList();
    }

    public LiveData<List<PodcastsVO>> loadPodcasts(int pageIndex, String accessToken) {
        Observable<GetPodcastsResponse> podcastsResponseObservable = mTalkApi.getPodcasts(pageIndex, accessToken);
        podcastsResponseObservable
                .subscribeOn(Schedulers.io()) //run value creation code on a specific thread (non-UI thread)
                .observeOn(AndroidSchedulers.mainThread()) //observe the emitted value of the Observable on an appropriate thread
                .subscribe(new Observer<GetPodcastsResponse>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GetPodcastsResponse getPodcastsResponse) {
                        if (getPodcastsResponse.getPodcastsVOList() != null && getPodcastsResponse.getPodcastsVOList().size() > 0) {
                            mPodcastList.setValue(getPodcastsResponse.getPodcastsVOList());
                            ConfigUtils.getInstance().savePodcastsPageIndex(getPodcastsResponse.getPage() + 1);
                            mAppDatabase.podcastsDao().deleteAll();
                            long[] insertedIds = mAppDatabase.podcastsDao().insertPodcasts(getPodcastsResponse.getPodcastsVOList());
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
        return mPodcastList;
    }
}
