package com.UXUI.todayim.ui.listView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
        val notificationsViewModel =
            ViewModelProvider(this).get(ListViewModel::class.java)

        _binding = FragmentViewListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.listViewMainTv
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}