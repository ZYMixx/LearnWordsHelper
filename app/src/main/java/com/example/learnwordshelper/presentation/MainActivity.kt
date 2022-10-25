package com.example.learnwordshelper.presentation

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.learnwordshelper.R
import com.example.learnwordshelper.databinding.ActivityMainBinding
import com.example.learnwordshelper.domain.GroupWord
import com.example.learnwordshelper.presentation.rvadapter.WordLearnListAdapter
import com.example.learnwordshelper.presentation.utils.Animation.AnimationCircularReveal


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainScreenViewModel
    private lateinit var rvAdapter: WordLearnListAdapter
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val TAG = "MAIN_TAG"

    var scrollRVtoUpdateItem: (() -> Unit)? = null
    var onSaveFabClicked: (() -> Boolean)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpRV()
        setUpViewModel()
        setUpAddWordFab()
    }

    private fun setUpRV() {
        rvAdapter = WordLearnListAdapter(this)
        binding.mainRv.adapter = rvAdapter
        rvAdapter.onGroupClickListener = { group, position ->
            viewModel.changeOpenStatus(group)
            scrollRVtoUpdateItem = { binding.mainRv.smoothScrollToPosition(position) }
        }

        rvAdapter.onNewGroupAddClickListener = {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_main, AddGroupFragment.newInstance())
                .addToBackStack(null)
                .commit()
            binding.fabOpen.visibility = View.INVISIBLE
            setUpSaveAndCloseFab()
        }

        binding.mainRv.setOnTouchListener { mainRv, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                rvAdapter.scrollInnerRVCallBack = null
            }
            rvAdapter.scrollInnerRVCallBack?.invoke(motionEvent) ?: mainRv.onTouchEvent(motionEvent)
            rvAdapter.scrollInnerRVCallBack?.invoke(motionEvent) ?: mainRv.onTouchEvent(motionEvent)
            true
        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)[MainScreenViewModel::class.java]
        viewModel.wordLearnListLD.observe(this) {
            viewModel.allocateWordToGroup()
        }
        viewModel.groupWordListLD.observe(this) { groupList ->
            fillDataBaseIfEmpty(groupList)
            viewModel.allocateWordToGroup() //можно не все а только последнее.
            rvAdapter.submitList(groupList)
            scrollRVtoUpdateItem?.invoke()
        }
    }

    private fun setUpAddWordFab() {
        binding.fabOpen.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_main, AddWordFragment.newInstance())
                .addToBackStack(null)
                .commit()
            binding.fabOpen.visibility = View.INVISIBLE
            setUpSaveAndCloseFab()
        }
    }

    private fun setUpSaveAndCloseFab() {
        AnimationCircularReveal.createOpenAnim(binding.fabSave, 500).start()
        AnimationCircularReveal.createOpenAnim(binding.fabCancel, 500).start()
        AnimationCircularReveal.createOpenAnim(binding.fragmentContainerMain, 500).start()

        binding.fabSave.setOnClickListener {
            if (onSaveFabClicked?.invoke() == true) {
                hideSaveAndCloseFab()
            }
        }

        binding.fabCancel.setOnClickListener {
            hideSaveAndCloseFab()
        }
    }

    private fun hideSaveAndCloseFab() {
        hideKeyboard()
        supportFragmentManager.popBackStack()
        binding.fabOpen.visibility = View.VISIBLE
        AnimationCircularReveal.createCloseAnim(binding.fabSave, 300).start()
        AnimationCircularReveal.createCloseAnim(binding.fabCancel, 300).start()
        AnimationCircularReveal.createCloseAnim(binding.fragmentContainerMain, 300).start()

    }

    private fun hideKeyboard() {
        (this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(currentFocus?.windowToken, 0);
    }

    fun shadowMainScreenOn() {
        binding.shadowBgMain.visibility = View.VISIBLE
    }

    fun shadowMainScreenOff() {
        binding.shadowBgMain.visibility = View.INVISIBLE
    }

    private fun fillDataBaseIfEmpty(list: List<GroupWord>) {
        if (list.isEmpty()) {
            viewModel.loadDefaultData()// загружает РЫБУ в базу даных для тестов
        }
    }
}