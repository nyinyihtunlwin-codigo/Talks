package projects.nyinyihtunlwin.talks.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import projects.nyinyihtunlwin.talks.data.vo.PodcastsVO;

/**
 * Created by Dell on 1/25/2018.
 */

public class GetPodcastsResponse extends BaseResponse{

    @SerializedName("ted_podcasts")
    private List<PodcastsVO> podcastsVOList;

    public List<PodcastsVO> getPodcastsVOList() {
        return podcastsVOList;
    }
}
