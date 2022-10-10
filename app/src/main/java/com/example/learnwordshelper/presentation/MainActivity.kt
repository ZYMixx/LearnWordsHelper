package com.example.learnwordshelper.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.learnwordshelper.databinding.ActivityMainBinding
import com.example.learnwordshelper.domain.GroupWord
import com.example.learnwordshelper.presentation.adapter.WordLearnListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainScreenViewModel
    private lateinit var rvAdapter : WordLearnListAdapter
    private val scope = CoroutineScope(Dispatchers.IO)
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpRV()
        setUpViewModel()

    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)[MainScreenViewModel::class.java]
        viewModel.groupWordListLD.observe(this){
            rvAdapter.submitList(it)
        }
    }

    private fun setUpRV() {
        rvAdapter = WordLearnListAdapter(this)
        binding.mainRv.adapter = rvAdapter
        rvAdapter.onGroupClickListener = {
            viewModel.changeOpenStatus(it)
        }

    }
}