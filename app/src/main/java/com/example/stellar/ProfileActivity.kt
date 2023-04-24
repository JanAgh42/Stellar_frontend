package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager
import com.example.stellar.api.models.User
import com.example.stellar.data.LocalData
import com.example.stellar.data.LocalData.trigger
import com.example.stellar.data.viewmodels.UserViewModel
import com.example.stellar.enums.ActivityTypes
import com.example.stellar.enums.Colors
import com.example.stellar.functionalities.GeneralFunctionality
import com.example.stellar.functionalities.CollectionsFunctionality
import com.example.stellar.interfaces.IGeneralFunctionality
import com.example.stellar.interfaces.IMandatoryOverrides
import com.example.stellar.interfaces.ICollectionsFunctionality
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity(
    general: IGeneralFunctionality = GeneralFunctionality(),
    scrollCollections: ICollectionsFunctionality = CollectionsFunctionality()
) : AppCompatActivity(),
    IMandatoryOverrides,
    IGeneralFunctionality by general,
    ICollectionsFunctionality by scrollCollections {

    private lateinit var profilePhoto: CircleImageView
    private lateinit var profileToNotificationsBtn: ImageButton
    private lateinit var profileEditPhoto: Button
    private lateinit var profileLogOut: Button
    private lateinit var profileUsername: TextView
    private lateinit var profileNumGroups: TextView
    private lateinit var profileNumPosts: TextView
    private lateinit var profileEditBtn: ImageButton
    private lateinit var profileUsernameField: EditText
    private lateinit var profileEmailField: TextView
    private lateinit var profileColorsLayout: LinearLayout
    private lateinit var profileSettingsLayout: LinearLayout

    private lateinit var menuBar: LinearLayout

    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

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
        WorkManager.getInstance(applicationContext).cancelAllWorkByTag("loadUserData")
    }

    override fun loadViews() {
        this.profilePhoto = findViewById(R.id.profile_photo)
        this.profileToNotificationsBtn = findViewById(R.id.profile_notif_btn)
        this.profileEditPhoto = findViewById(R.id.profile_edit_photo)
        this.profileLogOut = findViewById(R.id.profile_log_out)
        this.profileUsername = findViewById(R.id.profile_username)
        this.profileNumGroups = findViewById(R.id.profile_num_groups)
        this.profileNumPosts = findViewById(R.id.profile_num_posts)
        this.profileEditBtn = findViewById(R.id.profile_edit_btn)
        this.profileUsernameField = findViewById(R.id.profile_username_field)
        this.profileEmailField = findViewById(R.id.profile_email_field)
        this.profileColorsLayout = findViewById(R.id.profile_colors_layout)
        this.profileSettingsLayout = findViewById(R.id.profile_settings_layout)
        this.menuBar = findViewById(R.id.profile_menu_bar)

        this.generateDynamicColors(this.profileColorsLayout, this, this::getProfileColorData, Colors.profileColors)
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
        this.profileToNotificationsBtn.setOnClickListener {
            this.changeActivity(this, NotificationActivity::class.java)
        }

        this.menuBarListeners(this.menuBar, this, ActivityTypes.PROFILE_ACTIVITY)

        LocalData.user.observe(this) { user ->
            if (user == null) {
                return@observe
            }
            this.displayData(user)
        }
    }

    override fun detachListeners() {
        this.profileToNotificationsBtn.setOnClickListener(null)
    }

    private fun getProfileColorData(text: String) {
        this.profileUsername.setTextColor(ContextCompat.getColor(this, text.toInt()))
            Toast.makeText(this, "group " + text, Toast.LENGTH_LONG).show()
    }

    private fun displayData(user: User) {
        this.profileUsername.text = user.name
        this.profileNumGroups.text = "${user.ownGroups}\ngroups"
        this.profileNumPosts.text = "${user.posts}\nposts"
    }
}