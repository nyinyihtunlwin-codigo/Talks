package projects.nyinyihtunlwin.talks.mvp.presenters;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import java.util.List;

import projects.nyinyihtunlwin.talks.data.model.SearchResultsModel;
import projects.nyinyihtunlwin.talks.data.vo.SearchResultVO;
import projects.nyinyihtunlwin.talks.mvp.views.SearchResultsView;

/**
 * Created by Dell on 1/26/2018.
 */

public class SearchResultsPresenter extends BasePresenter<SearchResultsView> {
    private SearchResultsModel mSearchResultModel;
    private LifecycleOwner mLifeCycleOwner;

    public SearchResultsPresenter(LifecycleOwner lifecycleOwner, SearchResultsModel mSearchResultModel) {
        super();
        this.mSearchResultModel = mSearchResultModel;
        this.mLifeCycleOwner = lifecycleOwner;
        startLoadingSearchResults(lifecycleOwner);
    }

    @Override
    public void onCreate(SearchResultsView mView) {
        super.onCreate(mView);
    }

    @Override
    public void onStart() {
        mSearchResultModel.getSearchResults().observe(mLifeCycleOwner, new Observer<List<SearchResultVO>>() {
            @Override
            public void onChanged(@Nullable List<SearchResultVO> searchResultVOList) {
                mView.displaySearchResults(searchResultVOList);
            }
        });
    }

    public void laodMoreSearchResults(LifecycleOwner lifecycleOwner) {
        mSearchResultModel.loadMoreSearchResults().observe(lifecycleOwner, new Observer<List<SearchResultVO>>() {
            @Override
            public void onChanged(@Nullable List<SearchResultVO> searchResultVOList) {
                mView.displaySearchResults(searchResultVOList);
            }
        });
    }

    public void forceRefreshSearchResults(LifecycleOwner lifecycleOwner) {
        mSearchResultModel.onForceRefreshSearchResults().observe(lifecycleOwner, new Observer<List<SearchResultVO>>() {
            @Override
            public void onChanged(@Nullable List<SearchResultVO> searchResultVOList) {
                mView.displaySearchResults(searchResultVOList);
            }
        });
    }

    public void startLoadingSearchResults(LifecycleOwner lifecycleOwner) {
        mSearchResultModel.startLoadingSearchResults().observe(lifecycleOwner, new Observer<List<SearchResultVO>>() {
            @Override
            public void onChanged(@Nullable List<SearchResultVO> searchResultVOList) {
                mView.displaySearchResults(searchResultVOList);
            }
        });
    }

    @Override
    public void onStop() {

    }
}
