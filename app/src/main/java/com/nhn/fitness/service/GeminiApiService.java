package com.nhn.fitness.service;

import com.nhn.fitness.data.model.chat.ApiResponse;
import com.nhn.fitness.data.model.chat.MessageModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GeminiApiService {
    @Headers("Content-Type: application/json")
    @POST("v1beta/models/gemini-2.0-flash:generateContent")
    Call<ApiResponse> generateContent(@Query("key") String apiKey, @Body MessageModel request);
}
