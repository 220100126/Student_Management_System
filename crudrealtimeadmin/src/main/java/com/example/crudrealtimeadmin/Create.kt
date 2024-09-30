package com.example.crudrealtimeadmin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudrealtimeadmin.databinding.ActivityCreateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Create : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().getReference("Student Information")

        binding.searchButton.setOnClickListener {
            val searchStudentNumber: String = binding.searchStudentNumber.text.toString()
            if (searchStudentNumber.isNotEmpty()) {
                readData(searchStudentNumber)
            } else {
                Toast.makeText(this, "Please enter the student number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(studentNumber: String) {
        databaseReference.child(studentNumber).get().addOnSuccessListener {
            if (it.exists()) {
                val studentName = it.child("studentName").value
                val studentGrade = it.child("studentGrade").value
                val phoneNumber = it.child("phoneNumber").value

                Toast.makeText(this, "Results Found", Toast.LENGTH_SHORT).show()
                binding.searchStudentNumber.text.clear()
                binding.readStudentName.text = studentName.toString()
                binding.readStudentGrade.text = studentGrade.toString()
                binding.readPhoneNumber.text = phoneNumber.toString()
            } else {
                Toast.makeText(this, "Student number does not exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
}
