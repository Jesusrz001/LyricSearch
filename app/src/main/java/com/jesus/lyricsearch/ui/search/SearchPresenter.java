package com.jesus.lyricsearch.ui.search;

import android.support.annotation.NonNull;
import com.jesus.lyricsearch.models.TrackResults;
import com.jesus.lyricsearch.networking.EspressoIdlingResource;
import com.jesus.lyricsearch.networking.SearchService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchPresenter implements SearchContract.UserActions {
    private final SearchContract.View view;
    private final SearchService service;

    public SearchPresenter(@NonNull SearchContract.View view) {
        this.view = view;
        this.service = new SearchService();
    }

    @Override
    public void search(String searchTerm) {
        view.setProgressIndicator(true);
        view.hideErrorMessage();

        //network request will occur
        //lets espresso know
        EspressoIdlingResource.increment();

        //given more time I would seperate this out some more using a repository pattern or Dagger
        //in order to stub the network calls
        service.getAPI()
                .getTrackList(searchTerm)
                .enqueue(new Callback<TrackResults>() {
                    @Override public void onResponse(Call<TrackResults> call,
                            Response<TrackResults> response) {
                        EspressoIdlingResource.decrement();
                        TrackResults results = response.body();
                        if (results.getTracks().isEmpty()){
                            view.setTrackListVisibility(false);
                            view.setProgressIndicator(false);
                            view.showErrorMessage();
                        }else{
                            view.setProgressIndicator(false);
                            view.setTrackListVisibility(true);
                            view.showTrackResults(results);
                        }
                    }

                    @Override public void onFailure(Call<TrackResults> call, Throwable t) {
                        EspressoIdlingResource.decrement();
                        view.setProgressIndicator(false);
                        view.showErrorMessage();
                    }
                });
    }
}
