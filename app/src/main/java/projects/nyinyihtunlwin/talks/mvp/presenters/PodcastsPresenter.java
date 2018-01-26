package projects.nyinyihtunlwin.talks.mvp.presenters;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.List;

import projects.nyinyihtunlwin.talks.data.model.PodcastsModel;
import projects.nyinyihtunlwin.talks.data.vo.PodcastsVO;
import projects.nyinyihtunlwin.talks.mvp.views.PodcastsView;

/**
 * Created by Dell on 1/26/2018.
 */

public class PodcastsPresenter extends BasePresenter<PodcastsView> {
    private PodcastsModel mPodcastModel;
    private LifecycleOwner mLifeCycleOwner;

    public PodcastsPresenter(LifecycleOwner lifecycleOwner, PodcastsModel mPodcastModel) {
        super();
        this.mPodcastModel = mPodcastModel;
        this.mLifeCycleOwner = lifecycleOwner;
        startLoadingPodcasts(lifecycleOwner);
    }

    @Override
    public void onCreate(PodcastsView mView) {
        super.onCreate(mView);
    }

    @Override
    public void onStart() {
        mPodcastModel.getPodcasts().observe(mLifeCycleOwner, new Observer<List<PodcastsVO>>() {
            @Override
            public void onChanged(@Nullable List<PodcastsVO> podcastsVOList) {
                mView.displayPodcasts(podcastsVOList);
            }
        });
    }

    public void laodMorePodcasts(LifecycleOwner lifecycleOwner) {
        mPodcastModel.loadMorePodcasts().observe(lifecycleOwner, new Observer<List<PodcastsVO>>() {
            @Override
            public void onChanged(@Nullable List<PodcastsVO> podcastsVOList) {
                mView.displayPodcasts(podcastsVOList);
            }
        });
    }

    public void forceRefreshPodcasts(LifecycleOwner lifecycleOwner) {
        mPodcastModel.onForceRefreshPodcasts().observe(lifecycleOwner, new Observer<List<PodcastsVO>>() {
            @Override
            public void onChanged(@Nullable List<PodcastsVO> podcastsVOList) {
                mView.displayPodcasts(podcastsVOList);
            }
        });
    }

    public void startLoadingPodcasts(LifecycleOwner lifecycleOwner) {
        mPodcastModel.startLoadingPodcasts().observe(lifecycleOwner, new Observer<List<PodcastsVO>>() {
            @Override
            public void onChanged(@Nullable List<PodcastsVO> podcastsVOList) {
                mView.displayPodcasts(podcastsVOList);
            }
        });
    }

    @Override
    public void onStop() {

    }
}
