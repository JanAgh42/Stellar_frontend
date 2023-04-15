package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity : AppCompatActivity() {

    private lateinit var profilePhoto: CircleImageView
    private lateinit var profileNotifBtn: ImageButton
    private lateinit var profileEditPhoto: Button
    private lateinit var profileLogOut: Button
    private lateinit var profileUsername: TextView
    private lateinit var profileNumGroups: TextView
    private lateinit var profileNumPosts: TextView
    private lateinit var profileEditBtn: ImageButton
    private lateinit var profileUsernameField: EditText
    private lateinit var profileEmailField: EditText

    private lateinit var profileUsernameWhite: ImageButton
    private lateinit var profileUsernameRed: ImageButton
    private lateinit var profileUsernameBlue: ImageButton
    private lateinit var profileUsernameGreen: ImageButton
    private lateinit var profileUsernamePink: ImageButton
    private lateinit var profileUsernamePurple: ImageButton
    private lateinit var profileUsernameYellow: ImageButton
    private lateinit var profileUsernameTurquoise: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        this.profilePhoto = findViewById(R.id.profile_photo)
        this.profileNotifBtn = findViewById(R.id.profile_notif_btn)
        this.profileEditPhoto = findViewById(R.id.profile_edit_photo)
        this.profileLogOut = findViewById(R.id.profile_log_out)
        this.profileUsername = findViewById(R.id.profile_username)
        this.profileNumGroups = findViewById(R.id.profile_num_groups)
        this.profileNumPosts = findViewById(R.id.profile_num_posts)
        this.profileEditBtn = findViewById(R.id.profile_edit_btn)
        this.profileUsernameField = findViewById(R.id.profile_username_field)
        this.profileEmailField = findViewById(R.id.profile_email_field)
        this.profileUsernameWhite = findViewById(R.id.profile_username_white)
        this.profileUsernameRed = findViewById(R.id.profile_username_red)
        this.profileUsernameBlue = findViewById(R.id.profile_username_blue)
        this.profileUsernameGreen = findViewById(R.id.profile_username_green)
        this.profileUsernamePink = findViewById(R.id.profile_username_pink)
        this.profileUsernamePurple = findViewById(R.id.profile_username_purple)
        this.profileUsernameYellow = findViewById(R.id.profile_username_yellow)
        this.profileUsernameTurquoise = findViewById(R.id.profile_username_turquoise)

    }
}