package com.nhn.fitness.data.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.nhn.fitness.data.model.chat.ApiResponse;
import com.nhn.fitness.data.model.chat.Content;
import com.nhn.fitness.data.model.chat.MessageModel;
import com.nhn.fitness.data.model.chat.Part;
import com.nhn.fitness.service.GeminiApiService;
import com.nhn.fitness.service.RetrofitClient;

import java.util.Arrays;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeminiRepository {
    private final GeminiApiService apiService;
    private static final String API_KEY = "AIzaSyDaEjxH66ehmrJqJRe0-RzZ6eNHCEz2gd8";

    public GeminiRepository() {
        apiService = RetrofitClient.getInstance().create(GeminiApiService.class);
    }

    public void getAIResponse(String messageText, MutableLiveData<String> aiResponseLiveData) {
        Part part = new Part(messageText);
        Content content = new Content(Collections.singletonList(part));
        MessageModel messageModel = new MessageModel(Collections.singletonList(content));

        apiService.generateContent(API_KEY, messageModel).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String reply = response.body().getCandidates().get(0).getContent().getParts().get(0).getText();
                    aiResponseLiveData.postValue(reply);
                } else {
                    aiResponseLiveData.postValue("Lỗi: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                aiResponseLiveData.postValue("Lỗi kết nối: " + t.getMessage());
            }
        });
    }
}
