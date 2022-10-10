package com.example.learnwordshelper.data.maper

import com.example.learnwordshelper.data.database.dbmodel.GroupWordDbModel
import com.example.learnwordshelper.data.database.dbmodel.WordLearnDbModel
import com.example.learnwordshelper.domain.GroupWord
import com.example.learnwordshelper.domain.WordLearn

class WordLearnMapper {

    fun mapDbModelToEntity(dbModel: WordLearnDbModel) = WordLearn (
        id = dbModel.id,
        word = dbModel.word
    )

    fun mapDbModelToEntity(dbModel: GroupWordDbModel) = GroupWord(
        id = dbModel.id,
        name = dbModel.name,
        openStatus = dbModel.openStatus
    )

    fun entityToDbModel(entity: WordLearn) = WordLearnDbModel (
        id = entity.id,
        word = entity.word
    )

    fun entityToDbModel(entity: GroupWord) = GroupWordDbModel(
        id = entity.id,
        name = entity.name,
        openStatus = entity.openStatus
    )
}