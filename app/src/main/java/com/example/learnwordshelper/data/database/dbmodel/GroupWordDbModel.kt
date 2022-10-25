package com.example.learnwordshelper.data.database.dbmodel

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.learnwordshelper.domain.WordLearn

@Entity(tableName = "group_word_list")
data class GroupWordDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description : String,
    var openStatus: Boolean,
    val allTryLearn: Int = 0,
    val successLearn: Int = 0,
    val lastLearnTime: Long = 0,
    val difficultWordPos: Int = 0,
    val size: Int = 0
) {
}

