package projects.nyinyihtunlwin.talks.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import projects.nyinyihtunlwin.talks.data.vo.PlaylistsVO;

/**
 * Created by Dell on 1/25/2018.
 */

public class GetPlaylistsResponse extends BaseResponse {

    @SerializedName("ted_playlists")
    private List<PlaylistsVO> playlistsVOList;

    public List<PlaylistsVO> getPlaylistsVOList() {
        return playlistsVOList;
    }

}
