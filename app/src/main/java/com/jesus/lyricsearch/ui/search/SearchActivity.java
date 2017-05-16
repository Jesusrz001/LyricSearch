package com.jesus.lyricsearch.ui.search;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.google.gson.Gson;
import com.jesus.lyricsearch.R;
import com.jesus.lyricsearch.databinding.SearchViewBinding;
import com.jesus.lyricsearch.models.Track;
import com.jesus.lyricsearch.models.TrackResults;
import com.jesus.lyricsearch.networking.EspressoIdlingResource;
import com.jesus.lyricsearch.ui.lyric.LyricActivity;

public class SearchActivity extends AppCompatActivity
        implements SearchContract.View, TrackClickListener {
    //if time allowed I would use ButterKnife
    private SearchViewBinding binding;
    private SearchContract.UserActions actionsListener;
    private TrackResultsAdapter trackAdapter;

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionsListener = new SearchPresenter(this);
        binding = DataBindingUtil.setContentView(this, R.layout.search_view);
        setupViews();
    }

    private void setupViews() {
        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                actionsListener.search(binding.searchEdTxt.getText().toString());
            }
        });

        binding.searchEdTxt.setText("");
        binding.resultList.setVisibility(View.INVISIBLE);
        binding.progressBar.setVisibility(View.GONE);
        binding.errorTxt.setVisibility(View.GONE);

        //RecyclerView
        binding.resultList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.resultList.setLayoutManager(layoutManager);
        trackAdapter = new TrackResultsAdapter(getApplicationContext(), this);
        binding.resultList.setAdapter(trackAdapter);
    }

    @Override public void setProgressIndicator(boolean active) {
        if (active) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override public void showErrorMessage() {
        binding.errorTxt.setVisibility(View.VISIBLE);
    }

    @Override public void hideErrorMessage() {
        binding.errorTxt.setVisibility(View.GONE);
    }

    @Override public void showTrackResults(@NonNull TrackResults results) {
        if (results != null) {
            trackAdapter.setData(results.getTracks());
            binding.resultList.scrollToPosition(0);
        }
    }

    @Override public void setTrackListVisibility(boolean active) {
        if (active) {
            binding.resultList.setVisibility(View.VISIBLE);
        } else {
            binding.resultList.setVisibility(View.INVISIBLE);
        }
    }

    @Override public void onTrackItemClicked(Track track) {
        Intent intent = new Intent(this, LyricActivity.class);
        intent.putExtra("track", new Gson().toJson(track));
        startActivity(intent);
    }

    @VisibleForTesting public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
