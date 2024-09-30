package com.example.crudrealtimeadmin

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import com.example.crudrealtimeadmin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainCreate.setOnClickListener {
            val intent=Intent(this@MainActivity,Create::class.java)
            startActivity(intent)

        }

        binding.mainUpload.setOnClickListener {
            val intent=Intent(this@MainActivity,UploadActivity::class.java)
            startActivity(intent)

        }
        binding.mainUpdate.setOnClickListener {
            val intent=Intent(this@MainActivity,UpdateActivity::class.java)
            startActivity(intent)

        }
        binding.mainDelete.setOnClickListener {
            val intent=Intent(this@MainActivity,DeleteActivity::class.java)
            startActivity(intent)

        }
        }
    }
