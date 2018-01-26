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
import projects.nyinyihtunlwin.talks.data.vo.SearchResultVO;
import projects.nyinyihtunlwin.talks.network.responses.GetSearchResponse;
import projects.nyinyihtunlwin.talks.utils.AppConstants;
import projects.nyinyihtunlwin.talks.utils.ConfigUtils;

/**
 * Created by Dell on 1/26/2018.
 */

public class SearchResultsModel extends BaseModel {

    private MutableLiveData<List<SearchResultVO>> mSearchResultList;

    public SearchResultsModel() {
        mSearchResultList = new MutableLiveData<>();
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

    public LiveData<List<SearchResultVO>> loadMoreSearchResults() {
        int i = ConfigUtils.getInstance().loadSearchResultsIndex();
        return loadSearching(ConfigUtils.getInstance().loadSearchResultsIndex(), AppConstants.ACCESS_TOKEN);
    }

    public LiveData<List<SearchResultVO>> onForceRefreshSearchResults() {
        ConfigUtils.getInstance().saveSearchResultPageIndex(1);
        mSearchResultList = new MutableLiveData<>();
        return loadSearching(ConfigUtils.getInstance().loadSearchResultsIndex(), AppConstants.ACCESS_TOKEN);
    }

    public LiveData<List<SearchResultVO>> startLoadingSearchResults() {
        return loadSearching(ConfigUtils.getInstance().loadSearchResultsIndex(), AppConstants.ACCESS_TOKEN);
    }

    public LiveData<List<SearchResultVO>> getSearchResults() {
        return mAppDatabase.searchResultsDao().getSearchResults();
    }

    public LiveData<List<SearchResultVO>> loadSearching(int pageIndex, String accessToken) {
        Observable<GetSearchResponse> searchResponseObservable = mTalkApi.getSearchResults(pageIndex, accessToken);
        searchResponseObservable
                .subscribeOn(Schedulers.io()) //run value creation code on a specific thread (non-UI thread)
                .observeOn(AndroidSchedulers.mainThread()) //observe the emitted value of the Observable on an appropriate thread
                .subscribe(new Observer<GetSearchResponse>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GetSearchResponse getSearchResponse) {
                        if (getSearchResponse.getSearchResultVOList() != null && getSearchResponse.getSearchResultVOList().size() > 0) {
                            mSearchResultList.setValue(getSearchResponse.getSearchResultVOList());
                            ConfigUtils.getInstance().saveSearchResultPageIndex(getSearchResponse.getPage() + 1);
                            mAppDatabase.searchResultsDao().deleteAll();
                            long[] insertedIds = mAppDatabase.searchResultsDao().insertSearchResults(getSearchResponse.getSearchResultVOList());
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
        return mSearchResultList;
    }
}
