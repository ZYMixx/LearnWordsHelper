package com.example.learnwordshelper.domain

import androidx.lifecycle.LiveData

interface WordLearnRepository {

    fun getWordLearnById(id : Int) : LiveData<WordLearn>

    fun getWordLearnByName(word : String) : LiveData<WordLearn>

    fun getListWordLearn() : LiveData<List<WordLearn>>

    suspend fun editWordLearn(wordLearn: WordLearn)

    fun deleteWordLearn(wordLearn: WordLearn)

    suspend fun addWordLearn(wordLearn: WordLearn)


}