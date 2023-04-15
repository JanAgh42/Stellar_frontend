package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout

class SearchActivity(
    general: IGeneralFunctionality = GeneralFunctionality(),
    scrollCollections: IScrollCollectionsFunctionality = ScrollCollectionsFunctionality()
) : AppCompatActivity(),
    IMandatoryOverrides,
    IGeneralFunctionality by general,
    IScrollCollectionsFunctionality by scrollCollections {

    private lateinit var searchBar: EditText

    private lateinit var menuBar: LinearLayout
    private lateinit var tagsLayout: LinearLayout

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

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0,0)
    }

    override fun loadViews() {
        this.searchBar = findViewById(R.id.search_search)
        this.menuBar = findViewById(R.id.search_menu_bar)
        this.tagsLayout = findViewById(R.id.search_tags_layout)

        this.generateDynamicTags(this.tagsLayout, this)
    }

    override fun setDefaultValues() {}

    override fun attachListeners() {
        this.menuBarListeners(this.menuBar, this, ActivityTypes.SEARCH_ACTIVITY)
    }

    override fun detachListeners() {

    }
}