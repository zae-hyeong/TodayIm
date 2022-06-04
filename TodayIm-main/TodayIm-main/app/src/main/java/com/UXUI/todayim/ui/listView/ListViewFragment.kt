package com.UXUI.todayim.ui.listView

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.UXUI.todayim.ResultActivity
import com.UXUI.todayim.database.Diary
import com.UXUI.todayim.database.DiaryDatabase
import com.UXUI.todayim.databinding.FragmentViewListBinding
import com.google.gson.Gson

class ListViewFragment : Fragment() {

    private var _binding: FragmentViewListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // room database
    private lateinit var roomDatabase: DiaryDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewListBinding.inflate(inflater, container, false)
        roomDatabase = DiaryDatabase.getInstance(binding.root.context)!!

        return binding.root
    }

    private fun initRecyclerView() {
        val recordRV = RecordRVAdapter()

        binding.listRecordRv.adapter = recordRV
        binding.listRecordRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        recordRV.setRecordClickListener(object : RecordRVAdapter.RecordClickListener {
            override fun onItemClick(diary: Diary) {
                val gson = Gson()
                val intent = Intent(context, ResultActivity::class.java)

                intent.putExtra("diaryInfo", gson.toJson(diary))
                startActivity(intent)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        records = roomDatabase.diaryDao().getAllDiaryData()

        initRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}