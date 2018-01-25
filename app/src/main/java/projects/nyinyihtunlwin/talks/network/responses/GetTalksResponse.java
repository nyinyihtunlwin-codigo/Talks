package projects.nyinyihtunlwin.talks.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import projects.nyinyihtunlwin.talks.data.vo.DateVO;
import projects.nyinyihtunlwin.talks.data.vo.TalksVO;

/**
 * Created by Dell on 1/25/2018.
 */

public class GetTalksResponse {


    @SerializedName("page")
    private String page;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("dates")
    private DateVO date;

    @SerializedName("results")
    private List<TalksVO> talksVOList;

    public String getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public DateVO getDate() {
        return date;
    }

    public List<TalksVO> getTalksVOList() {
        return talksVOList;
    }
}
