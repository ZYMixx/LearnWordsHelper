package com.example.learnwordshelper.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.learnwordshelper.data.repository.GroupWordRepositoryImpl
import com.example.learnwordshelper.data.repository.WordLearnRepositoryImpl
import com.example.learnwordshelper.domain.GroupWord
import com.example.learnwordshelper.domain.WordLearn
import com.example.learnwordshelper.domain.usecase.groupword.GetListGroupWordUseCase
import com.example.learnwordshelper.domain.usecase.wordlearn.AddWordLearnUseCase
import com.example.learnwordshelper.domain.usecase.wordlearn.EditWordLearnUseCase
import kotlinx.coroutines.launch

class AddWordFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repositoryWordLearn = WordLearnRepositoryImpl(application)

    private val addWordLearnUseCase = AddWordLearnUseCase(repositoryWordLearn)
    private val editWordLearnUseCase = EditWordLearnUseCase(repositoryWordLearn)

    private val _errorInputWord = MutableLiveData<Boolean>()
    val errorInputWord: LiveData<Boolean>
        get() = _errorInputWord

    private val _errorInputTranslate = MutableLiveData<Boolean>()
    val errorInputTranslate: LiveData<Boolean>
        get() = _errorInputTranslate

    fun addNewWordLearn(wordLearn: WordLearn) {
        viewModelScope.launch {
            addWordLearnUseCase.invoke(wordLearn)
        }
    }

    fun validateInput(word: String, translate: String): Boolean {
        //возможно добавить разные проверки с ввыводом текста ошибки
        var result = true
        if (word.isBlank()) {
            _errorInputWord.value = true
            result = false
        }
        if (translate.isBlank()) {
            _errorInputTranslate.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputWord() {
        _errorInputWord.value = false

    }

    fun resetErrorInputTranslate() {
        _errorInputTranslate.value = false

    }


}