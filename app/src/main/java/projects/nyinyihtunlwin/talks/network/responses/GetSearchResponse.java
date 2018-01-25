package projects.nyinyihtunlwin.talks.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import projects.nyinyihtunlwin.talks.data.vo.SearchResultVO;

/**
 * Created by Dell on 1/25/2018.
 */

public class GetSearchResponse extends BaseResponse {

    @SerializedName("results")
    private List<SearchResultVO> searchResultVOList;

    public List<SearchResultVO> getSearchResultVOList() {
        return searchResultVOList;
    }
}
