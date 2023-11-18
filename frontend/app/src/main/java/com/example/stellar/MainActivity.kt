package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager
import com.example.stellar.api.models.User
import com.example.stellar.data.LocalData
import com.example.stellar.data.LocalData.trigger
import com.example.stellar.data.viewmodels.UserViewModel
import com.example.stellar.enums.ActivityTypes
import com.example.stellar.functionalities.GeneralFunctionality
import com.example.stellar.interfaces.IGeneralFunctionality
import com.example.stellar.interfaces.IMandatoryOverrides
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity(
    general: IGeneralFunctionality = GeneralFunctionality()
) : AppCompatActivity(),
    IMandatoryOverrides,
    IGeneralFunctionality by general {

    private lateinit var usernameText: TextView
    private lateinit var groupMemberText: TextView
    private lateinit var allGroups: TextView
    private lateinit var ownGroups: TextView

    private lateinit var profilePicture: CircleImageView
    private lateinit var toNotificationsButton: ImageButton

    private lateinit var searchBar: EditText

    private lateinit var menuBar: LinearLayout

    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        overridePendingTransition(0,0)
        WorkManager.getInstance(applicationContext).cancelAllWorkByTag("loadUserData")
    }

    override fun loadViews() {
        this.usernameText = findViewById(R.id.main_username)
        this.groupMemberText = findViewById(R.id.main_group_member)
        this.allGroups = findViewById(R.id.main_all_groups)
        this.ownGroups = findViewById(R.id.main_own_groups)
        this.profilePicture = findViewById(R.id.main_profile_image)
        this.toNotificationsButton = findViewById(R.id.main_to_notifications)
        this.searchBar = findViewById(R.id.main_search_groups)
        this.menuBar = findViewById(R.id.main_menu_bar)
    }

    override fun setDefaultValues() {
        if (LocalData.user.value == null) {
            this.viewModel.getUser(applicationContext, LocalData.identity)
        }
        else {
            LocalData.user.trigger()
        }
    }

    override fun attachListeners() {
        this.menuBarListeners(this.menuBar, this, ActivityTypes.MAIN_ACTIVITY)

        this.toNotificationsButton.setOnClickListener {
            this.changeActivity(this, NotificationActivity::class.java)
        }

        this.profilePicture.setOnClickListener {
            this.changeActivity(this, ProfileActivity::class.java)
        }

        this.allGroups.setOnClickListener {

        }

        this.ownGroups.setOnClickListener {

        }

        LocalData.user.observe(this) { user ->
            if (user == null) {
                return@observe
            }
            this.displayData(user)
        }
    }

    override fun detachListeners() {
        this.toNotificationsButton.setOnClickListener(null)
        this.profilePicture.setOnClickListener(null)
        this.allGroups.setOnEditorActionListener(null)
        this.ownGroups.setOnEditorActionListener(null)
    }

    private fun displayData(user: User) {
        this.usernameText.text = user.name
        this.groupMemberText.text = "Member of ${user.groups} groups"
    }
}