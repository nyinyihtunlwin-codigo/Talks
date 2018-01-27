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
import projects.nyinyihtunlwin.talks.data.vo.PlaylistsVO;
import projects.nyinyihtunlwin.talks.network.responses.GetPlaylistsResponse;
import projects.nyinyihtunlwin.talks.utils.AppConstants;
import projects.nyinyihtunlwin.talks.utils.ConfigUtils;

/**
 * Created by Dell on 1/26/2018.
 */

public class PlaylistsModel extends BaseModel {

    private MutableLiveData<List<PlaylistsVO>> mPlaylistsList;

    public PlaylistsModel() {
        mPlaylistsList = new MutableLiveData<>();
        initTedTalksApi();
    }

    public void initDatabase(Context context) {
        mAppDatabase = AppDatabase.getDatabase(context);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        AppDatabase.destroyInstance();
    }

    public LiveData<List<PlaylistsVO>> loadMorePlaylists() {
        int i = ConfigUtils.getInstance().loadPlaylistsPageIndex();
        return loadPlaylists(ConfigUtils.getInstance().loadPlaylistsPageIndex(), AppConstants.ACCESS_TOKEN);
    }

    public LiveData<List<PlaylistsVO>> onForceRefreshPlaylists() {
        ConfigUtils.getInstance().savePlaylistsPageIndex(1);
        mPlaylistsList = new MutableLiveData<>();
        return loadPlaylists(ConfigUtils.getInstance().loadPlaylistsPageIndex(), AppConstants.ACCESS_TOKEN);
    }

    public LiveData<List<PlaylistsVO>> startLoadingPlaylists() {
        return loadPlaylists(ConfigUtils.getInstance().loadPlaylistsPageIndex(), AppConstants.ACCESS_TOKEN);
    }

    public LiveData<List<PlaylistsVO>> getPlaylists() {
        return mAppDatabase.playlistsDao().getPlaylists();
    }

    public LiveData<List<PlaylistsVO>> loadPlaylists(int pageIndex, String accessToken) {
        Observable<GetPlaylistsResponse> playlistResponseObservable = mTalkApi.getPlaylists(pageIndex, accessToken);
        playlistResponseObservable
                .subscribeOn(Schedulers.io()) //run value creation code on a specific thread (non-UI thread)
                .observeOn(AndroidSchedulers.mainThread()) //observe the emitted value of the Observable on an appropriate thread
                .subscribe(new Observer<GetPlaylistsResponse>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GetPlaylistsResponse getPlaylistsResponse) {
                        if (getPlaylistsResponse.getPlaylistsVOList() != null && getPlaylistsResponse.getPlaylistsVOList().size() > 0) {
                            mPlaylistsList.setValue(getPlaylistsResponse.getPlaylistsVOList());
                            ConfigUtils.getInstance().savePlaylistsPageIndex(getPlaylistsResponse.getPage() + 1);
                            mAppDatabase.playlistsDao().deleteAll();
                            long[] insertedIds = mAppDatabase.playlistsDao().insertPlaylists(getPlaylistsResponse.getPlaylistsVOList());
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
        return mPlaylistsList;
    }
}
