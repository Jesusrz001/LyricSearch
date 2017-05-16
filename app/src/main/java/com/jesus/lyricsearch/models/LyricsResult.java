package com.jesus.lyricsearch.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "task")
public class LyricsResult {
    @Element(name = "song")
    private String song;

    @Element(name = "isOnTakedownList")
    private String isOnTakedownList;

    @Element(name = "page_id")
    private String page_id;

    @Element(name = "page_namespace")
    private String page_namespace;

    @Element(name = "artist")
    private String artist;

    @Element(name = "lyrics")
    private String lyrics;

    @Element(name = "url")
    private String url;

    public String getSong() {
        return song;
    }

    public String getIsOnTakedownList() {
        return isOnTakedownList;
    }

    public String getPage_id() {
        return page_id;
    }

    public String getPage_namespace() {
        return page_namespace;
    }

    public String getArtist() {
        return artist;
    }

    public String getLyrics() {
        return lyrics;
    }

    public String getUrl() {
        return url;
    }
}
