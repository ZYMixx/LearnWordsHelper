package com.example.learnwordshelper.domain.usecase.wordlearn

import com.example.learnwordshelper.domain.WordLearnRepository

class GetAllWordLearnByGroupNameUseCase (val repository: WordLearnRepository) {

    suspend operator fun invoke (groupName : String) = repository.getAllWordLearnByGroupName(groupName)

}