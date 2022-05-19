package com.UXUI.todayim

import android.os.Bundle
import android.view.View
import com.UXUI.todayim.base.BaseActivity
import com.UXUI.todayim.database.EmotionAdjective
import com.UXUI.todayim.database.EmotionAdjectiveDatabase
import com.UXUI.todayim.databinding.ActivitySplashBinding
import com.UXUI.todayim.databinding.ActivityTestBinding

class TestActivity: BaseActivity() {
    lateinit var binding: ActivityTestBinding

    lateinit var choiceArray: List<EmotionAdjective>

    private val choiceResult= ArrayList<EmotionAdjective>()

    companion object {
        private const val LIMIT_REPEAT_NUM: Int = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)

        initView()

        var i: Int = 0

        while( i < LIMIT_REPEAT_NUM ) {

        }
    }

    private fun initView() {
        binding.testC1Btn.setOnClickListener(ChoiceClickListener(choiceResult, choiceArray, 0))
        binding.testC2Btn.setOnClickListener(ChoiceClickListener(choiceResult, choiceArray, 0))
        binding.testC3Btn.setOnClickListener(ChoiceClickListener(choiceResult, choiceArray, 0))
        binding.testC4Btn.setOnClickListener(ChoiceClickListener(choiceResult, choiceArray, 0))
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when(v!!.id) {
            R.id.test_c1_btn -> {

            }
            R.id.test_c2_btn -> {

            }
            R.id.test_c3_btn -> {

            }
            R.id.test_c4_btn -> {

            }
        }
    }

    public fun getAdjectives() {
        val emotionAdjectiveDB = EmotionAdjectiveDatabase.getInstance(this)!!
        choiceArray = emotionAdjectiveDB.emotionAdjectiveDao().getRandom4Adjective()

        binding.testC1Btn.text = choiceArray[0].adjectiveName
        binding.testC2Btn.text = choiceArray[1].adjectiveName
        binding.testC3Btn.text = choiceArray[2].adjectiveName
        binding.testC4Btn.text = choiceArray[3].adjectiveName
    }
}

private open class ChoiceClickListener(private val choiceResult: ArrayList<EmotionAdjective>, private val choiceArray: List<EmotionAdjective>, private val choiceNum: Int): View.OnClickListener{
    override fun onClick(v: View?) {
        choiceResult.add(choiceArray[choiceNum])
        val activityInstance = TestActivity()
        activityInstance.getAdjectives()
    }
}
