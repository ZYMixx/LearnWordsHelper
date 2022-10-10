package com.example.learnwordshelper.domain.usecase.groupword

import com.example.learnwordshelper.domain.GroupWordRepository

class GetGroupWordUseCase(private val repository : GroupWordRepository,) {


    operator fun invoke (id : Int) = repository.getGroupWordById(id)

    operator fun invoke (name : String) = repository.getGroupWordByName(name)
}