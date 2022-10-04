package com.example.learnwordshelper.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.learnwordshelper.domain.GroupWord

object GroupWordDiffCallBack : DiffUtil.ItemCallback<GroupWord>() {

    override fun areItemsTheSame(oldItem: GroupWord, newItem: GroupWord): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: GroupWord, newItem: GroupWord): Boolean {
        TODO("Not yet implemented")
    }
}