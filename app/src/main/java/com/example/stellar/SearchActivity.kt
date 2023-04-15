package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout

class SearchActivity : AppCompatActivity() {

    private val searchBar = findViewById<EditText>(R.id.search_search)

    private val menuBar = findViewById<LinearLayout>(R.id.search_menu_bar)

    private val menuHome = menuBar.findViewById<ImageButton>(R.id.menu_home)
    private val menuProfile = menuBar.findViewById<ImageButton>(R.id.menu_profile)
    private val menuGroup = menuBar.findViewById<ImageButton>(R.id.menu_group)
    private val menuSearch = menuBar.findViewById<ImageButton>(R.id.menu_search)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }
}