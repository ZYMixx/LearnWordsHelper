package com.example.learnwordshelper.domain

data class WordLearn(
    val id : Int = 0,
    val word : String,
    val translation : String,
    val groupName: String = "defaultGroup",
    val allTryLearn: Int = 0,
    val successLearn : Int = 0,
    val lastLearnTime : Long = 0,
    val difficultWordPos : Int = 0
    ) {
}