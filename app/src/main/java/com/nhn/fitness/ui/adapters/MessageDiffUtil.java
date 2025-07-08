package com.nhn.fitness.ui.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.nhn.fitness.data.model.Message;

public class MessageDiffUtil extends DiffUtil.ItemCallback<Message> {

   @Override
   public boolean areItemsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
      return oldItem.getId() == newItem.getId();
   }

   @Override
   public boolean areContentsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
      return oldItem.getId() == newItem.getId();
   }
}
