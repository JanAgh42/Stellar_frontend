package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager
import com.example.stellar.data.LocalData
import com.example.stellar.data.LocalData.trigger
import com.example.stellar.data.viewmodels.NotificationViewModel
import com.example.stellar.data.viewmodels.UserViewModel
import com.example.stellar.enums.ActivityTypes
import com.example.stellar.functionalities.GeneralFunctionality
import com.example.stellar.interfaces.IGeneralFunctionality
import com.example.stellar.interfaces.IMandatoryOverrides

class NotificationActivity(
    general: IGeneralFunctionality = GeneralFunctionality()
) : AppCompatActivity(),
    IMandatoryOverrides,
    IGeneralFunctionality by general {

    private lateinit var notifLayout: LinearLayout

    private lateinit var menuBar: LinearLayout

    private lateinit var topBar: LinearLayout

    private lateinit var topGoBack: ImageButton
    private lateinit var topTitle: TextView
    private lateinit var topSettings: ImageButton
    private lateinit var topButton: TextView
    private lateinit var topMessage: ImageButton

    private val viewModel: NotificationViewModel by lazy {
        ViewModelProvider(this)[NotificationViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        this.loadViews()
    }

    override fun onStart() {
        super.onStart()

        this.attachListeners()
        this.setDefaultValues()
    }

    override fun onStop() {
        super.onStop()

        this.detachListeners()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
        WorkManager.getInstance(applicationContext).cancelAllWorkByTag("getNotifications")
    }

    override fun loadViews() {
        this.menuBar = findViewById(R.id.notif_menu_bar)
        this.topBar = findViewById(R.id.notif_top_bar)
        this.topGoBack = this.topBar.findViewById(R.id.top_back)
        this.topTitle = this.topBar.findViewById(R.id.top_title)
        this.topSettings = this.topBar.findViewById(R.id.top_settings)
        this.topButton = this.topBar.findViewById(R.id.top_button)
        this.topMessage = this.topBar.findViewById(R.id.top_add_message)
        this.notifLayout = findViewById(R.id.notif_layout)
    }

    override fun setDefaultValues() {
        this.topTitle.visibility = View.INVISIBLE
        this.topSettings.visibility = View.INVISIBLE
        this.topMessage.visibility = View.INVISIBLE
        this.topButton.visibility = View.VISIBLE

        this.topButton.setText(R.string.notif_delete_all_button)

        if (LocalData.userNotifs.value == null) {
            this.viewModel.getUserNotifs(applicationContext, LocalData.identity)
        }
        else {
            LocalData.userNotifs.trigger()
        }
    }

    override fun attachListeners() {
        this.menuBarListeners(this.menuBar, this, ActivityTypes.NOTIFICATION_ACTIVITY)

        this.topGoBack.setOnClickListener {
            finish()
        }

        this.topButton.setOnClickListener {
            finish()
        }

        LocalData.userNotifs.observe(this) { list ->
            if (list == null) {
                return@observe
            }
            Toast.makeText(this, "notifs here", Toast.LENGTH_SHORT).show()
        }
    }

    override fun detachListeners() {
        this.topGoBack.setOnClickListener(null)
        this.topButton.setOnClickListener(null)
    }
}