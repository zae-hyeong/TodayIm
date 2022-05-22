package com.UXUI.todayim.ui.listView

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.UXUI.todayim.ResultActivity
import com.UXUI.todayim.database.DiaryDatabase
import com.UXUI.todayim.databinding.FragmentViewListBinding

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
//        val notificationsViewModel =
//            ViewModelProvider(this)[ListViewModel::class.java]

        _binding = FragmentViewListBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.listViewMainTv
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        return binding.root
    }

    private fun initRecyclerView() {
        val recordRV = RecordRVAdapter()

        binding.listRecordRv.adapter = recordRV
        binding.listRecordRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recordRV.setRecordClickListener(object : RecordRVAdapter.RecordClickListener {
            override fun onItemClick() {
                val intent = Intent(context, ResultActivity::class.java)
                startActivity(intent)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        roomDatabase = DiaryDatabase.getInstance(binding.root.context)!!
        records = roomDatabase.diaryDao().getAllDiaryData()

        initRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}