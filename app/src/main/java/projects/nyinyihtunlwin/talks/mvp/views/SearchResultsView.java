package projects.nyinyihtunlwin.talks.mvp.views;

import java.util.List;

import projects.nyinyihtunlwin.talks.data.vo.SearchResultVO;

/**
 * Created by Dell on 1/26/2018.
 */

public interface SearchResultsView {
    void displaySearchResults(List<SearchResultVO> searchResultVOList);
}
