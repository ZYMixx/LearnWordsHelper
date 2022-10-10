package com.example.learnwordshelper.domain.usecase.wordlearn

import com.example.learnwordshelper.domain.WordLearn
import com.example.learnwordshelper.domain.WordLearnRepository

class DeleteWordLearnUseCase (private val repository: WordLearnRepository) {

    operator fun invoke (wordLearn : WordLearn) = repository.deleteWordLearn(wordLearn)


}