package projects.nyinyihtunlwin.talks.data.db.converters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import projects.nyinyihtunlwin.talks.data.vo.TagVO;
import projects.nyinyihtunlwin.talks.data.vo.TalksVO;

/**
 * Created by Dell on 1/26/2018.
 */

public class TalksVOConverter {

    @TypeConverter
    public String fromCountryLangList(List<TalksVO> countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<TalksVO>>() {
        }.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public List<TalksVO> toCountryLangList(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<TalksVO>>() {
        }.getType();
        List<TalksVO> countryLangList = gson.fromJson(countryLangString, type);
        return countryLangList;
    }
}
