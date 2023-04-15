package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {

    private val usernameText = findViewById<TextView>(R.id.main_username)
    private val groupMemberText = findViewById<TextView>(R.id.main_group_member)
    private val allGroups = findViewById<TextView>(R.id.main_all_groups)
    private val ownGroups = findViewById<TextView>(R.id.main_own_groups)

    private val profilePicture = findViewById<CircleImageView>(R.id.main_profile_image)
    private val toNotificationsButton = findViewById<ImageButton>(R.id.main_to_notifications)

    private val searchBar = findViewById<EditText>(R.id.main_search_groups)

    private val menuBar = findViewById<LinearLayout>(R.id.main_menu_bar)

    private val menuHome = menuBar.findViewById<ImageButton>(R.id.menu_home)
    private val menuProfile = menuBar.findViewById<ImageButton>(R.id.menu_profile)
    private val menuGroup = menuBar.findViewById<ImageButton>(R.id.menu_group)
    private val menuSearch = menuBar.findViewById<ImageButton>(R.id.menu_search)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}