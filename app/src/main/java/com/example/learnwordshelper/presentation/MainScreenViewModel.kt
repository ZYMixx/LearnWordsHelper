package com.example.learnwordshelper.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.learnwordshelper.data.repository.GroupWordRepositoryImpl
import com.example.learnwordshelper.data.repository.WordLearnRepositoryImpl
import com.example.learnwordshelper.domain.GroupWord
import com.example.learnwordshelper.domain.WordLearn
import com.example.learnwordshelper.domain.usecase.groupword.AddGroupWordUseCase
import com.example.learnwordshelper.domain.usecase.groupword.EditGroupWordUseCase
import com.example.learnwordshelper.domain.usecase.groupword.GetGroupWordUseCase
import com.example.learnwordshelper.domain.usecase.groupword.GetListGroupWordUseCase
import com.example.learnwordshelper.domain.usecase.wordlearn.*
import kotlinx.coroutines.launch

class MainScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val repositoryWordLearn = WordLearnRepositoryImpl(application)
    private val repositoryGroupWord = GroupWordRepositoryImpl(application)

    private val getListGroupWordUseCase = GetListGroupWordUseCase(repositoryGroupWord)
    private val getGroupWordUseCase = GetGroupWordUseCase(repositoryGroupWord)
    private val addGroupWordUseCase = AddGroupWordUseCase(repositoryGroupWord)
    private val editGroupWord = EditGroupWordUseCase(repositoryGroupWord)

    private val getWordLearnUseCase = GetWordLearnUseCase(repositoryWordLearn)
    private val getListWordLearnUseCase = GetListWordLearnUseCase(repositoryWordLearn)
    private val addWordLearnUseCase = AddWordLearnUseCase(repositoryWordLearn)
    private val editWordLearnUseCase = EditWordLearnUseCase(repositoryWordLearn)
    private val getAllWordLearnByGroupName = GetAllWordLearnByGroupNameUseCase(repositoryWordLearn)

    val wordLearnListLD: LiveData<List<WordLearn>> = getListWordLearnUseCase.invoke()
    val groupWordListLD: LiveData<List<GroupWord>> = getListGroupWordUseCase.invoke()
    var wordLD = getWordLearnUseCase.invoke(2)
    var wordListLD = getListWordLearnUseCase.invoke()

    val TAG = "MainScreenViewModel_TAG"

    fun allocateWordToGroup() {
        groupWordListLD.value?.let { gList ->
            for (groupWord in gList) {
                wordLearnListLD.value?.let { wList ->
                    for (word in wList) {
                        if (word.groupName == groupWord.name && !groupWord.wordList.contains(word)) {
                            groupWord.wordList.add(word)
                        }
                    }
                }
                updateDataGroupWord(groupWord)
            }
        }
    }

    private fun updateDataGroupWord(groupWord: GroupWord) {
        if (groupWord.size != groupWord.wordList.size) {
            val editGroup = groupWord.copy(size = groupWord.wordList.size)
            viewModelScope.launch {
                editGroupWord.invoke(editGroup)
            }
        }
    }

    fun changeOpenStatus(groupWord: GroupWord) {
        viewModelScope.launch {
            val newGroup = groupWord.copy(openStatus = !groupWord.openStatus)
            editGroupWord.invoke(newGroup)

        }
    }

    fun loadDefaultData() {
        viewModelScope.launch {
            addGroupWordUseCase.invoke(
                GroupWord(
                    name = "defaultGroup",
                    description = "words without a home, you can always find a place for them"
                )
            )
            addGroupWordUseCase.invoke(
                GroupWord(
                    name = "Programming",
                    description = "difficult words"
                )
            )
            addGroupWordUseCase.invoke(
                GroupWord(
                    name = "Popular",
                    description = "most using"
                )
            )
            addGroupWordUseCase.invoke(
                GroupWord(
                    name = "Future",
                    description = ""
                )
            )
            addWordLearnUseCase.invoke(WordLearn(word = "Autonomous", translation = "????????????????????"))
            addWordLearnUseCase.invoke(WordLearn(word = "Excite", translation = "????????????????????"))
            addWordLearnUseCase.invoke(WordLearn(word = "Relate", translation = "????????????????????"))
            addWordLearnUseCase.invoke(WordLearn(word = "Rage", translation = "??????????????????????"))
            addWordLearnUseCase.invoke(WordLearn(word = "Awareness", translation = "??????????????????????????????"))
            addWordLearnUseCase.invoke(WordLearn(word = "Portfolio", translation = "????????????????"))
            addWordLearnUseCase.invoke(WordLearn(word = "Theory", translation = "????????????"))
            addWordLearnUseCase.invoke(WordLearn(word = "Syllable", translation = "????????"))

            fastAddWordWithGroup("Constraint", "??????????????????????", "Programming")
            fastAddWordWithGroup("Wrap", "??????????????", "Programming")
            fastAddWordWithGroup("Margin", "????????", "Programming")
            fastAddWordWithGroup("Outline", "????????????", "Programming")
            fastAddWordWithGroup("Synchronize", "????????????????????????????????", "Programming")
            fastAddWordWithGroup("Invoke", "??????????????", "Programming")
            fastAddWordWithGroup("Edit", "??????????????????????????", "Programming")
            fastAddWordWithGroup("Backup", "??????????????????", "Programming")
            fastAddWordWithGroup("Enable", "??????????????????", "Programming")
            fastAddWordWithGroup("Query ", "????????????", "Programming")
            fastAddWordWithGroup("Validation", "????????????????", "Programming")
            fastAddWordWithGroup("Touch", "??????????????????????", "Programming")
            fastAddWordWithGroup("Scroll", "G??????????????????????", "Programming")
            fastAddWordWithGroup("Location", "??????????????????", "Programming")

            fastAddWordWithGroup("Description", "????????????????", "Popular")
            fastAddWordWithGroup("Spicy", "????????????", "Popular")
            fastAddWordWithGroup("Refuse", "????????????????????", "Popular")
        }
    }

    private suspend fun fastAddWordWithGroup(word: String, translation: String, group: String) {
        addWordLearnUseCase.invoke(
            WordLearn(
                word = word,
                translation = translation,
                groupName = group
            )
        )
    }

}