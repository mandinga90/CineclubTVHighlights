package cineclub.tvhighlights.http;

import java.util.List;

import cineclub.tvhighlights.entities.TvHighlight;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface TVHighlightService {

    @Headers({
        "Content-Type: application/json;charset=utf-8",
        "Accept: application/json"
    })

    @GET("/rss/tv.json")
    Call<List<TvHighlight>> getTVHighlights();

}
