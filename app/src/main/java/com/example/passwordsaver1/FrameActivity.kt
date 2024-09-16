package com.example.passwordsaver1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi

class FrameActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame)
        val eee:EditText=findViewById(R.id.ee1)
        val eep:EditText=findViewById(R.id.ep1)
        val buts:Button=findViewById(R.id.bbt1)
        val buts1:Button=findViewById(R.id.bbt11)

        val dbHelper=Mydatabase(this)
        buts.setOnClickListener {
            val email=eee.text.toString()
            val password=eep.text.toString()
            if (password.isNotEmpty() && email.isNotEmpty())
            {
                dbHelper.insertUser(email,password)
                Toast.makeText(this,"Data Saved Successfully",Toast.LENGTH_LONG).show()
                eee.setText("")
                eep.setText("")
            }
            else
            {
                Toast.makeText(this,"Please enter full details",Toast.LENGTH_SHORT).show()
            }
        }
        buts1?.setOnClickListener {
            var inte: Intent = Intent(this, MainActivity::class.java)
            startActivity(inte)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }

    }
}