package com.nhn.fitness.ui.activities;

import static com.nhn.fitness.ui.adapters.ChatBotAdapter.TYPE_TEXT_LEFT;
import static com.nhn.fitness.ui.adapters.ChatBotAdapter.TYPE_TEXT_RIGHT;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.nhn.fitness.R;
import com.nhn.fitness.data.model.Message;
import com.nhn.fitness.databinding.ActivityChatBotBinding;
import com.nhn.fitness.ui.activities.viewmodel.ChatBotViewModel;
import com.nhn.fitness.ui.adapters.ChatBotAdapter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ChatBotActivity extends AppCompatActivity {

    private ActivityChatBotBinding binding;
    private ChatBotViewModel viewModel;
    private ChatBotAdapter mBotAdapter;
    private final ArrayList<Message> listMessage = new ArrayList<>();
    private int idNumber = 0;   // position Adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBotBinding.inflate(getLayoutInflater());
        viewModel = new ChatBotViewModel();
        setContentView(binding.getRoot());
        initViews();
        eventClick();
        registerObserveData();
    }

    private void registerObserveData() {
        viewModel.getAIResponse().observe(this, response -> {
            listMessage.set(idNumber, new Message(idNumber, response, getCurrentTime(), TYPE_TEXT_LEFT));
            mBotAdapter.setData(listMessage);
            binding.rcvChatBot.scrollToPosition(listMessage.size() - 1);
        });
    }

    private void eventClick() {
        binding.icBack.setOnClickListener(mView -> finish());
        binding.layoutInput.btnSendMessage.setOnClickListener(mView -> actionSendMessage());
    }

    private void actionSendMessage() {
        Editable charEdt = binding.layoutInput.edtInputMessage.getText();
        if (charEdt != null) {
            String strMessage = charEdt.toString();
            idNumber++;
            listMessage.add(new Message(idNumber, strMessage, getCurrentTime(), TYPE_TEXT_RIGHT));
            idNumber++;
            listMessage.add(new Message(idNumber, null, getCurrentTime(), TYPE_TEXT_LEFT));
            mBotAdapter.setData(listMessage);
            binding.layoutInput.edtInputMessage.setText("");
            viewModel.fetchAIResponse(strMessage);
        } else Toast.makeText(this, "Bạn chưa hỏi", Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        listMessage.add(new Message(idNumber, getString(R.string.txt_hello_ai), "Just Now", TYPE_TEXT_LEFT));
        mBotAdapter = new ChatBotAdapter();
        LinearLayoutManager lnManager = new LinearLayoutManager(this);
        binding.rcvChatBot.setAdapter(mBotAdapter);
        binding.rcvChatBot.setLayoutManager(lnManager);
        binding.rcvChatBot.setHasFixedSize(true);
        mBotAdapter.setData(listMessage);
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
}