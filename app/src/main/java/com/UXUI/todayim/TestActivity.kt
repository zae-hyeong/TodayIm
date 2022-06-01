package com.UXUI.todayim

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import com.UXUI.todayim.base.BaseActivity
import com.UXUI.todayim.base.TEST_REPEAT_NUM
import com.UXUI.todayim.database.*
import com.UXUI.todayim.databinding.ActivityTestBinding
import com.google.gson.Gson

class TestActivity: BaseActivity() {

    private lateinit var binding: ActivityTestBinding
    private lateinit var choiceArray: List<EmotionAdjective>
    private lateinit var emotionAdjectiveDB: EmotionAdjectiveDatabase

    private val adjectiveResult= ArrayList<DiaryEmotionDetail>()
    private val categoryResult= ArrayList<DiaryEmotionCategory>()

    private var i: Int = 0

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
        setContentView(binding.root)

        emotionAdjectiveDB = EmotionAdjectiveDatabase.getInstance(this)!!
    }

    private fun initView() {
        getAdjectives(emotionAdjectiveDB)

        binding.testC1Btn.setOnClickListener(this)
        binding.testC2Btn.setOnClickListener(this)
        binding.testC3Btn.setOnClickListener(this)
        binding.testC4Btn.setOnClickListener(this)
    }

    private fun choiceClickFunction(choiceNum: Int) {

        val selectedAdjective = choiceArray[choiceNum]

        adjectiveResult.add(
            DiaryEmotionDetail(
                adjective = selectedAdjective.adjectiveName,
                adjectiveCategoryIdx = selectedAdjective.adjectiveCategoryIdx
        ) )
        Log.d("TestActivity", "problem Num : $i, selectedAdjectives : ${selectedAdjective.adjectiveName}")

        val tempCategory = DiaryEmotionCategory(
            adjectiveCategoryIdx= selectedAdjective.adjectiveCategoryIdx,
        )

        if( !categoryResult.contains(tempCategory) ){
            categoryResult.add(tempCategory)
        }

        i++
        if ( i >= TEST_REPEAT_NUM ) {
            var j =0
            while( j <  categoryResult.size ) {
                categoryResult[j].adjectiveCategoryName = emotionAdjectiveDB.emotionAdjectiveDao()
                    .getAdjectiveCategoryName(categoryResult[j].adjectiveCategoryIdx)
                j++
            }

            startResultActivity()
            finish()
        }

        getAdjectives(emotionAdjectiveDB)
    }

    private fun getAdjectives(emotionAdjectiveDB: EmotionAdjectiveDatabase) {
        choiceArray = emotionAdjectiveDB.emotionAdjectiveDao().getRandom4Adjective()

        if(choiceArray.isEmpty()) {
            Thread.sleep(1000)
            Toast.makeText(this, "연결 실패", Toast.LENGTH_SHORT).show()
        }
        else {
            binding.testC1Btn.text = choiceArray[0].adjectiveName
            binding.testC2Btn.text = choiceArray[1].adjectiveName
            binding.testC3Btn.text = choiceArray[2].adjectiveName
            binding.testC4Btn.text = choiceArray[3].adjectiveName
        }
    }

    private fun startResultActivity() {
        val intent = Intent(this@TestActivity, ResultActivity::class.java)
        val gson: Gson = Gson()
        intent.putExtra("categoryResult", gson.toJson(categoryResult))
        intent.putExtra("adjectiveResult", gson.toJson(adjectiveResult))
        startActivity(intent)
        finish()
    }
}

class CustomCircleBarView: View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // Paint 객체 생성
        val paint = Paint()

        // 1. 회색 원(배경)그리기
        paint.color = Color.GRAY
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 40f // 0도에서 시작하는 360도의 호를 그린다.

        canvas?.drawArc(200f, 200f, 700f, 700f, 0f, 360f, false, paint)

        // 2. 파란 원(프로그레스) 그리기
        paint.color = Color.BLUE

        // -90도에서 시작하는 180도의 호를 그린다.
        canvas?.drawArc(200f, 200f, 700f, 700f, -90f, 180f, false, paint)
    }
}