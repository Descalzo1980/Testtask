package ru.stas.testtask

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.stas.model.User
import ru.stas.testtask.databinding.FragmentSingInBinding
import ru.stas.viewmodel.UserViewModel

class SingInFragment : Fragment() {

    private var _binding: FragmentSingInBinding? = null
    private val binding get() = checkNotNull(_binding){
        "Cannot access binding because it is null. Is the view visible?"
    }

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSingInBinding.inflate(inflater,container,false)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding.btnSingIn.setOnClickListener {
            insertDataToDatabase()
        }
        binding.tvLogIn.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun insertDataToDatabase() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val email = binding.etEmail.text

        if (inputCheck(firstName, lastName, email)) {
            val user = User(0, firstName, lastName, email.toString())
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.profileFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
                .show()
        }
    }
    private fun inputCheck(firstName: String, lastName: String, email: Editable): Boolean {
        return !(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || !isEmailValid(email))
    }

    private fun isEmailValid(email: Editable): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
        return emailRegex.matches(email.toString())
    }
}