package com.UXUI.todayim.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Diary(
    var diaryComment: String= "내용 없음", // 일기 및 Comment
    var diaryDate: String= "2000-00-00"    // 일기 작성 일자
) {
    @PrimaryKey(autoGenerate = true)
    var diaryIdx: Int = 0
}

@Entity
data class DiaryEmotionCategory(
    var adjectiveCategoryIdx: Int = -1,
    var adjectiveCategoryName: String = "",
    var diaryIdx: Int = -1
){
    @PrimaryKey(autoGenerate = true)
    var diaryCategoryIdx: Int = 0
}

@Entity
data class DiaryEmotionDetail(
    var adjectiveCategoryIdx: Int = -1,
    var adjective: String = "",
    var diaryIdx: Int = -1
){
    @PrimaryKey(autoGenerate = true)
    var diaryEmotionIdx: Int = 0
}
