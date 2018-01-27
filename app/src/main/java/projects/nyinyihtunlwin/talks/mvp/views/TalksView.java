package projects.nyinyihtunlwin.talks.mvp.views;

import java.util.List;

import projects.nyinyihtunlwin.talks.data.vo.TalksVO;

/**
 * Created by Dell on 1/26/2018.
 */

public interface TalksView {
    void displayTalksList(List<TalksVO> talksList);
    void showLoding();
}
