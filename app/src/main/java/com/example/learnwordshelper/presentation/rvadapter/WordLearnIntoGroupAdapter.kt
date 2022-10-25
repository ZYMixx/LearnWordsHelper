package com.example.learnwordshelper.presentation.rvadapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.learnwordshelper.databinding.WordIntoGroupLayoutBinding
import com.example.learnwordshelper.domain.GroupWord
import com.example.learnwordshelper.domain.WordLearn

class WordLearnIntoGroupAdapter() :
    ListAdapter<WordLearn, WordLearnIntoGroupAdapter.WordLearnViewHolder>(WordLearnDiffCallBack) {

    private val TAG = "WORD_ADAPTER_TAG"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordLearnViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var layoutBinding = WordIntoGroupLayoutBinding.inflate(layoutInflater, parent, false)
        Log.d(TAG, "onCreateViewHolder: ${itemCount}")
        return WordLearnViewHolder(layoutBinding)
    }

    override fun onBindViewHolder(holder: WordLearnViewHolder, position: Int) {
        holder.binding.tvWord.text = getItem(position).word
        holder.binding.tvTranslate.text = getItem(position).translation
        Log.d(TAG, "onBindViewHolder: ")
    }

    class WordLearnViewHolder(var binding: WordIntoGroupLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    private object WordLearnDiffCallBack : DiffUtil.ItemCallback<WordLearn>() {

        override fun areItemsTheSame(oldItem: WordLearn, newItem: WordLearn): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WordLearn, newItem: WordLearn): Boolean {
            return oldItem == newItem
        }
    }


}