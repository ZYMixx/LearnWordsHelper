package com.example.learnwordshelper.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.learnwordshelper.data.repository.GroupWordRepositoryImpl
import com.example.learnwordshelper.data.repository.WordLearnRepositoryImpl
import com.example.learnwordshelper.domain.GroupWord
import com.example.learnwordshelper.domain.usecase.groupword.AddGroupWordUseCase
import com.example.learnwordshelper.domain.usecase.groupword.EditGroupWordUseCase
import com.example.learnwordshelper.domain.usecase.groupword.GetGroupWordUseCase
import com.example.learnwordshelper.domain.usecase.groupword.GetListGroupWordUseCase
import kotlinx.coroutines.launch

class MainScreenViewModel(application: Application) : AndroidViewModel(application){

    private val repositoryWordLearn = WordLearnRepositoryImpl(application)
    private val repositoryGroupWord = GroupWordRepositoryImpl(application)

    private val getListGroupWordUseCase = GetListGroupWordUseCase(repositoryGroupWord)
    private val getGroupWordUseCase = GetGroupWordUseCase(repositoryGroupWord)
    private val addGroupWordUseCase = AddGroupWordUseCase(repositoryGroupWord)
    private val editGroupWord = EditGroupWordUseCase(repositoryGroupWord)



    var groupWordListLD : LiveData<List<GroupWord>> = getListGroupWordUseCase.invoke()

    init {
    }


    fun changeOpenStatus(groupWord: GroupWord) {
        viewModelScope.launch {
            val newGroup = groupWord.copy(openStatus = !groupWord.openStatus)
            editGroupWord.invoke(newGroup)
        }
    }

}