package com.example.learnwordshelper.domain.usecase.wordlearn

import com.example.learnwordshelper.domain.WordLearn
import com.example.learnwordshelper.domain.WordLearnRepository

class GetWordLearnUseCase (private val repository: WordLearnRepository) {

    operator fun invoke (id : Int) = repository.getWordLearnById(id)

    operator fun invoke (word : String) = repository.getWordLearnByName(word)

}