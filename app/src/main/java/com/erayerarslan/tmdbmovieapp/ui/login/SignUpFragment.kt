package com.erayerarslan.tmdbmovieapp.ui.login

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.erayerarslan.tmdbmovieapp.R
import com.erayerarslan.tmdbmovieapp.databinding.FragmentSignInBinding
import com.erayerarslan.tmdbmovieapp.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()

        // Kayıt butonu tıklama dinleyicisi
        binding.buttonSignUp.setOnClickListener {
            val email = binding.emailEt.text.toString().trim()
            val password = binding.passEt.text.toString().trim()

            if (validateForm(email, password)) {
                registerUser(email, password)
            }
        }
        binding.textViewSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        return binding.root
    }

    // Form validasyonu
    private fun validateForm(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            binding.emailEt.error = "Email required"
            binding.emailEt.requestFocus()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailEt.error = "Please enter a valid email"
            binding.emailEt.requestFocus()
            return false
        }

        if (password.isEmpty()) {
            binding.passEt.error = "Password required"
            binding.passEt.requestFocus()
            return false
        }

        if (password.length < 6) {
            binding.passEt.error = "Password should be at least 6 characters"
            binding.passEt.requestFocus()
            return false
        }
        if (binding.passEt.text.toString() != binding.confirmPassEt.text.toString()) {
            binding.passEt.error = "Password is not the same"
            binding.passEt.requestFocus()
            return false
        }

        return true
    }


    private fun registerUser(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Kayıt başarılı, kullanıcı ana ekrana yönlendirilebilir
                    Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()
                    // Navigate to main screen or perform other actions
                    findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
                } else {
                    // Kayıt başarısız, hata mesajı göster
                    Toast.makeText(requireContext(), task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


