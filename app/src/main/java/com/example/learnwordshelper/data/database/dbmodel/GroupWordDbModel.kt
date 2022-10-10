package com.example.learnwordshelper.data.database.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName ="group_word_list")
data class GroupWordDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name : String,
    var openStatus : Boolean
) {
}

