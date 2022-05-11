package com.UXUI.todayim.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EmotionDetail(
    var categoryIdx: String,
    var emotionName: String
){
    @PrimaryKey(autoGenerate = true)
    var emotionIdx: Int = 0
}
