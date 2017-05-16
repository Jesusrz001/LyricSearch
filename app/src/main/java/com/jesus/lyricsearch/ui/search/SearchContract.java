package com.jesus.lyricsearch.ui.search;

import com.jesus.lyricsearch.models.TrackResults;

public class SearchContract {
    interface View {
        void setProgressIndicator(boolean b);

        void showErrorMessage();

        void hideErrorMessage();

        void showTrackResults(TrackResults results);

        void setTrackListVisibility(boolean b);
    }
    interface UserActions {

        void search(String searchInfo);
    }
}
