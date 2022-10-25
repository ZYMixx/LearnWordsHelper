package com.example.learnwordshelper.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.learnwordshelper.data.repository.GroupWordRepositoryImpl
import com.example.learnwordshelper.domain.GroupWord
import com.example.learnwordshelper.domain.usecase.groupword.AddGroupWordUseCase
import com.example.learnwordshelper.domain.usecase.groupword.EditGroupWordUseCase

import kotlinx.coroutines.launch

class AddGroupFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repositoryGroupWord = GroupWordRepositoryImpl(application)

    private val addGroupWordUseCase = AddGroupWordUseCase(repositoryGroupWord)
    private val editGroupWordUseCase = EditGroupWordUseCase(repositoryGroupWord)

    private val _errorInputGroupName = MutableLiveData<Boolean>()
    val errorInputGroupName: LiveData<Boolean>
        get() = _errorInputGroupName

    private val _errorInputGroupDescription = MutableLiveData<Boolean>()
    val errorInputGroupDescription: LiveData<Boolean>
        get() = _errorInputGroupDescription

    fun addNewGroupWord(groupWord: GroupWord) {
        viewModelScope.launch {
            addGroupWordUseCase.invoke(groupWord)
        }
    }

    fun validateInput(groupName: String, description: String): Boolean {
        //возможно добавить разные проверки с ввыводом текста ошибки
        var result = true
        if (groupName.isBlank()) {
            _errorInputGroupName.value = true
            result = false
        }

        if (description.isBlank()) {
            _errorInputGroupDescription.value = true
            result = false
        }

        return result
    }

    fun resetErrorInputGroup() {
        _errorInputGroupName.value = false

    }

    fun resetErrorInputGroupDescription() {
        _errorInputGroupDescription.value = false

    }


}