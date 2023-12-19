package com.team.chatsocket

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team.chatsocket.adapter.ChatAdapter
import com.team.chatsocket.model.ChatModel
import java.text.SimpleDateFormat
import java.util.Date

class ChatRoomActivity : AppCompatActivity() {

    internal lateinit var preferences: SharedPreferences
    private lateinit var chatingText : EditText
    private lateinit var chatSendButton : Button
    private lateinit var chat_recycler : RecyclerView

    var arrayList = arrayListOf<ChatModel>()
    val mApdapter = ChatAdapter(this, arrayList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)
        preferences = getSharedPreferences("USERSIGN", Context.MODE_PRIVATE)

        chat_recycler = findViewById<RecyclerView>(R.id.chat_recyclerview)
        //어댑터 선언
        chat_recycler.adapter = mApdapter
        //레이아웃 매니저 선언
        val lm = LinearLayoutManager(this)
        chat_recycler.layoutManager = lm
        chat_recycler.setHasFixedSize(true)

        chatSendButton = findViewById(R.id.chat_Send_Button)
        chatingText = findViewById(R.id.chating_Text)

        chatSendButton.setOnClickListener {
            sendMessage()

        }
    }

    fun sendMessage() {
        val now = System.currentTimeMillis()
        val date = Date(now)
        //나중에 바꿔줄것 밑의 yyyy-MM-dd는 그냥 20xx년 xx월 xx일만 나오게 하는 식
        val sdf = SimpleDateFormat("yyyy-MM-dd")

        val getTime = sdf.format(date)

        val item = preferences.getString("name", "")
            ?.let { ChatModel(it, chatingText.text.toString(), "example", getTime) }
        mApdapter.addItem(item!!)
        mApdapter.notifyDataSetChanged()

        chatingText.setText("")
    }
}