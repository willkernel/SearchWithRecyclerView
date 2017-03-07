package com.willkernel.app.searchwithrecycler.model.http.api;

import com.willkernel.app.searchwithrecycler.model.http.response.JSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by willkernel on 2017/3/7.
 * mail:willkerneljc@gmail.com
 */

public interface RequestInterface {
    @GET("android/jsonandroid")
    Call<JSONResponse> getJSON();
}