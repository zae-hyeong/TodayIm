package com.UXUI.todayim.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.UXUI.todayim.TestActivity
import com.UXUI.todayim.databinding.FragmentHomeBinding

class HomeFragment : Fragment() { //, View.OnAttachStateChangeListener{

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.homeMainTv
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        binding.homeStartBtn.setOnClickListener {
            val intent = Intent(context, TestActivity::class.java)
            startActivity(intent)
        }
        binding.homeStartPb.progress = 0

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    override fun onViewAttachedToWindow(p0: View?) {
//        while(true){
//            binding.homeStartPb.progress++
//            sleep(100)
//            if(binding.homeStartPb.progress >=100) {
//                val startTestActivityIntent = Intent(activity, TestActivity::class.java)
//                startActivity(startTestActivityIntent)
//                break
//            }
//        }
//    }

//    override fun onViewDetachedFromWindow(p0: View?) {
//        //TODO("Not yet implemented")
//    }
}
