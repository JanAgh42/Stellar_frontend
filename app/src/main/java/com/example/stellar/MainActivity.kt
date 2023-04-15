package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
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

    private lateinit var menuHome: ImageButton
    private lateinit var menuProfile: ImageButton
    private lateinit var menuGroup: ImageButton
    private lateinit var menuSearch: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.loadViews()
        this.attachListeners()
    }

    override fun onDestroy() {
        super.onDestroy()

        this.detachListeners()
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