package projects.nyinyihtunlwin.talks.data.vo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dell on 1/25/2018.
 */

public class PodcastsVO {

    @SerializedName("podcast_id")
    private int podcastId;

    @SerializedName("title")
    private String title;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("description")
    private String description;

    @SerializedName("segments")
    private List<SegmentVO> segments;

    public int getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(int podcastId) {
        this.podcastId = podcastId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SegmentVO> getSegments() {
        return segments;
    }

    public void setSegments(List<SegmentVO> segments) {
        this.segments = segments;
    }
}
