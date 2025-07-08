package com.nhn.fitness.ui.adapters.chat

import androidx.recyclerview.widget.DiffUtil
import com.nhn.fitness.data.model.Message

class MessageDiffUtil: DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.id == newItem.id
    }
}