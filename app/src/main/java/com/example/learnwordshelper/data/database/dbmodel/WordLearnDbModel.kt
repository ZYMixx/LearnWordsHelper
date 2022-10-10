package com.example.learnwordshelper.data.database.dbmodel

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity (tableName = "full_word_learn_list")
data class WordLearnDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val word : String,
    val groupName: String = "defaultGroup",
    val allTryLearn: Int = 0,
    val successLearn : Int = 0,
    val lastLearnTime : Long = 0,
    val difficultWordPos : Int = 0
) {
}