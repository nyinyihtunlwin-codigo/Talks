package projects.nyinyihtunlwin.talks.data.db.converters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import projects.nyinyihtunlwin.talks.data.vo.SegmentVO;
import projects.nyinyihtunlwin.talks.data.vo.TalksVO;

/**
 * Created by Dell on 1/26/2018.
 */

public class SegmentVOConverter {
    @TypeConverter
    public String fromSegmentList(List<SegmentVO> segmentVOList) {
        if (segmentVOList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<SegmentVO>>() {
        }.getType();
        String json = gson.toJson(segmentVOList, type);
        return json;
    }

    @TypeConverter
    public List<SegmentVO> toSegmentList(String segmentString) {
        if (segmentString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<SegmentVO>>() {
        }.getType();
        List<SegmentVO> countryLangList = gson.fromJson(segmentString, type);
        return countryLangList;
    }
}
