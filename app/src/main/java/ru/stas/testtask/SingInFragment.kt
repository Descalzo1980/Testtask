package ru.stas.testtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.stas.testtask.databinding.FragmentSingInBinding

class SingInFragment : Fragment() {

    private var _binding: FragmentSingInBinding? = null
    private val binding get() = checkNotNull(_binding){
        "Cannot access binding because it is null. Is the view visible?"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSingInBinding.inflate(inflater,container,false)

        binding.tvLogIn.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
        return binding.root
    }
}