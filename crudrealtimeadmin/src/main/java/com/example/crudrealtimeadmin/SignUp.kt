package com.example.crudrealtimeadmin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudrealtimeadmin.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.AlreadyRSignIn.setOnClickListener {
            clearFields()
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

        binding.SignUp.setOnClickListener {
            val email = binding.Email.text.toString()
            val pass = binding.Password.text.toString()
            val conPass = binding.RePassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && conPass.isNotEmpty()) {
                if (pass == conPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            clearFields()
                            val intent = Intent(this, SignIn::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearFields() {
        binding.Email.text?.clear()
        binding.Password.text?.clear()
        binding.RePassword.text?.clear()
    }
}
