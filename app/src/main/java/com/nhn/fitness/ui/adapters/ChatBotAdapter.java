package com.nhn.fitness.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.nhn.fitness.data.model.Message;
import com.nhn.fitness.databinding.ItemChatLeftTextBinding;
import com.nhn.fitness.databinding.ItemChatRightTextBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatBotAdapter extends ListAdapter<Message, RecyclerView.ViewHolder> {

    public static final int TYPE_TEXT_RIGHT = 0;
    public static final int TYPE_TEXT_LEFT = 1;

    public ChatBotAdapter() {
        super(new MessageDiffUtil());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_TEXT_RIGHT) {
            ItemChatRightTextBinding bindingRight = ItemChatRightTextBinding.inflate(inflater, parent, false);
            return new ChatTextRightViewHolder(bindingRight);
        } else {
            ItemChatLeftTextBinding bindingLeft = ItemChatLeftTextBinding.inflate(inflater, parent, false);
            return new ChatTextLeftViewHolder(bindingLeft);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message item = getItem(position);
        if (holder instanceof ChatTextLeftViewHolder) {
            ((ChatTextLeftViewHolder) holder).bindView(item);
        } else if (holder instanceof ChatTextRightViewHolder) {
            ((ChatTextRightViewHolder) holder).bindView(item);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    @Override
    public int getItemCount() {
        return getCurrentList().size();
    }

    public static class ChatTextLeftViewHolder extends RecyclerView.ViewHolder {
        private final ItemChatLeftTextBinding binding;

        public ChatTextLeftViewHolder(ItemChatLeftTextBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindView(Message message) {
            if (message.getMessage() != null) {
                binding.tvContentLeft.setVisibility(View.VISIBLE);
                binding.tvTimeLeft.setVisibility(View.VISIBLE);
                binding.lottieTyping.setVisibility(View.GONE);

                binding.tvContentLeft.setText(message.getMessage());
                binding.tvTimeLeft.setText(message.getTime());
            } else {
                binding.lottieTyping.setVisibility(View.VISIBLE);
                binding.tvContentLeft.setVisibility(View.GONE);
                binding.tvTimeLeft.setVisibility(View.GONE);
            }
        }
    }

    public static class ChatTextRightViewHolder extends RecyclerView.ViewHolder {
        private final ItemChatRightTextBinding binding;

        public ChatTextRightViewHolder(ItemChatRightTextBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindView(Message message) {
            binding.tvContentRight.setText(message.getMessage() != null ? message.getMessage() : "");
            binding.tvTimeRight.setText(message.getTime());
        }
    }

    public void setData(List<Message> listMessage) {
        submitList(listMessage);
    }

    public void addData(Message message) {
        List<Message> currentList = new ArrayList<>(getCurrentList());
        currentList.add(message);
        submitList(currentList);
    }
}

