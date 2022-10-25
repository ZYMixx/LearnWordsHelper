package com.example.learnwordshelper.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.learnwordshelper.data.database.dbmodel.GroupWordDbModel
import com.example.learnwordshelper.data.database.dbmodel.WordLearnDbModel

@Database(entities = [WordLearnDbModel::class, GroupWordDbModel::class], version = 12)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun wordLearnDao(): WordLearnDao
    abstract fun groupWordDao(): GroupWordDao

}