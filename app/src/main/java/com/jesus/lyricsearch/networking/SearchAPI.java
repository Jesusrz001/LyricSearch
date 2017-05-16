package com.jesus.lyricsearch.networking;

import com.jesus.lyricsearch.models.TrackResults;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchAPI {
    @GET("/search") Call<TrackResults> getTrackList(
            @Query("term") String term
    );

}
