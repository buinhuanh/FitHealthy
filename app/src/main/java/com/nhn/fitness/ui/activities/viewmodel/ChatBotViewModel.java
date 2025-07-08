package com.nhn.fitness.ui.activities.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nhn.fitness.data.repositories.GeminiRepository;
import com.nhn.fitness.ui.base.BaseViewModel;

public class ChatBotViewModel extends BaseViewModel {
    private final GeminiRepository repository;
    private final MutableLiveData<String> aiResponse = new MutableLiveData<>();

    public ChatBotViewModel() {
        repository = new GeminiRepository();
    }

    public LiveData<String> getAIResponse() {
        return aiResponse;
    }

    public void fetchAIResponse(String message) {
        repository.getAIResponse(message, aiResponse);
    }
}
