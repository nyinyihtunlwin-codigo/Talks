package projects.nyinyihtunlwin.talks.data.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
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
import projects.nyinyihtunlwin.talks.data.vo.TalksVO;
import projects.nyinyihtunlwin.talks.network.responses.GetTalksResponse;
import projects.nyinyihtunlwin.talks.utils.AppConstants;

/**
 * Created by Dell on 1/25/2018.
 */

public class TalksModel extends ViewModel {

    private Context mContext;
    private TalkApp mTalkApp;
    private int mPageIndex = 1;

    public TalksModel(Context context) {
        this.mContext = context;
        mTalkApp = (TalkApp) context;
    }

    public void getTedTalks() {
        Observable<GetTalksResponse> talksResponseObservable = mTalkApp.getTalkApi().getTedTalkList(mPageIndex, AppConstants.ACCESS_TOKEN);
        talksResponseObservable
                .subscribeOn(Schedulers.io()) //run value creation code on a specific thread (non-UI thread)
                .observeOn(AndroidSchedulers.mainThread()) //observe the emitted value of the Observable on an appropriate thread
                .subscribe(new Observer<GetTalksResponse>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GetTalksResponse getTalksResponse) {
                        for (TalksVO talksVO : getTalksResponse.getTalksVOList()) {
                            Log.e("talkvo", talksVO.getTitle());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
