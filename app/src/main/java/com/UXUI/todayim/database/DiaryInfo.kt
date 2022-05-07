package com.UXUI.todayim.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DiaryInfo(
    var diaryComment: String, // 일기 및 Comment
    var diaryDate: String,    // 일기 작성 일자
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}