package com.nhn.fitness.ui.adapters.chat

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.protobuf.Internal
import com.nhn.fitness.data.model.Message
import com.nhn.fitness.databinding.ItemChatLeftTextBinding
import com.nhn.fitness.databinding.ItemChatRightTextBinding

class ChatBotAdapter: ListAdapter<Message, RecyclerView.ViewHolder>(MessageDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bindingLeft = ItemChatLeftTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val bindingRight = ItemChatRightTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return when (viewType) {
            TYPE_TEXT_RIGHT -> ChatTextRightViewHolder(bindingRight)
            else -> ChatTextLeftViewHolder(bindingLeft)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, ) {
        val item = getItem(position)
        when (holder) {
            is ChatTextLeftViewHolder -> holder.bindView(item)
            is ChatTextRightViewHolder -> holder.bindView(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    override fun getItemCount(): Int = currentList.size

    inner class ChatTextLeftViewHolder(val binding: ItemChatLeftTextBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(message: Message) {
            binding.apply {
                message.message?.let {
                    tvContentLeft.visibility = View.VISIBLE
                    tvTimeLeft.visibility = View.VISIBLE
                    lottieTyping.visibility = View.GONE

                    tvContentLeft.text = it
                    tvTimeLeft.text = message.time
                } ?: run {
                    lottieTyping.visibility = View.VISIBLE
                    tvContentLeft.visibility = View.GONE
                    tvTimeLeft.visibility = View.GONE
                }
            }
        }
    }

    inner class ChatTextRightViewHolder(val binding: ItemChatRightTextBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(message: Message) {
            binding.apply {
                tvContentRight.text = message.message ?: ""
                tvTimeRight.text = message.time
            }
        }
    }

    fun setData(listMessage: MutableList<Message>) {
        submitList(listMessage)
    }

    fun addData(message: Message) {
        val currentList = currentList.toMutableList()
        currentList.add(message)
        submitList(currentList)
    }

    companion object {
        const val TYPE_TEXT_RIGHT = 0
        const val TYPE_TEXT_LEFT = 1
    }
}