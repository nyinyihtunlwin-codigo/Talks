package projects.nyinyihtunlwin.talks.data.db.converters;

import android.arch.persistence.room.TypeConverter;
import android.nfc.Tag;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import projects.nyinyihtunlwin.talks.data.vo.TagVO;

/**
 * Created by Dell on 1/26/2018.
 */

public class TagVOConverter {
    @TypeConverter
    public String fromTagVOList(List<TagVO> tagVOList) {
        if (tagVOList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<TagVO>>() {
        }.getType();
        String json = gson.toJson(tagVOList, type);
        return json;
    }

    @TypeConverter
    public List<TagVO> toTagVOList(String tagVOString) {
        if (tagVOString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<TagVO>>() {
        }.getType();
        List<TagVO> countryLangList = gson.fromJson(tagVOString, type);
        return countryLangList;
    }
}
