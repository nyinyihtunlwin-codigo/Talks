package projects.nyinyihtunlwin.talks.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import projects.nyinyihtunlwin.talks.data.vo.PodcastsVO;
import projects.nyinyihtunlwin.talks.utils.AppConstants;

/**
 * Created by Dell on 1/26/2018.
 */
@Dao
public interface PodcastsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPodcast(PodcastsVO podcastsVO);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertPodcasts(List<PodcastsVO> podcastsVOList);

    @Query("SELECT * FROM "+ AppConstants.TABLE_PODCASTS)
    LiveData<List<PodcastsVO>> getPodcastsVOList();

    @Query("DELETE FROM "+AppConstants.TABLE_PODCASTS)
    void deleteAll();
}
