package com.example.passwordsaver1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper=Mydatabase(this)
        val userList=dbHelper.getAllUsers()
        val lu:ListView=findViewById(R.id.l)
        val but:Button=findViewById(R.id.b)

        val userAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userList.map { "${it.email}: ${it.passwoard}" })

        // Set the adapter to the ListView
        lu.adapter = userAdapter
        but?.setOnClickListener {
            var inte: Intent = Intent(this, FrameActivity::class.java)
            startActivity(inte)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }
    }
}