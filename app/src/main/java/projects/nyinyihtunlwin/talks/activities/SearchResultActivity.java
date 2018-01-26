package projects.nyinyihtunlwin.talks.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.talks.R;
import projects.nyinyihtunlwin.talks.adapters.SearchResultsAdapter;
import projects.nyinyihtunlwin.talks.components.EmptyViewPod;
import projects.nyinyihtunlwin.talks.components.SmartRecyclerView;
import projects.nyinyihtunlwin.talks.components.SmartScrollListener;

public class SearchResultActivity extends BaseActivity {

    @BindView(R.id.rv_search_result)
    SmartRecyclerView rvSearchResult;

    @BindView(R.id.vp_empty_movie)
    EmptyViewPod emptyViewPod;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private SearchResultsAdapter mSearchResultAdapter;

    private SmartScrollListener mSmartScrollListener;

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

        rvSearchResult.setHasFixedSize(true);
        mSearchResultAdapter = new SearchResultsAdapter(getApplicationContext());
        rvSearchResult.setEmptyView(emptyViewPod);
        rvSearchResult.setAdapter(mSearchResultAdapter);
        rvSearchResult.setLayoutManager(new LinearLayoutManager(this));

        mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReached() {

            }
        });

        rvSearchResult.addOnScrollListener(mSmartScrollListener);
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
}
