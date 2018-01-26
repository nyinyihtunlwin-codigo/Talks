package projects.nyinyihtunlwin.talks.mvp.presenters;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.List;

import projects.nyinyihtunlwin.talks.data.model.TalksModel;
import projects.nyinyihtunlwin.talks.data.vo.TalksVO;
import projects.nyinyihtunlwin.talks.mvp.views.TalksView;

/**
 * Created by Dell on 1/26/2018.
 */

public class TalksPresenter extends BasePresenter<TalksView> {

    private TalksModel mTalksModel;

    public TalksPresenter(TalksModel mTalksModel) {
        super();
        this.mTalksModel = mTalksModel;
    }

    @Override
    public void onCreate(TalksView mView) {
        super.onCreate(mView);
    }

    @Override
    public void onStart() {

    }

    public void loadMoreTalks(LifecycleOwner lifecycleOwner) {
        mTalksModel.loadMoreTedTalks().observe(lifecycleOwner, new Observer<List<TalksVO>>() {
            @Override
            public void onChanged(@Nullable List<TalksVO> talksVOS) {
                mView.displayTalksList(talksVOS);
            }
        });
    }

    public void forceRefreshTedTalks(LifecycleOwner lifecycleOwner) {
        mTalksModel.onForceRefreshTedTalks().observe(lifecycleOwner, new Observer<List<TalksVO>>() {
            @Override
            public void onChanged(@Nullable List<TalksVO> talksVOS) {
                mView.displayTalksList(talksVOS);
            }
        });
    }

    public void startLoadingTalks(LifecycleOwner lifecycleOwner) {
        mTalksModel.startLoadingTedTalks().observe(lifecycleOwner, new Observer<List<TalksVO>>() {
            @Override
            public void onChanged(@Nullable List<TalksVO> talksVOS) {
                mView.displayTalksList(talksVOS);
            }
        });
    }

    @Override
    public void onStop() {

    }


}
