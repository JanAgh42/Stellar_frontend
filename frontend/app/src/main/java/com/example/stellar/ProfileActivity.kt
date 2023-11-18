package com.example.stellar

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager
import com.example.stellar.api.models.User
import com.example.stellar.data.LocalData
import com.example.stellar.data.LocalData.trigger
import com.example.stellar.data.viewmodels.UserViewModel
import androidx.core.view.children
import com.example.stellar.enums.ActivityTypes
import com.example.stellar.enums.Colors
import com.example.stellar.functionalities.GeneralFunctionality
import com.example.stellar.functionalities.CollectionsFunctionality
import com.example.stellar.interfaces.IGeneralFunctionality
import com.example.stellar.interfaces.IMandatoryOverrides
import com.example.stellar.interfaces.ICollectionsFunctionality
import com.squareup.picasso.Picasso
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
    private lateinit var profileSavePhoto: Button
    private lateinit var profileLogOut: Button
    private lateinit var profileUsername: TextView
    private lateinit var profileNumGroups: TextView
    private lateinit var profileNumPosts: TextView
    private lateinit var profileEditBtn: ImageButton
    private lateinit var profileEditUndoBtn: ImageButton
    private lateinit var profileEditSaveBtn: ImageButton
    private lateinit var profileUsernameField: EditText
    private lateinit var profileEmailField: TextView
    private lateinit var profileColorsLayout: LinearLayout
    private lateinit var profileSettingsLayout: LinearLayout

    private lateinit var menuBar: LinearLayout

    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }
    
    private lateinit var pictureAction: ActivityResultLauncher<Intent>
    private var imageUri: Uri? = null
    private val GALLERY_REQUEST_CODE = 10111

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
        this.profileSavePhoto = findViewById(R.id.profile_save_photo)
        this.profileLogOut = findViewById(R.id.profile_log_out)
        this.profileUsername = findViewById(R.id.profile_username)
        this.profileNumGroups = findViewById(R.id.profile_num_groups)
        this.profileNumPosts = findViewById(R.id.profile_num_posts)
        this.profileEditBtn = findViewById(R.id.profile_edit_btn)
        this.profileEditUndoBtn = findViewById(R.id.profile_edit_undo_btn)
        this.profileEditSaveBtn = findViewById(R.id.profile_edit_save_btn)
        this.profileUsernameField = findViewById(R.id.profile_username_field)
        this.profileEmailField = findViewById(R.id.profile_email_field)
        this.profileColorsLayout = findViewById(R.id.profile_colors_layout)
        this.profileSettingsLayout = findViewById(R.id.profile_settings_layout)
        this.menuBar = findViewById(R.id.profile_menu_bar)

        this.profileUsernameField.isEnabled = false
        this.profileEditSaveBtn.visibility = View.GONE
        this.profileEditUndoBtn.visibility = View.GONE
        this.profileSavePhoto.visibility = View.GONE

        this.generateDynamicColors(this.profileColorsLayout, this, this::getProfileColorData, Colors.profileColors, false)
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

        this.profileEditBtn.setOnClickListener {
            this.profileUsernameField.isEnabled = true
            this.profileEditBtn.visibility = View.GONE
            this.profileEditSaveBtn.visibility = View.VISIBLE
            this.profileEditUndoBtn.visibility = View.VISIBLE
            this.profileUsernameField.backgroundTintList = ColorStateList
                .valueOf(ContextCompat.getColor(this, R.color.white))
            this.makeColorButtonsClickable(this.profileColorsLayout, this, this::getProfileColorData)
        }

        this.profileEditSaveBtn.setOnClickListener {
            this.profileEditBtn.visibility = View.VISIBLE
            this.profileEditSaveBtn.visibility = View.GONE
            this.profileEditUndoBtn.visibility = View.GONE
            stopEditable()
        }

        this.profileEditUndoBtn.setOnClickListener {
            this.profileEditBtn.visibility = View.VISIBLE
            this.profileEditSaveBtn.visibility = View.GONE
            this.profileEditUndoBtn.visibility = View.GONE
            stopEditable()
        }

        this.menuBarListeners(this.menuBar, this, ActivityTypes.PROFILE_ACTIVITY)

        LocalData.user.observe(this) { user ->
            if (user == null) {
                return@observe
            }
            this.displayData(user)
        }

        this.profileEditPhoto.setOnClickListener {
            this.checkForPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                GALLERY_REQUEST_CODE,
                applicationContext,
                this,
                this::getImageFromGallery)
        }

        this.pictureAction = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                if (it.data != null) {
                    this.imageUri = it.data?.data
                }
                this.profileEditPhoto.visibility = View.GONE
                this.profileSavePhoto.visibility = View.VISIBLE
                this.handleReceivedImage()
            }
        }

        this.profileSavePhoto.setOnClickListener {
            this.profileSavePhoto.visibility = View.GONE
            this.profileEditPhoto.visibility = View.VISIBLE
        }
    }

    override fun detachListeners() {
        this.profileToNotificationsBtn.setOnClickListener(null)
        this.profileEditBtn.setOnClickListener(null)
        this.profileEditSaveBtn.setOnClickListener(null)
        this.profileEditUndoBtn.setOnClickListener(null)
        this.profileEditPhoto.setOnClickListener(null)
        this.profileSavePhoto.setOnClickListener(null)
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
    
    override fun onRequestPermissionsResult (
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            return
        }

        when (requestCode) {
            this.GALLERY_REQUEST_CODE -> {
                this.getImageFromGallery()
            }
        }
    }

    private fun getImageFromGallery() {
        val toGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        this.pictureAction.launch(toGallery)
    }

    @Suppress("DEPRECATION")
    private fun handleReceivedImage() {
        Picasso.with(this).load(this.imageUri).into(profilePhoto)
    }

    private fun stopEditable() {
        this.profileUsernameField.backgroundTintList = ColorStateList
            .valueOf(ContextCompat.getColor(this, R.color.profile_info))
        for ((index, child) in this.profileColorsLayout.children.withIndex()) {
            if (index % 2 != 0) {
                continue
            }
            val iconShape = child as FrameLayout
            val iconBorder = iconShape.getChildAt(0)

            iconShape.setOnClickListener(null)
            iconBorder.setOnClickListener(null)
        }
    }
}