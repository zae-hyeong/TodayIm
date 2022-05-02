package com.UXUI.todayim.ui.listView

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.UXUI.todayim.ResultActivity
import com.UXUI.todayim.databinding.FragmentViewListBinding

class ListViewFragment : Fragment() {

    private var _binding: FragmentViewListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val notificationsViewModel =
//            ViewModelProvider(this)[ListViewModel::class.java]

        _binding = FragmentViewListBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.listViewMainTv
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        val recordRV = RecordRV()

        binding.listRecordRv.adapter = recordRV
        binding.listRecordRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recordRV.setRecordClickListener(object : RecordRV.RecordClickListener {
            override fun onItemClick() {
                val intent = Intent(context, ResultActivity::class.java)
                startActivity(intent)
            }
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}