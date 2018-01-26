package projects.nyinyihtunlwin.talks.data.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dell on 1/25/2018.
 */
@Entity(tableName = "speakers")
public class SpeakerVO {

    @PrimaryKey
    @SerializedName("speaker_id")
    private int speakerId;

    @SerializedName("name")
    private String name;

    public int getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(int speakerId) {
        this.speakerId = speakerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
