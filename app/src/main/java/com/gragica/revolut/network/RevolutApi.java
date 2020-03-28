package com.gragica.revolut.network;


import com.gragica.revolut.entities.RateList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RevolutApi {

    @GET("/api/android/latest")
    Observable<RateList> getRates(@Query("base") String baseRate);

}
