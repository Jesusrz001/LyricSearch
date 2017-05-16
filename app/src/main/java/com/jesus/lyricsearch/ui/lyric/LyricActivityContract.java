package com.jesus.lyricsearch.ui.lyric;

import com.jesus.lyricsearch.models.LyricsResult;
import com.jesus.lyricsearch.models.Track;

public interface LyricActivityContract {
    interface View {

        void loadInitialViews(Track track);

        void loadLyrics(LyricsResult lyrics);

        void showNoLyricsMessage(LyricsResult lyrics);

        void showError();
    }
    interface UserActions {

        void initialize();
    }
}
