package com.UXUI.todayim

import android.os.Bundle
import android.view.View
import com.UXUI.todayim.base.BaseActivity
import com.UXUI.todayim.database.EmotionAdjective
import com.UXUI.todayim.databinding.ActivitySplashBinding
import com.UXUI.todayim.databinding.ActivityTestBinding

class TestActivity: BaseActivity() {
    lateinit var binding: ActivityTestBinding

    lateinit var choiceArray: ArrayList<EmotionAdjective>

    companion object {
        private const val LIMIT_REPEAT_NUM: Int = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)

        initView()

//        var i: Int = 0
//
//        while( i < LIMIT_REPEAT_NUM ) {
//
//        }
    }

    private fun initView() {
        binding.testC1Btn.tag = 0
        binding.testC2Btn.tag = 1
        binding.testC3Btn.tag = 2
        binding.testC4Btn.tag = 3

        binding.testC1Btn.setOnClickListener(this)
        binding.testC2Btn.setOnClickListener(this)
        binding.testC3Btn.setOnClickListener(this)
        binding.testC4Btn.setOnClickListener(this)
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
}

