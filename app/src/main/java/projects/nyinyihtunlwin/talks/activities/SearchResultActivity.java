package projects.nyinyihtunlwin.talks.activities;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.talks.R;
import projects.nyinyihtunlwin.talks.adapters.SearchResultsAdapter;
import projects.nyinyihtunlwin.talks.components.EmptyViewPod;
import projects.nyinyihtunlwin.talks.components.SmartRecyclerView;
import projects.nyinyihtunlwin.talks.components.SmartScrollListener;
import projects.nyinyihtunlwin.talks.data.model.SearchResultsModel;
import projects.nyinyihtunlwin.talks.data.model.TalksModel;
import projects.nyinyihtunlwin.talks.data.vo.SearchResultVO;
import projects.nyinyihtunlwin.talks.mvp.presenters.SearchResultsPresenter;
import projects.nyinyihtunlwin.talks.mvp.presenters.TalksPresenter;
import projects.nyinyihtunlwin.talks.mvp.views.SearchResultsView;

public class SearchResultActivity extends BaseActivity implements SearchResultsView, LifecycleOwner {

    @BindView(R.id.rv_search_result)
    SmartRecyclerView rvSearchResult;

    @BindView(R.id.vp_empty_movie)
    EmptyViewPod emptyViewPod;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private SearchResultsAdapter mSearchResultAdapter;

    private SmartScrollListener mSmartScrollListener;

    private SearchResultsModel mSearchResultsModel;
    private SearchResultsPresenter mPresenter;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, SearchResultActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this, this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSearchResultsModel = ViewModelProviders.of(this).get(SearchResultsModel.class);
        mSearchResultsModel.initDatabase(getApplicationContext());
        mPresenter = new SearchResultsPresenter(this, mSearchResultsModel);
        mPresenter.onCreate(this);

        rvSearchResult.setHasFixedSize(true);
        mSearchResultAdapter = new SearchResultsAdapter(getApplicationContext());
        rvSearchResult.setEmptyView(emptyViewPod);
        rvSearchResult.setAdapter(mSearchResultAdapter);
        rvSearchResult.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout.setRefreshing(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.forceRefreshSearchResults(SearchResultActivity.this);
            }
        });


        mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReached() {
                mPresenter.laodMoreSearchResults(SearchResultActivity.this);
            }
        });

        rvSearchResult.addOnScrollListener(mSmartScrollListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displaySearchResults(List<SearchResultVO> searchResultVOList) {
        mSearchResultAdapter.setNewData(searchResultVOList);
        swipeRefreshLayout.setRefreshing(false);
    }
}
