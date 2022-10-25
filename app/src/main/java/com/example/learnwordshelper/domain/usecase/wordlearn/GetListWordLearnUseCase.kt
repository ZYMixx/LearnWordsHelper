package com.example.learnwordshelper.domain.usecase.wordlearn

import com.example.learnwordshelper.domain.WordLearnRepository

class GetListWordLearnUseCase (private val repository: WordLearnRepository) {

    operator fun invoke () = repository.getListWordLearn()

}