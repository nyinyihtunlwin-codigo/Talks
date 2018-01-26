package projects.nyinyihtunlwin.talks.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import projects.nyinyihtunlwin.talks.data.vo.SearchResultVO;
import projects.nyinyihtunlwin.talks.utils.AppConstants;

/**
 * Created by Dell on 1/26/2018.
 */
@Dao
public interface SearchResultsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertSearchResult(SearchResultVO searchResultVO);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertSearchResults(List<SearchResultVO> searchResultVOList);

    @Query("SELECT * FROM " + AppConstants.TABLE_SEARCH_RESULTS)
    LiveData<List<SearchResultVO>> getSearchResults();

    @Query("DELETE FROM " + AppConstants.TABLE_SEARCH_RESULTS)
    void deleteAll();

}
