package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import com.example.stellar.enums.ActivityTypes
import com.example.stellar.functionalities.GeneralFunctionality
import com.example.stellar.interfaces.IGeneralFunctionality
import com.example.stellar.interfaces.IMandatoryOverrides

class GroupActivity(
    general: IGeneralFunctionality = GeneralFunctionality()
) : AppCompatActivity(),
    IMandatoryOverrides,
    IGeneralFunctionality by general {

    private lateinit var groupMessagesLayout: LinearLayout

    private lateinit var menuBar: LinearLayout

    private lateinit var topBar: LinearLayout

    private lateinit var topGoBack: ImageButton
    private lateinit var topTitle: TextView
    private lateinit var topSettings: ImageButton
    private lateinit var topButton: TextView
    private lateinit var topMessage: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)

        this.loadViews()
        this.setDefaultValues()
    }

    override fun onStart() {
        super.onStart()

        this.attachListeners()
    }

    override fun onStop() {
        super.onStop()

        this.detachListeners()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    override fun loadViews() {
        this.menuBar = findViewById(R.id.group_menu_bar)
        this.topBar = findViewById(R.id.group_top_bar)
        this.topGoBack = this.topBar.findViewById(R.id.top_back)
        this.topTitle = this.topBar.findViewById(R.id.top_title)
        this.topSettings = this.topBar.findViewById(R.id.top_settings)
        this.topButton = this.topBar.findViewById(R.id.top_button)
        this.topMessage = this.topBar.findViewById(R.id.top_add_message)
        this.groupMessagesLayout = findViewById(R.id.group_message_layout)
    }

    override fun setDefaultValues() {
        this.topTitle.visibility = View.VISIBLE
        this.topSettings.visibility = View.VISIBLE
        this.topMessage.visibility = View.VISIBLE
        this.topButton.visibility = View.INVISIBLE
    }

    override fun attachListeners() {
        this.menuBarListeners(this.menuBar, this, ActivityTypes.GROUP_ACTIVITY)

        this.topGoBack.setOnClickListener {
            finish()
        }
    }

    override fun detachListeners() {
        this.topGoBack.setOnClickListener(null)
    }
}