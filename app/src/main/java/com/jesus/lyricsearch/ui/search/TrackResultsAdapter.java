package com.jesus.lyricsearch.ui.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jesus.lyricsearch.R;
import com.jesus.lyricsearch.models.Track;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class TrackResultsAdapter extends RecyclerView.Adapter<TrackResultsAdapter.ViewHolder> {
    private Context context;
    private TrackClickListener listener;
    private ArrayList<Track> trackList;

    public TrackResultsAdapter(Context context, TrackClickListener listener){
        this.context = context;
        this.listener = listener;
        this.trackList = new ArrayList<>();
    }

    public TrackResultsAdapter(Context context, TrackClickListener listener, ArrayList<Track> trackList){
        this.context = context;
        this.listener = listener;
        this.trackList = trackList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.track_item_view, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        //use picasso to load art image
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                listener.onTrackItemClicked(trackList.get(position));
            }
        });
        Picasso.with(context).load(trackList.get(position).getArtworkUrl100())
                .into(holder.albumIv);
        holder.artistTxt.setText(trackList.get(position).getArtistName());
        holder.trackTxt.setText(trackList.get(position).getTrackName());
        holder.albumTxt.setText(trackList.get(position).getCollectionName());
    }

    @Override public int getItemCount() {
        return trackList == null ? 0 : trackList.size();
    }

    private Context getContext(){
        return context;
    }

    public void setData(@NonNull ArrayList<Track> tracks){
        this.trackList = tracks;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {
        public TextView trackTxt, artistTxt, albumTxt;
        public RelativeLayout container;
        public ImageView albumIv;

        public ViewHolder(View view){
            super(view);
            container = (RelativeLayout) view.findViewById(R.id.track_container);
            trackTxt = (TextView) view.findViewById(R.id.track_txt);
            albumTxt = (TextView) view.findViewById(R.id.album_txt);
            artistTxt = (TextView) view.findViewById(R.id.artist_txt);
            albumIv = (ImageView) view.findViewById(R.id.album_iv);
        }

    }
}
