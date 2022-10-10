package com.example.learnwordshelper.presentation.adapter

import android.content.Context
import android.graphics.Color
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.learnwordshelper.databinding.GroupWordCloseLayoutBinding
import com.example.learnwordshelper.databinding.GroupWordOpenLayoutBinding
import com.example.learnwordshelper.domain.GroupWord
import com.example.learnwordshelper.domain.WordLearn

class WordLearnListAdapter(private val context: Context)
    : ListAdapter<GroupWord, GroupWordViewHolder>(GroupWordDiffCallBack){

    var onGroupClickListener: ((GroupWord) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupWordViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutBinding = when(viewType){
            VIEW_TYPE_OPEN -> GroupWordOpenLayoutBinding.inflate(layoutInflater, parent, false)
            VIEW_TYPE_CLOSE -> GroupWordCloseLayoutBinding.inflate(layoutInflater, parent, false)
            else -> throw RuntimeException("Unknown view type $viewType")
        }

        return GroupWordViewHolder(layoutBinding)
    }



    override fun onBindViewHolder(holder: GroupWordViewHolder, position: Int) {
        holder.groupViewBinding.root.setOnClickListener{
            onGroupClickListener?.invoke(this.getItem(position))
            Log.d("CLICK_TAG", "onBindViewHolder: CLICK")
        }
        if (getItemViewType(position) == VIEW_TYPE_CLOSE){
            setUpCloseGroupView(
                holder.groupViewBinding as GroupWordCloseLayoutBinding,
                getItem(position)
            )
        }
        if (getItemViewType(position) == VIEW_TYPE_OPEN){
            setUpOpenGroupView(
                holder.groupViewBinding as GroupWordOpenLayoutBinding,
                getItem(position)
            )
        }
        if (holder.groupViewBinding.javaClass == (GroupWordCloseLayoutBinding::class) ) {


        }
    }

    private fun setUpCloseGroupView(binding : GroupWordCloseLayoutBinding, groupWord: GroupWord){
        with(binding){
            tvGroupName.text = "NameGroup"
            tvGroupDescription.text = "there Description of yours group with words"
            tvStatsName1.text ="words"
            tvStatsName2.text ="lvl learn"
            tvStatsName3.text ="last learn"
            tvStatsName4.text ="ishue"
            tvStatsName4.setTextColor(Color.RED)

/*            tvStatsValue1.text = groupWord.
            tvStatsValue2.text = ""
            tvStatsValue3.text = ""
            tvStatsValue4.text = ""*/
        }
    }

    private fun setUpOpenGroupView(binding : GroupWordOpenLayoutBinding, groupWord: GroupWord){

    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).openStatus){
            true -> return  VIEW_TYPE_OPEN
            false -> return VIEW_TYPE_CLOSE
        }
    }

    companion object {
        const val VIEW_TYPE_CLOSE = 100
        const val VIEW_TYPE_OPEN = 101
        const val MAX_POO_SIZE = 10
    }

    interface OnGroupClickListener {
        fun onGroupClick(groupWord: GroupWord)
    }

}