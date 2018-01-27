package projects.nyinyihtunlwin.talks.data.vo;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import projects.nyinyihtunlwin.talks.data.db.converters.TagVOConverter;
import projects.nyinyihtunlwin.talks.utils.AppConstants;

/**
 * Created by Dell on 1/23/2018.
 */
@Entity(tableName = AppConstants.TABLE_TALKS)
@TypeConverters({TagVOConverter.class})
public class TalksVO {

    @PrimaryKey
    @SerializedName("talk_id")
    private int talkId;

    @SerializedName("title")
    private String title;

    @Embedded
    @SerializedName("speaker")
    private SpeakerVO speaker;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("durationInSec")
    private int durationInSec;

    @SerializedName("description")
    private String description;

    @SerializedName("tag")
    private List<TagVO> tagVOList;

    public int getTalkId() {
        return talkId;
    }

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SpeakerVO getSpeaker() {
        return speaker;
    }

    public void setSpeaker(SpeakerVO speaker) {
        this.speaker = speaker;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getDurationInSec() {
        return durationInSec;
    }

    public void setDurationInSec(int durationInSec) {
        this.durationInSec = durationInSec;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TagVO> getTagVOList() {
        if (tagVOList == null) {
            tagVOList = new ArrayList<>();
        }
        return tagVOList;
    }

    public void setTagVOList(List<TagVO> tagVOList) {
        this.tagVOList = tagVOList;
    }
}
