package com.example.learnwordshelper.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.learnwordshelper.data.database.AppDatabase
import com.example.learnwordshelper.data.maper.WordLearnMapper
import com.example.learnwordshelper.domain.GroupWord
import com.example.learnwordshelper.domain.GroupWordRepository

class GroupWordRepositoryImpl(application: Application) : GroupWordRepository {

    private val groupWordDao = AppDatabase.getInstance(application).groupWordDao()
    private val mapper = WordLearnMapper()

    override fun getGroupWordById(id: Int): LiveData<GroupWord> {
        return Transformations.map(groupWordDao.getGroupWord(id)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun getGroupWordByName(word: String): LiveData<GroupWord> {
        return Transformations.map(groupWordDao.getGroupWord(word)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun getListGroupWord(): LiveData<List<GroupWord>> {
        return Transformations.map(groupWordDao.getGroupWordList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override suspend fun editGroupWord(wordLearn: GroupWord) {
        groupWordDao.editGroupWord(mapper.entityToDbModel(wordLearn))
    }

    override fun deleteGroupWord(wordLearn: GroupWord) {
        groupWordDao.deleteGroupWord(mapper.entityToDbModel(wordLearn))
    }

    override suspend fun addGroupWord(wordLearn: GroupWord) {
        groupWordDao.addGroupWord(mapper.entityToDbModel(wordLearn))
    }
}