package com.example.learnwordshelper.presentation.rvadapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.learnwordshelper.databinding.GroupWordAddNewLayoutBinding
import com.example.learnwordshelper.databinding.GroupWordCloseLayoutBinding
import com.example.learnwordshelper.databinding.GroupWordOpenLayoutBinding
import com.example.learnwordshelper.domain.GroupWord
import kotlin.math.abs

class WordLearnListAdapter(context: Context) :
    ListAdapter<GroupWord, GroupWordViewHolder>(GroupWordDiffCallBack) {

    var onGroupClickListener: ((GroupWord, Int) -> Unit)? = null
    var onNewGroupAddClickListener: (() -> Unit)? = null
    var scrollInnerRVCallBack: ((MotionEvent) -> Unit)? = null
    val TAG = "WordLearnListAdapter_TAG"

    var firstTouchRV = true
    var firstTouchY = 0f
    var parentTouchDifferentY = 0f

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupWordViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutBinding = when (viewType) {
            VIEW_TYPE_OPEN -> GroupWordOpenLayoutBinding.inflate(layoutInflater, parent, false)
            VIEW_TYPE_CLOSE -> GroupWordCloseLayoutBinding.inflate(layoutInflater, parent, false)
            VIEW_TYPE_ADD_NEW -> GroupWordAddNewLayoutBinding.inflate(layoutInflater, parent, false)
            else -> throw RuntimeException("Unknown view type $viewType")
        }
        return GroupWordViewHolder(layoutBinding)
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun onBindViewHolder(holder: GroupWordViewHolder, position: Int) {
        holder.groupViewBinding.root.setOnClickListener {
            Log.d(TAG, "onBindViewHolder: ${it.elevation}")
            onGroupClickListener?.invoke(this.getItem(position), position)
        }
        if (getItemViewType(position) == VIEW_TYPE_CLOSE) {
            setUpCloseGroupView(
                holder.groupViewBinding as GroupWordCloseLayoutBinding,
                getItem(position)
            )
        }
        if (getItemViewType(position) == VIEW_TYPE_OPEN) {
            setUpOpenGroupView(
                holder.groupViewBinding as GroupWordOpenLayoutBinding,
                getItem(position)
            )
        }
        if (getItemViewType(position) == VIEW_TYPE_ADD_NEW) {
            setUpAddNewGroupView(
                holder.groupViewBinding as GroupWordAddNewLayoutBinding
            )
        }
    }

    private fun setUpCloseGroupView(binding: GroupWordCloseLayoutBinding, groupWord: GroupWord) {
        with(binding) {
            tvGroupName.text = groupWord.name
            tvGroupDescription.text = groupWord.description
            tvStatsName1.text = "words"
            tvStatsName2.text = "lvl learn"
            tvStatsName3.text = "last learn"
            tvStatsName4.text = "ishue"
            tvStatsName4.setTextColor(Color.RED)
            tvStatsValue1.text = groupWord.size.toString()
            tvStatsValue2.text = ""
            tvStatsValue3.text = ""
            tvStatsValue4.text = ""
        }
    }

    private fun setUpOpenGroupView(binding: GroupWordOpenLayoutBinding, groupWord: GroupWord) {

        with(binding) {
            var rvAdapter = WordLearnIntoGroupAdapter()
            wordsIntoGroupRv.adapter = rvAdapter
            rvAdapter.submitList(groupWord.wordList)
            tvGroupName.text = groupWord.name
            setUpSmoothTouchInnerRV(wordsIntoGroupRv)
        }
    }

    private fun setUpSmoothTouchInnerRV(wordsIntoGroupRv: RecyclerView) {
        var rvNewTouchY = 0f
        var rvOldTouchY = 0f
        var rvStartTouchY = 0f
        wordsIntoGroupRv.setOnTouchListener { rv, motionEvent ->
            rvNewTouchY = motionEvent.y
            if (abs(rvOldTouchY - rvNewTouchY) > 40) {
                rvStartTouchY = rvOldTouchY
            }
            rvOldTouchY = rvNewTouchY
            scrollInnerRVCallBack = { parentTouchEvent ->
                if (firstTouchRV) {
                    firstTouchY = parentTouchEvent.y
                    firstTouchRV = false
                }
                parentTouchDifferentY = firstTouchY - parentTouchEvent.y
                parentTouchEvent.setLocation(
                    parentTouchEvent.x,
                    rvStartTouchY - parentTouchDifferentY
                )
                rv.onTouchEvent(parentTouchEvent)
            }
            true
        }
    }


    private fun setUpAddNewGroupView(binding: GroupWordAddNewLayoutBinding) {
        with(binding) {
            root.setOnClickListener {
                onNewGroupAddClickListener?.invoke()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == itemCount - 1) {
            return VIEW_TYPE_ADD_NEW
        }
        return when (getItem(position).openStatus) {
            true -> return VIEW_TYPE_OPEN
            false -> return VIEW_TYPE_CLOSE
        }
    }

    companion object {
        const val VIEW_TYPE_CLOSE = 100
        const val VIEW_TYPE_OPEN = 101
        const val VIEW_TYPE_ADD_NEW = 102
    }

}