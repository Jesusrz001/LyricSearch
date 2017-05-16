package com.jesus.lyricsearch.ui.lyric;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.google.gson.Gson;
import com.jesus.lyricsearch.R;
import com.jesus.lyricsearch.databinding.LyricActivityBinding;
import com.jesus.lyricsearch.models.LyricsResult;
import com.jesus.lyricsearch.models.Track;
import com.squareup.picasso.Picasso;

public class LyricActivity extends AppCompatActivity implements LyricActivityContract.View{
    private LyricActivityBinding binding;

    private LyricActivityContract.UserActions actionsListener;

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.lyric_activity);

        Track track = new Gson().fromJson(getIntent().getStringExtra("track"), Track.class);
        actionsListener = new LyricActivityPresenter(this, track);
        actionsListener.initialize();
    }

    @Override public void loadInitialViews(Track track) {
        Picasso.with(this).load(track.getArtworkUrl100())
                .into(binding.album120Iv);
        binding.albumTitleTxt.setText(track.getCollectionName());
        binding.artistTitleTxt.setText(track.getArtistName());
        binding.trackTitleTxt.setText(track.getTrackName());
    }

    @Override public void loadLyrics(LyricsResult lyrics) {
        binding.lyricsTxt.setText(lyrics.getLyrics());
    }

    @Override public void showNoLyricsMessage(LyricsResult lyrics) {
        binding.lyricsTxt.setText(lyrics.getLyrics());
    }

    @Override public void showError() {
        binding.lyricsTxt.setText("Error getting Lyrics");
    }
}
