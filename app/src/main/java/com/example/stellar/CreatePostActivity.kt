package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView

class CreatePostActivity(
    general: IGeneralFunctionality = GeneralFunctionality()
) : AppCompatActivity(),
    IMandatoryOverrides,
    IGeneralFunctionality by general {

    private lateinit var messageTypeTitle: TextView
    private lateinit var recipientName: TextView
    private lateinit var recipientMessage: TextView
    private lateinit var showLocation: TextView
    private lateinit var hideLocation: TextView

    private lateinit var messageContent: EditText

    private lateinit var notifyAll: CheckBox

    private lateinit var topBar: LinearLayout

    private lateinit var topGoBack: ImageButton
    private lateinit var topTitle: TextView
    private lateinit var topSettings: ImageButton
    private lateinit var topButton: TextView

    private lateinit var menuBar: LinearLayout

    private lateinit var menuHome: ImageButton
    private lateinit var menuProfile: ImageButton
    private lateinit var menuGroup: ImageButton
    private lateinit var menuSearch: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        this.loadViews()
        this.attachListeners()
    }

    override fun onDestroy() {
        super.onDestroy()

        this.detachListeners()
    }

    override fun loadViews() {
        this.messageTypeTitle = findViewById(R.id.cpost_message_type)
        this.recipientName = findViewById(R.id.cpost_recipient_name)
        this.recipientMessage = findViewById(R.id.cpost_recipient_message)
        this.showLocation = findViewById(R.id.cpost_location)
        this.hideLocation = findViewById(R.id.cpost_hide_location)
        this.messageContent = findViewById(R.id.cpost_message)
        this.notifyAll = findViewById(R.id.cpost_notify_all)
        this.topBar = findViewById(R.id.cpost_top_bar)
        this.topGoBack = this.topBar.findViewById(R.id.top_back)
        this.topTitle = this.topBar.findViewById(R.id.top_title)
        this.topSettings = this.topBar.findViewById(R.id.top_settings)
        this.topButton = this.topButton.findViewById(R.id.top_button)
        this.menuBar = findViewById(R.id.cpost_menu_bar)
        this.menuHome = this.menuBar.findViewById(R.id.menu_home)
        this.menuProfile = this.menuBar.findViewById(R.id.menu_profile)
        this.menuGroup = this.menuBar.findViewById(R.id.menu_group)
        this.menuSearch = this.menuBar.findViewById(R.id.menu_search)
    }

    override fun setDefaultValues() {}

    override fun attachListeners() {

    }

    override fun detachListeners() {

    }
}