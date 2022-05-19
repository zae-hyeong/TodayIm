package com.UXUI.todayim

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.UXUI.todayim.base.BaseActivity
import com.UXUI.todayim.base.TEST_REPEAT_NUM
import com.UXUI.todayim.database.*
import com.UXUI.todayim.databinding.ActivityResultBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * 결과 화면
 */
class ResultActivity: BaseActivity() {
    private var mBinding: ActivityResultBinding? = null
    private val binding get() = mBinding!!
    private lateinit var roomDatabase: DiaryDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setInitialize()

        val gson: Gson = Gson()
        val rawAdjectiveResult = gson.fromJson(intent.getStringExtra("adjectiveResult"), Array<DiaryEmotionDetail>::class.java)
        Log.d("rawAdjectiveResult", rawAdjectiveResult.toString())
        val rawCategoryResult = gson.fromJson(intent.getStringExtra("categoryResult"), Array<DiaryEmotionCategory>::class.java)
        Log.d("rawCategoryResult", rawCategoryResult.toString())

//        val comparator: Comparator<DiaryEmotionDetail> = compareBy { it.adjectiveCategoryIdx }
//        rawAdjectiveResult = rawAdjectiveResult.sortedWith(comparator)

//        val categoryResult = getCategories(testResult)
    }

    private fun setInitialize() {
        // 뷰 바인딩 구성
        mBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // room data base init
        roomDatabase = DiaryDatabase.getInstance(applicationContext)!!

        binding.btnComplete.setOnClickListener(this)

        // todo - sample progress value
        binding.progressBar1.incrementProgressBy(80)
        binding.progressBar2.incrementProgressBy(40)
        binding.progressBar3.incrementProgressBy(60)
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_complete-> {
                // 완료 버튼 클릭

                // 유효성 검사 (빈 입력필드)
                if (binding.etContent.text.isEmpty()) {
                    Toast.makeText(binding.root.context, "입력 값이 비어있습니다", Toast.LENGTH_SHORT).show()
                    return
                }

                // 일기 데이터 생성
                val diaryInfo = Diary(binding.etContent.text.toString(), SimpleDateFormat("yyyy-MM-dd").format(
                    Date()
                ))

                roomDatabase.diaryDao().insertDiaryData(diaryInfo)
                val diaryIdx = roomDatabase.diaryDao().getDiaryIdx(diaryInfo.diaryDate)

                // 코루틴을 활용하여 비동기 스레드 에서 DB Insert
                CoroutineScope(Dispatchers.IO).launch {
//                    roomDatabase.diaryDao().insertDiaryEmotionCategory(diaryInfo)
//                    roomDatabase.diaryDao().insertDiaryEmotionDetail(diaryInfo)
                    // Toast 메시지는 UI 처리이므로 UI 쓰레드에서 별도로 돌림.
                    runOnUiThread { Toast.makeText(binding.root.context, "일기 작성이 완료 되었습니다", Toast.LENGTH_SHORT).show() }
                    // 액티비티 종료
                    finish()
                }
            }
        }
    }

    private fun getCategories(testResult: List<EmotionAdjective>): ArrayList<DiaryEmotionCategory> {
        var i: Int =0
        var nowCategory: Int = -1
        val categories= ArrayList<DiaryEmotionCategory>()

        while( i< TEST_REPEAT_NUM ){
            if(nowCategory != testResult[i].adjectiveCategoryIdx) {
                categories.add(DiaryEmotionCategory(adjectiveCategoryIdx = testResult[i].adjectiveCategoryIdx))
                nowCategory = testResult[i].adjectiveCategoryIdx
            }
            i++
        }
        return categories
    }
}