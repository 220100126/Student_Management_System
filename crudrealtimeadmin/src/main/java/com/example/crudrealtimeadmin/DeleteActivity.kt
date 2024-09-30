package com.example.crudrealtimeadmin
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudrealtimeadmin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.deleteButton.setOnClickListener {
            val StudentNumber=binding.deleteStudentNumber.text.toString()
            if (StudentNumber.isNotEmpty()){
                deleteData(StudentNumber)
            }else{
                Toast.makeText(this, "Please enter student number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteData(StudentNumber: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Student Information")
        databaseReference.child(StudentNumber).removeValue().addOnSuccessListener {
            binding.deleteStudentNumber.text.clear()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this, "Unable to Delete", Toast.LENGTH_SHORT).show()
        }
    }

}
