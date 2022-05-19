package com.UXUI.todayim

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.UXUI.todayim.base.BaseActivity
import com.UXUI.todayim.database.EmotionAdjective
import com.UXUI.todayim.database.EmotionAdjectiveDatabase
import com.UXUI.todayim.databinding.ActivitySplashBinding
import com.UXUI.todayim.databinding.ActivityTestBinding
import com.google.gson.Gson

class TestActivity: BaseActivity() {

    private lateinit var binding: ActivityTestBinding
    private lateinit var choiceArray: List<EmotionAdjective>
    private lateinit var emotionAdjectiveDB: EmotionAdjectiveDatabase

    private val choiceResult= ArrayList<EmotionAdjective>()

    private var i: Int = 0

    companion object {
        private const val LIMIT_REPEAT_NUM: Int = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setInitialize()
        initView()
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when(v!!.id) {
            R.id.test_c1_btn -> {
                choiceClickFunction(0)
            }
            R.id.test_c2_btn -> {
                choiceClickFunction(1)
            }
            R.id.test_c3_btn -> {
                choiceClickFunction(2)
            }
            R.id.test_c4_btn -> {
                choiceClickFunction(3)
            }
        }
    }
    private fun setInitialize(){
        binding = ActivityTestBinding.inflate(layoutInflater)

        emotionAdjectiveDB = EmotionAdjectiveDatabase.getInstance(this)!!
    }

    private fun initView() {
        getAdjectives(emotionAdjectiveDB)

        binding.testC1Btn.setOnClickListener(this)
        binding.testC2Btn.setOnClickListener(this)
        binding.testC3Btn.setOnClickListener(this)
        binding.testC4Btn.setOnClickListener(this)
    }

    private fun getAdjectives(emotionAdjectiveDB: EmotionAdjectiveDatabase) {
        choiceArray = emotionAdjectiveDB.emotionAdjectiveDao().getRandom4Adjective()

        binding.testC1Btn.text = choiceArray[0].adjectiveName
        binding.testC2Btn.text = choiceArray[1].adjectiveName
        binding.testC3Btn.text = choiceArray[2].adjectiveName
        binding.testC4Btn.text = choiceArray[3].adjectiveName
    }

    private fun choiceClickFunction(choiceNum: Int) {
        choiceResult.add(choiceArray[choiceNum])
        getAdjectives(emotionAdjectiveDB)
        i++
        if (i >= LIMIT_REPEAT_NUM)
            startResultActivity()
    }

    private fun startResultActivity() {
        val intent = Intent(this@TestActivity, ResultActivity::class.java)
        val gson: Gson = Gson()
        intent.putExtra("testResult", gson.toJson(choiceResult))
        startActivity(intent)
        finish()
    }
}