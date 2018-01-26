package projects.nyinyihtunlwin.talks.data.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dell on 1/25/2018.
 */

@Entity(tableName = "tags")
public class TagVO {

    @PrimaryKey
    @SerializedName("tag_id")
    private int tagId;

    @SerializedName("tag")
    private String tag;

    @SerializedName("description")
    private String description;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
