package projects.nyinyihtunlwin.talks.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import projects.nyinyihtunlwin.talks.data.vo.TalksVO;

/**
 * Created by Dell on 1/25/2018.
 */

public class GetTalksResponse extends BaseResponse {

    @SerializedName("ted_talks")
    private List<TalksVO> talksVOList;

    public List<TalksVO> getTalksVOList() {
        return talksVOList;
    }
}
