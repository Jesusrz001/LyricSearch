package com.jesus.lyricsearch.ui.lyric;

import android.support.annotation.NonNull;
import com.jesus.lyricsearch.models.LyricsResult;
import com.jesus.lyricsearch.models.Track;
import com.jesus.lyricsearch.networking.LyricService;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LyricActivityPresenter implements LyricActivityContract.UserActions{
    private final LyricActivityContract.View view;
    private final LyricService service;
    private Track track;

    public LyricActivityPresenter(@NonNull LyricActivityContract.View view, Track track) {
        this.view = view;
        this.service = new LyricService();
        this.track = track;
    }

    @Override public void initialize() {
        view.loadInitialViews(track);
        loadLyrics();
    }

    private void loadLyrics() {
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put("action", "lyrics");
        queryParam.put("artist", track.getArtistName());
        queryParam.put("song", track.getTrackName());
        queryParam.put("fmt", "xml");
        queryParam.put("func", "getSong");

        //given more time I would separate this out some more using a repository pattern or Dagger
        //in order to stub the network calls
        service.getAPI().getLyrics(queryParam).enqueue(new Callback<LyricsResult>() {
            @Override
            public void onResponse(Call<LyricsResult> call, Response<LyricsResult> response) {
                LyricsResult lyrics = response.body();
                if(lyrics.getLyrics().equalsIgnoreCase("Not found")){
                    //given more time I would go ahead and create a linkify textview to open
                    //the wikia url given in the response
                    view.showNoLyricsMessage(lyrics);
                }else{
                    //Since the API only returns 15% of the lyrics due to copyright restrictions
                    //I would go ahead and linkify the [...] with the url given in the response
                    view.loadLyrics(lyrics);
                }

            }

            @Override public void onFailure(Call<LyricsResult> call, Throwable t) {
                view.showError();
            }
        });
    }
}
