package com.example.learnwordshelper.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "full_word_learn_list")
data class WordLearnDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val word : String
) {
}