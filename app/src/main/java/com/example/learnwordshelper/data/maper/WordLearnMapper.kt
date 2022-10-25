package com.example.learnwordshelper.data.maper

import com.example.learnwordshelper.data.database.dbmodel.GroupWordDbModel
import com.example.learnwordshelper.data.database.dbmodel.WordLearnDbModel
import com.example.learnwordshelper.domain.GroupWord
import com.example.learnwordshelper.domain.WordLearn

class WordLearnMapper {

    fun mapDbModelToEntity(dbModel: WordLearnDbModel) = WordLearn(
        id = dbModel.id,
        word = dbModel.word,
        translation = dbModel.translation,
        groupName = dbModel.groupName,
        allTryLearn = dbModel.allTryLearn,
        successLearn = dbModel.successLearn,
        lastLearnTime = dbModel.lastLearnTime,
        difficultWordPos = dbModel.difficultWordPos
    )

    fun mapDbModelToEntity(dbModel: GroupWordDbModel) = GroupWord(
        id = dbModel.id,
        name = dbModel.name,
        description = dbModel.description,
        openStatus = dbModel.openStatus,
        allTryLearn = dbModel.allTryLearn,
        successLearn = dbModel.successLearn,
        lastLearnTime = dbModel.lastLearnTime,
        difficultWordPos = dbModel.difficultWordPos,
        size = dbModel.size
    )

    fun entityToDbModel(entity: WordLearn) = WordLearnDbModel(
        id = entity.id,
        word = entity.word,
        translation = entity.translation,
        groupName = entity.groupName,
        allTryLearn = entity.allTryLearn,
        successLearn = entity.successLearn,
        lastLearnTime = entity.lastLearnTime,
        difficultWordPos = entity.difficultWordPos
    )

    fun entityToDbModel(entity: GroupWord) = GroupWordDbModel(
        id = entity.id,
        name = entity.name,
        description = entity.description,
        openStatus = entity.openStatus,
        allTryLearn = entity.allTryLearn,
        successLearn = entity.successLearn,
        lastLearnTime = entity.lastLearnTime,
        difficultWordPos = entity.difficultWordPos,
        size = entity.size
    )
}