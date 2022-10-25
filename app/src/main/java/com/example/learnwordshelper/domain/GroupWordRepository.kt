package com.example.learnwordshelper.domain

import androidx.lifecycle.LiveData

interface GroupWordRepository {

    fun getGroupWordById(id: Int): LiveData<GroupWord>

    fun getGroupWordByName(name: String): LiveData<GroupWord>

    fun getListGroupWord(): LiveData<List<GroupWord>>

    suspend fun editGroupWord(groupWord: GroupWord)

    fun deleteGroupWord(groupWord: GroupWord)

    suspend fun addGroupWord(groupWord: GroupWord)

}