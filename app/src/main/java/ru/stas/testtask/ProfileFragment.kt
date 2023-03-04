package ru.stas.testtask

import android.os.Bundle
import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AlignmentSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.stas.testtask.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = checkNotNull(_binding){
        "Cannot access binding because it is null. Is the view visible?"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        logOut()
//        addName()
        binding.tvTradeStore.setOnClickListener {
            findNavController().navigate(R.id.pageOneFragment)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

//    private fun addName(){
//        val firstName = ProfileFragmentArgs.fromBundle(requireArguments()).firstName
//        val spannable = SpannableString("Привет $firstName")
//        spannable.setSpan(
//            AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
//            0,
//            spannable.length,
//            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//        )
//        binding.tvNameProfile.text = spannable
//    }
    private fun logOut(){
        binding.ivLogOut.setOnClickListener {
            findNavController().navigate(R.id.singInFragment)
        }
    }
}