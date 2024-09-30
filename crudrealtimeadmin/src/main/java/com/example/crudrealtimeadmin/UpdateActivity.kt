package com.example.crudrealtimeadmin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudrealtimeadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Student Information")

        binding.updateButton.setOnClickListener {
            val referenceStudentNumber = binding.referenceStudentNumber.text.toString()
            val updateStudentName = binding.updateStudentName.text.toString()
            val updateStudentGrade = binding.updateStudentGrade.text.toString()
            val updatePhoneNumber = binding.updatePhoneNumber.text.toString()

            if (referenceStudentNumber.isNotEmpty() && updateStudentName.isNotEmpty() && updateStudentGrade.isNotEmpty() && updatePhoneNumber.isNotEmpty()) {
                updateData(
                    referenceStudentNumber,
                    updateStudentName,
                    updateStudentGrade,
                    updatePhoneNumber
                )
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateData(
        studentNumber: String,
        studentName: String,
        studentGrade: String,
        phoneNumber: String
    ) {
        val studentData = mapOf(
            "studentName" to studentName,
            "studentGrade" to studentGrade,
            "phoneNumber" to phoneNumber
        )

        databaseReference.child(studentNumber).updateChildren(studentData).addOnSuccessListener {
            binding.referenceStudentNumber.text.clear()
            binding.updateStudentName.text.clear()
            binding.updateStudentGrade.text.clear()
            binding.updatePhoneNumber.text.clear()
            Toast.makeText(this, "Update successful", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to update", Toast.LENGTH_SHORT).show()
        }
    }
}
