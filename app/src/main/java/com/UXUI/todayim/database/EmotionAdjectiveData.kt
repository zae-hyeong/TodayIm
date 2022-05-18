package com.UXUI.todayim.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EmotionAdjectiveCategory(
    var adjectiveCategoryName: String= "내용 없음"
) {
    @PrimaryKey(autoGenerate = true)
    var adjectiveCategoryIdx: Int = 0
}

@Entity
data class EmotionAdjective(
    var adjectiveCategoryIdx: Int,
    var adjectiveName: String
) {
    @PrimaryKey(autoGenerate = true)
    var adjectiveIdx: Int = 0
}