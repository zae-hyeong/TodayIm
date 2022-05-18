package com.UXUI.todayim

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.UXUI.todayim.base.BaseActivity
import com.UXUI.todayim.database.EmotionAdjective
import com.UXUI.todayim.database.EmotionAdjectiveCategory
import com.UXUI.todayim.database.EmotionAdjectiveDatabase
import com.UXUI.todayim.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity: BaseActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        Handler(Looper.getMainLooper()).postDelayed({
            //액티비티 이동
            startMainActivity()
        }, DURATION)

        addDummyAdjectives()

    }

    companion object {
        private const val DURATION : Long = 1500
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        finish()
    }

    private fun addDummyAdjectives() {
        val emotionAdjectiveDB = EmotionAdjectiveDatabase.getInstance(this)!!
        val categories = emotionAdjectiveDB.emotionAdjectiveDao().getAdjectiveCategories()
        val adjectives = emotionAdjectiveDB.emotionAdjectiveDao().getAdjectives()

        if (categories.isNotEmpty()) return

        addCategories(emotionAdjectiveDB)

        if ( adjectives.isNotEmpty() ) return

        add0Category(emotionAdjectiveDB)

        add1Category(emotionAdjectiveDB)

        add2Category(emotionAdjectiveDB)

        add3Category(emotionAdjectiveDB)

        add4Category(emotionAdjectiveDB)

        add5Category(emotionAdjectiveDB)

        add6Category(emotionAdjectiveDB)
    }

    private fun addCategories(emotionAdjectiveDB: EmotionAdjectiveDatabase) {
        emotionAdjectiveDB.emotionAdjectiveDao().insertCategory(
            EmotionAdjectiveCategory("편안함", 0)
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertCategory(
            EmotionAdjectiveCategory("기쁨", 1)
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertCategory(
            EmotionAdjectiveCategory("괴로움", 2)
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertCategory(
            EmotionAdjectiveCategory("슬픔", 3)
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertCategory(
            EmotionAdjectiveCategory("두려움", 4)
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertCategory(
            EmotionAdjectiveCategory("미움", 5)
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertCategory(
            EmotionAdjectiveCategory("화남", 6)
        )
    }

    private fun add6Category(emotionAdjectiveDB: EmotionAdjectiveDatabase) {
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(6, "죽이고 싶은")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(6, "피가 솟구치는")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(6, "미칠 것 같은")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(6, "때려부수고 싶은")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(6, "노여운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(6, "울화가 치미는")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(6, "열받는")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(6, "꽤심한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(6, "분한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(6, "욱하는")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(6, "성난")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(6, "화난")
        )
    }

    private fun add5Category(emotionAdjectiveDB: EmotionAdjectiveDatabase) {
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(5, "미운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(5, "복수하고 싶은")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(5, "증오하는")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(5, "경멸스러운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(5, "혐오스러운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(5, "역겨운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(5, "가증스러운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(5, "질투하는")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(5, "시기하는")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(5, "미운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(5, "싫은")
        )
    }

    private fun add4Category(emotionAdjectiveDB: EmotionAdjectiveDatabase) {
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(4, "끔찍한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(4, "죽을 것 같은")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(4, "얼어붙은")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(4, "섬뜩한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(4, "공포스러운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(4, "소름돋는")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(4, "살벌한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(4, "무서운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(4, "떨리는")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(4, "암담한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(4, "두려운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(4, "겁나는")
        )
    }

    private fun add3Category(emotionAdjectiveDB: EmotionAdjectiveDatabase) {
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(3, "죽고 싶은")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(3, "참담한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(3, "처참한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(3, "애터지는")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(3, "비통한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(3, "좌절스러운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(3, "애절한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(3, "비참한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(3, "한탄스러운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(3, "절망스러운")
        )
    }

    private fun add2Category(emotionAdjectiveDB: EmotionAdjectiveDatabase) {
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(2, "뼈저린")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(2, "모멸스러운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(2, "치욕스러운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(2, "굴욕스러운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(2, "한스러운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(2, "자괴감 드는")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(2, "고통스러운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(2, "망신스러운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(2, "열등한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(2, "집착하는")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(2, "괴로운")
        )
    }

    private fun add1Category(emotionAdjectiveDB: EmotionAdjectiveDatabase) {
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(1, "희열")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(1, "황홀한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(1, "짜릿한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(1, "짜릿한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(1, "열애하는")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(1, "통쾌한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(1, "신이 나는")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(1, "날아갈 듯한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(1, "사랑스러운")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(1, "희망찬")
        )
    }

    private fun add0Category(emotionAdjectiveDB: EmotionAdjectiveDatabase) {
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(0, "평온한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(0, "존중하는")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(0, "평안한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(0, "충만한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(0, "신뢰하는")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(0, "행복한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(0, "좋은")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(0, "온화한")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(0, "감사하는")
        )
        emotionAdjectiveDB.emotionAdjectiveDao().insertAdjective(
            EmotionAdjective(0, "자유로운")
        )
    }
}