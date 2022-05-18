package com.UXUI.todayim.database

import androidx.room.*

@Dao // Data Access Object
interface EmotionAdjectiveDao {
    @Insert
    fun insertCategory(category: EmotionAdjectiveCategory)

    @Insert
    fun insertAdjective(adjective: EmotionAdjective)

    // ==== QUERY ===== //
    @Query("SELECT * FROM EmotionAdjective ORDER BY random() LIMIT 4")
    fun getRandom4Adjective(): List<EmotionAdjective>
}