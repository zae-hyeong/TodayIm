package com.UXUI.todayim.ui.calendarView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.UXUI.todayim.databinding.FragmentViewCalendarBinding
import com.UXUI.todayim.ui.version.VersionViewModel
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import com.UXUI.todayim.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_view_calendar.*
import android.widget.Toast
//import org.jetbrains.anko.toast
import java.io.FileInputStream
import java.io.FileOutputStream


class CalendarViewFragment : Fragment() {

    var fname: String = ""
    var str: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // 달력 날짜가 선택되면
            diaryTextView.visibility = View.VISIBLE // 해당 날짜가 뜨는 textView가 Visible
            save_Btn.visibility = View.VISIBLE // 저장 버튼이 Visible
            contextEditText.visibility = View.VISIBLE // EditText가 Visible
            textView2.visibility = View.INVISIBLE // 저장된 일기 textView가 Invisible
            cha_Btn.visibility = View.INVISIBLE // 수정 Button이 Invisible
            del_Btn.visibility = View.INVISIBLE // 삭제 Button이 Invisible

            diaryTextView.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)
            // 날짜를 보여주는 텍스트에 해당 날짜를 넣는다.
            contextEditText.setText("") // EditText에 공백값 넣기

            checkedDay(year, month, dayOfMonth) // checkedDay 메소드 호출


        }

        save_Btn.setOnClickListener { // 저장 Button이 클릭되면
            //saveDiary(fname) // saveDiary 메소드 호출
            Toast.makeText(fname + "데이터를 저장했습니다.") // 토스트 메세지
            str = contextEditText.getText().toString() // str 변수에 edittext내용을 toString형으로 저장
            textView2.text = "${str}" // textView에 str 출력
            save_Btn.visibility = View.INVISIBLE
            cha_Btn.visibility = View.VISIBLE
            del_Btn.visibility = View.VISIBLE
            contextEditText.visibility = View.INVISIBLE
            textView2.visibility = View.VISIBLE
        }
    }

    fun checkedDay(cYear: Int, cMonth: Int, cDay: Int) {
        fname = "" + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt"
// 저장할 파일 이름 설정. Ex) 2019-01-20.txt
        var fis: FileInputStream? = null // FileStream fis 변수 설정

        try {
            //fis = openFileInput(fname) // fname 파일 오픈!!

            val fileData = ByteArray(fis.available()) // fileData에 파이트 형식
//으로 저장
            fis.read(fileData) // fileData를 읽음
            fis.close()

            str = String(fileData) // str 변수에 fileData를 저장

            contextEditText.visibility = View.INVISIBLE
            textView2.visibility = View.VISIBLE
            textView2.text = "${str}" // textView에 str 출력

            save_Btn.visibility = View.INVISIBLE
            cha_Btn.visibility = View.VISIBLE
            del_Btn.visibility = View.VISIBLE

            cha_Btn.setOnClickListener { // 수정 버튼을 누를 시
                contextEditText.visibility = View.VISIBLE
                textView2.visibility = View.INVISIBLE
                contextEditText.setText(str) // editText에 textView에 저장된
// 내용을 출력
                save_Btn.visibility = View.VISIBLE
                cha_Btn.visibility = View.INVISIBLE
                del_Btn.visibility = View.INVISIBLE
                textView2.text = "${contextEditText.getText()}"
            }

            del_Btn.setOnClickListener {
                textView2.visibility = View.INVISIBLE
                contextEditText.setText("")
                contextEditText.visibility = View.VISIBLE
                save_Btn.visibility = View.VISIBLE
                cha_Btn.visibility = View.INVISIBLE
                del_Btn.visibility = View.INVISIBLE
                //removeDiary(fname)
                Toast.makeText(fname + "데이터를 삭제했습니다.")
            }

            if (textView2.getText() == "") {
                textView2.visibility = View.INVISIBLE
                diaryTextView.visibility = View.VISIBLE
                save_Btn.visibility = View.VISIBLE
                cha_Btn.visibility = View.INVISIBLE
                del_Btn.visibility = View.INVISIBLE
                contextEditText.visibility = View.VISIBLE
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}