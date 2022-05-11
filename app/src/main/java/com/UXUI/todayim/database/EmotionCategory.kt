package com.UXUI.todayim.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class EmotionCategory(
    var diaryIdx: Int,
    var categoryName: String
){
    @PrimaryKey(autoGenerate = true)
    var categoryIdx: Int = 0
}
