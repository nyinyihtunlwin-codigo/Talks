package projects.nyinyihtunlwin.talks.mvp.presenters;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.List;

import projects.nyinyihtunlwin.talks.data.model.PlaylistsModel;
import projects.nyinyihtunlwin.talks.data.vo.PlaylistsVO;
import projects.nyinyihtunlwin.talks.mvp.views.PlaylistsView;

/**
 * Created by Dell on 1/26/2018.
 */

public class PlaylistsPresenter extends BasePresenter<PlaylistsView> {

    private PlaylistsModel mPlaylistsModel;
    private LifecycleOwner mLifeCycleOwner;

    public PlaylistsPresenter(LifecycleOwner lifecycleOwner, PlaylistsModel mPlaylistsModel) {
        super();
        this.mPlaylistsModel = mPlaylistsModel;
        this.mLifeCycleOwner = lifecycleOwner;
        startLoadingPlaylists(lifecycleOwner);
    }

    @Override
    public void onCreate(PlaylistsView mView) {
        super.onCreate(mView);
    }

    @Override
    public void onStart() {
        mPlaylistsModel.getPlaylists().observe(mLifeCycleOwner, new Observer<List<PlaylistsVO>>() {
            @Override
            public void onChanged(@Nullable List<PlaylistsVO> playlistsVOList) {
                mView.displayPlaylists(playlistsVOList);
            }
        });
    }

    public void laodMorePlaylists(LifecycleOwner lifecycleOwner) {
        mPlaylistsModel.loadMorePlaylists().observe(lifecycleOwner, new Observer<List<PlaylistsVO>>() {
            @Override
            public void onChanged(@Nullable List<PlaylistsVO> plalistsVOList) {
                mView.displayPlaylists(plalistsVOList);
            }
        });
    }

    public void forceRefreshPlaylists(LifecycleOwner lifecycleOwner) {
        mPlaylistsModel.onForceRefreshPlaylists().observe(lifecycleOwner, new Observer<List<PlaylistsVO>>() {
            @Override
            public void onChanged(@Nullable List<PlaylistsVO> plalistsVOList) {
                mView.displayPlaylists(plalistsVOList);
            }
        });
    }

    public void startLoadingPlaylists(LifecycleOwner lifecycleOwner) {
        mPlaylistsModel.startLoadingPlaylists().observe(lifecycleOwner, new Observer<List<PlaylistsVO>>() {
            @Override
            public void onChanged(@Nullable List<PlaylistsVO> plalistsVOList) {
                mView.displayPlaylists(plalistsVOList);
            }
        });
    }

    @Override
    public void onStop() {

    }

}
