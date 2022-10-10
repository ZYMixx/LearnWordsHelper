package com.example.learnwordshelper.domain.usecase.groupword

import com.example.learnwordshelper.domain.GroupWord
import com.example.learnwordshelper.domain.GroupWordRepository

class DeleteGroupWordUseCase (private val repository : GroupWordRepository) {

    operator fun invoke (groupWord : GroupWord) = repository.deleteGroupWord(groupWord)

}