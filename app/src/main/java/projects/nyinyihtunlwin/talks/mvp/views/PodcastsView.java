package projects.nyinyihtunlwin.talks.mvp.views;

import java.util.List;

import projects.nyinyihtunlwin.talks.data.vo.PodcastsVO;

/**
 * Created by Dell on 1/26/2018.
 */

public interface PodcastsView {
    void displayPodcasts(List<PodcastsVO> podcastsVOList);
}
