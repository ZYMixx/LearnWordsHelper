package com.example.learnwordshelper.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.learnwordshelper.data.database.dbmodel.WordLearnDbModel

@Dao
interface WordLearnDao {

    @Query("SELECT * FROM full_word_learn_list ORDER BY id")
    fun getWordLearnList(): LiveData<List<WordLearnDbModel>>

    @Query("SELECT * FROM full_word_learn_list WHERE id = :id")
    fun getWordLearn(id: Int): LiveData<WordLearnDbModel>

    @Query("SELECT * FROM full_word_learn_list WHERE word = :word")
    fun getWordLearn(word: String): LiveData<WordLearnDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLearnWord(wordLearnDbModel: WordLearnDbModel)

    @Update()
    suspend fun editWordLearn(wordLearnDbModel: WordLearnDbModel)

    @Delete()
    fun deleteWordLearn(wordLearnDbModel: WordLearnDbModel)

}