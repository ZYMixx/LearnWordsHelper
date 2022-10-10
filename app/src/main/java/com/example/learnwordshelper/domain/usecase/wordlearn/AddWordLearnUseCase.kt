package com.example.learnwordshelper.domain.usecase.wordlearn

import com.example.learnwordshelper.domain.WordLearn
import com.example.learnwordshelper.domain.WordLearnRepository

class AddWordLearnUseCase (private val repository: WordLearnRepository) {

    suspend operator fun invoke (wordLearn : WordLearn) = repository.addWordLearn(wordLearn)

}