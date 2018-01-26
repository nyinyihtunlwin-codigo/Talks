package projects.nyinyihtunlwin.talks.mvp.views;

import java.util.List;

import projects.nyinyihtunlwin.talks.data.vo.PlaylistsVO;

/**
 * Created by Dell on 1/26/2018.
 */

public interface PlaylistsView {
    void displayPlaylists(List<PlaylistsVO> playlistsVOList);
}
