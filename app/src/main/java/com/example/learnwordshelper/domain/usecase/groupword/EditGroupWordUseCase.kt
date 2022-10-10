package com.example.learnwordshelper.domain.usecase.groupword

import com.example.learnwordshelper.domain.GroupWord
import com.example.learnwordshelper.domain.GroupWordRepository

class EditGroupWordUseCase (private val repository : GroupWordRepository) {

    suspend operator fun invoke (groupWord : GroupWord) = repository.editGroupWord(groupWord)

}