package com.example.learnwordshelper.domain.usecase.groupword

import com.example.learnwordshelper.domain.GroupWordRepository

class GetListGroupWordUseCase(private val repository : GroupWordRepository ) {

    operator fun invoke() = repository.getListGroupWord()

}