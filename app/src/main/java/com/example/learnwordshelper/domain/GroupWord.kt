package com.example.learnwordshelper.domain

import androidx.lifecycle.LiveData

data class GroupWord(
    val id: Int = 0,
    val name: String,
    val description : String,
    var openStatus: Boolean = false,
    val allTryLearn: Int = 0,
    val successLearn: Int = 0,
    val lastLearnTime: Long = 0,
    val difficultWordPos: Int = 0,
    var size: Int = 0
) {

    var wordList = mutableListOf<WordLearn>()


}