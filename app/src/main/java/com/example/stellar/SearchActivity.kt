package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout

class SearchActivity(
    general: IGeneralFunctionality = GeneralFunctionality()
) : AppCompatActivity(),
    IMandatoryOverrides,
    IGeneralFunctionality by general {

    private lateinit var searchBar: EditText

    private lateinit var menuBar: LinearLayout

    private lateinit var menuHome: ImageButton
    private lateinit var menuProfile: ImageButton
    private lateinit var menuGroup: ImageButton
    private lateinit var menuSearch: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        this.loadViews()
        this.attachListeners()
    }

    override fun onDestroy() {
        super.onDestroy()

        this.detachListeners()
    }

    override fun loadViews() {
        this.searchBar = findViewById(R.id.search_search)
        this.menuBar = findViewById(R.id.search_menu_bar)
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