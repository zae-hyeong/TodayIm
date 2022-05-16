package com.UXUI.todayim.database

data class EmotionDataSet(
    val categoryIdx: Int,
    val categoryName: String,
    val emotions: Array<Emotion>
)

data class Emotion(
    val emotionIdx: Int,
    val emotionName: String
)
