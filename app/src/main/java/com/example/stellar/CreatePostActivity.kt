package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView

class CreatePostActivity : AppCompatActivity() {

    private val messageTypeTitle = findViewById<TextView>(R.id.cpost_message_type)
    private val recipientName = findViewById<TextView>(R.id.cpost_recipient_name)
    private val recipientMessage = findViewById<TextView>(R.id.cpost_recipient_message)
    private val showLocation = findViewById<TextView>(R.id.cpost_location)
    private val hideLocation = findViewById<TextView>(R.id.cpost_hide_location)

    private val messageContent = findViewById<EditText>(R.id.cpost_message)

    private val notifyAll = findViewById<CheckBox>(R.id.cpost_notify_all)

    private val topBar = findViewById<LinearLayout>(R.id.cpost_top_bar)

    private val topGoBack = topBar.findViewById<ImageButton>(R.id.top_back)
    private val topTitle = topBar.findViewById<TextView>(R.id.top_title)
    private val topSettings = topBar.findViewById<ImageButton>(R.id.top_settings)
    private val topButton = topBar.findViewById<TextView>(R.id.top_button)

    private val menuBar = findViewById<LinearLayout>(R.id.cpost_menu_bar)

    private val menuHome = menuBar.findViewById<ImageButton>(R.id.menu_home)
    private val menuProfile = menuBar.findViewById<ImageButton>(R.id.menu_profile)
    private val menuGroup = menuBar.findViewById<ImageButton>(R.id.menu_group)
    private val menuSearch = menuBar.findViewById<ImageButton>(R.id.menu_search)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
    }
}