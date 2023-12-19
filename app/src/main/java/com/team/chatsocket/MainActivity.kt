package com.team.chatsocket

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    internal lateinit var preferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = getSharedPreferences("USERSIGN", Context.MODE_PRIVATE)
        val editor = preferences!!.edit()
        val button = findViewById<Button>(R.id.Button)
        val editText = findViewById<EditText>(R.id.editText)
        button.setOnClickListener {
            editor.putString("name", editText.text.toString())
            val intent = Intent(this, ChatRoomActivity::class.java)
            startActivity(intent)
        }
    }
}