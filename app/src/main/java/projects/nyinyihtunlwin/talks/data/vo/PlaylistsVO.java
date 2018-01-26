package projects.nyinyihtunlwin.talks.data.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import projects.nyinyihtunlwin.talks.data.db.converters.TalksVOConverter;
import projects.nyinyihtunlwin.talks.utils.AppConstants;

/**
 * Created by Dell on 1/24/2018.
 */
@Entity(tableName = AppConstants.TABLE_PLAYLISTS)
@TypeConverters(TalksVOConverter.class)
public class PlaylistsVO {

    @PrimaryKey
    @SerializedName("playlist_id")
    private int playlistId;

    @SerializedName("title")
    private String title;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("totalTalks")
    private int totalTalks;

    @SerializedName("description")
    private String description;

    @SerializedName("talksInPlaylist")
    private List<TalksVO> talksInPlayListList;

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getTotalTalks() {
        return totalTalks;
    }

    public void setTotalTalks(int totalTalks) {
        this.totalTalks = totalTalks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TalksVO> getTalksInPlayListList() {
        return talksInPlayListList;
    }

    public void setTalksInPlayListList(List<TalksVO> talksInPlayListList) {
        this.talksInPlayListList = talksInPlayListList;
    }
}
