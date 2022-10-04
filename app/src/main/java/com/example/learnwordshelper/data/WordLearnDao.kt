package com.example.learnwordshelper.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.learnwordshelper.domain.WordLearn

@Dao
interface WordLearnDao {

    @Query("SELECT * FROM full_word_learn_list ORDER BY id")
    fun getWordLearnList(): LiveData<List<WordLearn>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLearnWord(wordLearnDbModel: WordLearnDbModel)

}