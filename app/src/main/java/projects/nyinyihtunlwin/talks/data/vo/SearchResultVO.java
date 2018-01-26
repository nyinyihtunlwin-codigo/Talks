package projects.nyinyihtunlwin.talks.data.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import projects.nyinyihtunlwin.talks.utils.AppConstants;

/**
 * Created by Dell on 1/25/2018.
 */
@Entity(tableName = AppConstants.TABLE_SEARCH_RESULTS)
public class SearchResultVO {

    @PrimaryKey
    @SerializedName("search_result_id")
    private int searchResultId;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("result_type")
    private String resultType;

    @SerializedName("result_id")
    private int resultId;

    public int getSearchResultId() {
        return searchResultId;
    }

    public void setSearchResultId(int searchResultId) {
        this.searchResultId = searchResultId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }
}
