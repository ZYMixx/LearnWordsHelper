package com.example.learnwordshelper.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.learnwordshelper.data.database.dbmodel.GroupWordDbModel

@Dao
interface GroupWordDao {

    @Query("SELECT * FROM group_word_list ORDER BY id")
    fun getGroupWordList(): LiveData<List<GroupWordDbModel>>

    @Query("SELECT * FROM group_word_list WHERE id = :id")
    fun getGroupWord(id: Int): LiveData<GroupWordDbModel>

    @Query("SELECT * FROM group_word_list WHERE name = :name")
    fun getGroupWord(name: String): LiveData<GroupWordDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGroupWord(groupWordDbModel: GroupWordDbModel)

    @Update()
    suspend fun editGroupWord(groupWordDbModel: GroupWordDbModel)

    @Delete()
    fun deleteGroupWord(groupWordDbModel: GroupWordDbModel)
}