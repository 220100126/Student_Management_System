package com.example.crudrealtimeadmin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crudrealtimeadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val StudentName=binding.uploadStudentName.text.toString()
            val StudentGrade=binding.uploadStudentGrade.text.toString()
            val PhoneNumber=binding.uploadPhoneNumber.text.toString()
            val StudentNumber=binding.uploadStudentNumber.text.toString()

            databaseReference=FirebaseDatabase.getInstance().getReference("Student Information")
            val vehicleData=StudentData(StudentName,StudentGrade,PhoneNumber,StudentNumber)

            databaseReference.child(StudentNumber).setValue(vehicleData).addOnSuccessListener {
                binding.uploadStudentName.text.clear()
                binding.uploadStudentGrade.text.clear()
                binding.uploadPhoneNumber.text.clear()
                binding.uploadStudentNumber.text.clear()

                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
                val intent= Intent(this@UploadActivity,MainActivity::class.java)
                startActivity(intent)
                finish()


            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }
        }
    }
