package com.UXUI.todayim.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Diary::class, DiaryEmotionCategory::class, DiaryEmotionDetail::class], version = 1, exportSchema = false)
abstract class DiaryDatabase : RoomDatabase() {
    abstract fun diaryDao(): DiaryDao

    // 싱글톤 구현을 위해 companion object로 생성
    companion object {
        private var instance: DiaryDatabase? = null
        @Synchronized
        fun getInstance(context: Context): DiaryDatabase? {
            if (instance == null) {
                synchronized(DiaryDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DiaryDatabase::class.java,
                        "diary-database"
                    ).allowMainThreadQueries().build()
                }
            }
            return instance
        }
    }
}