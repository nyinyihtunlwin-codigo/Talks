package projects.nyinyihtunlwin.talks.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import projects.nyinyihtunlwin.talks.data.vo.TalksVO;

@Dao
public interface TedTalksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertTalk(TalksVO talk);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertTalks(List<TalksVO> talksList);

    @Query("SELECT * FROM talks")
    LiveData<List<TalksVO>> getTedTalks();

    @Query("DELETE FROM talks")
    void deleteAll();
}
