package ru.stas.testtask

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ru.stas.testtask.databinding.FragmentLoginBinding
import ru.stas.viewmodel.UserViewModel


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showPassword()
        binding.btnLogin.setOnClickListener {
            authenticationUser()
            addName()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun authenticationUser() {
        val firstName = binding.etFirstName.text.toString()
        userViewModel.authenticationName(firstName = firstName).observe(viewLifecycleOwner) { user ->
            if (user.firstName.isNotEmpty()) {
                    findNavController().navigate(R.id.profileFragment)
            }else{
                Snackbar.make(requireView(), "Пользователь не найден, пройдите регистрацию", Snackbar.LENGTH_LONG).show()
            }
        }
    }

        private fun addName(){
        val firstName = binding.etFirstName.text.toString()
        val action = LoginFragmentDirections.actionLoginFragmentToProfileFragment(firstName)
        findNavController().navigate(action)
    }
    private fun showPassword() {
        val passwordEditText = binding.etPassword
        val eyeImageView = binding.imgEye
        passwordEditText.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        eyeImageView.setImageResource(R.drawable.eye)
        eyeImageView.setOnClickListener {
            val inputType = passwordEditText.inputType
            if (inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                passwordEditText.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                eyeImageView.setImageResource(R.drawable.eye)
            } else {
                passwordEditText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                eyeImageView.setImageResource(R.drawable.eye_open)
            }
        }
    }
}
