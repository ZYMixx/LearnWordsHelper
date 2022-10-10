package com.example.learnwordshelper.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.learnwordshelper.data.database.AppDatabase
import com.example.learnwordshelper.data.maper.WordLearnMapper
import com.example.learnwordshelper.domain.WordLearn
import com.example.learnwordshelper.domain.WordLearnRepository

class WordLearnRepositoryImpl(
    private val application: Application
    ) : WordLearnRepository {


    private val wordLearnDao = AppDatabase.getInstance(application).wordLearnDao()
    private val mapper = WordLearnMapper()

    override fun getWordLearnById(id : Int): LiveData<WordLearn> {
        return Transformations.map(wordLearnDao.getWordLearn(id)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun getWordLearnByName(word: String): LiveData<WordLearn> {
        return Transformations.map(wordLearnDao.getWordLearn(word)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun getListWordLearn(): LiveData<List<WordLearn>> {
        return Transformations.map(wordLearnDao.getWordLearnList()) {
            it.map{
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override suspend fun editWordLearn(wordLearn: WordLearn) {
        wordLearnDao.editWordLearn(mapper.entityToDbModel(wordLearn))
    }

    override fun deleteWordLearn(wordLearn: WordLearn) {
        wordLearnDao.deleteWordLearn(mapper.entityToDbModel(wordLearn))
    }

    override suspend fun addWordLearn(wordLearn: WordLearn) {
        wordLearnDao.addLearnWord(mapper.entityToDbModel(wordLearn))
    }

}