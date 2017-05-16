package com.jesus.lyricsearch.networking;

import com.jesus.lyricsearch.models.LyricsResult;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface LyricAPI {
    @GET("/api.php") Call<LyricsResult> getLyrics(
            @QueryMap Map<String, String> options
    );
}
