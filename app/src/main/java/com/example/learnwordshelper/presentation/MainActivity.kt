package com.example.learnwordshelper.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.learnwordshelper.R
import com.example.learnwordshelper.data.AppDatabase
import com.example.learnwordshelper.data.WordLearnDbModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scope.launch {
            AppDatabase.getInstance(applicationContext).wordLearnDao().addLearnWord(
                WordLearnDbModel(0, "TheardWord")
            )
        }

    }
}