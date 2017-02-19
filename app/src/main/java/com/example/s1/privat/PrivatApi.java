package com.example.s1.privat;

/**
 * Created by S1 on 2/18/2017.
 */
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PrivatApi {
    @GET("/p24api/exchange_rates")
    Call<PostModel> getData(@Query("json") String resourceName, @Query("date") String date);
}