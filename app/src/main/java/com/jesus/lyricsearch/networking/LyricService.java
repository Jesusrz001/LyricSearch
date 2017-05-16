package com.jesus.lyricsearch.networking;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

//Service for Lyric API
//There are two different service classes as they hit two entirely different API's
public class LyricService {
    private static String BASE_URL = "http://lyrics.wikia.com";

    public LyricAPI getAPI(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        /*
         * Initially tried using json fmt given by the api docs but the format was not correct
         * Tried formatting the string response but in the end I decided to just use the xml
         * converter factory with retrofit and okhttp
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(LyricAPI.class);
    }
}
