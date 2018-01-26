package projects.nyinyihtunlwin.talks.network;

import io.reactivex.Observable;
import projects.nyinyihtunlwin.talks.network.responses.GetPlaylistsResponse;
import projects.nyinyihtunlwin.talks.network.responses.GetPodcastsResponse;
import projects.nyinyihtunlwin.talks.network.responses.GetSearchResponse;
import projects.nyinyihtunlwin.talks.network.responses.GetTalksResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Dell on 1/25/2018.
 */

public interface TalksApi {

    @POST("getTedTalks.php")
    @FormUrlEncoded
    Observable<GetTalksResponse> getTedTalkList(@Field("page") int page, @Field("access_token") String accessToken);

    @POST("getPlayLists.php")
    @FormUrlEncoded
    Observable<GetPlaylistsResponse> getPlaylists(@Field("page") int page, @Field("access_token") String accessToken);

    @POST("getPodcasts.php")
    @FormUrlEncoded
    Observable<GetPodcastsResponse> getPodcasts(@Field("page") int page, @Field("access_token") String accessToken);


    @POST("/search.php")
    @FormUrlEncoded
    Observable<GetSearchResponse> getSearchResults(@Field("page") int page, @Field("access_token") String accessToken);


}
