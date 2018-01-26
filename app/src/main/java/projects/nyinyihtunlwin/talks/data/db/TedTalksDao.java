package projects.nyinyihtunlwin.talks.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import projects.nyinyihtunlwin.talks.data.vo.TalksVO;
import projects.nyinyihtunlwin.talks.utils.AppConstants;

@Dao
public interface TedTalksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertTalk(TalksVO talk);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertTalks(List<TalksVO> talksList);

    @Query("SELECT * FROM " + AppConstants.TABLE_TALKS)
    LiveData<List<TalksVO>> getTedTalks();

    @Query("DELETE FROM " + AppConstants.TABLE_TALKS)
    void deleteAll();
}
