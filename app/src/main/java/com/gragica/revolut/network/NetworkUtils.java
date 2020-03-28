package com.gragica.revolut.network;

import com.gragica.revolut.Const;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class NetworkUtils {

    private static NetworkUtils instance = null;

    private Retrofit retrofit;


    private NetworkUtils(){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(Const.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client())
                    .build();
    }

    static NetworkUtils getInstance(){
        if (instance == null)
            instance = new NetworkUtils();
        return instance;
    }

    RevolutApi getRevolutApi(){
        return retrofit.create(RevolutApi.class);
    }

    private OkHttpClient client(){
        return new OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }
}
