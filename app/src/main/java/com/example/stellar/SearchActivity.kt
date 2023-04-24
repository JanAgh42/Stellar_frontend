package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.stellar.data.LocalData
import com.example.stellar.data.viewmodels.GroupViewModel
import com.example.stellar.data.viewmodels.UserViewModel
import com.example.stellar.enums.ActivityTypes
import com.example.stellar.functionalities.GeneralFunctionality
import com.example.stellar.functionalities.CollectionsFunctionality
import com.example.stellar.interfaces.IGeneralFunctionality
import com.example.stellar.interfaces.IMandatoryOverrides
import com.example.stellar.interfaces.ICollectionsFunctionality

class SearchActivity(
    general: IGeneralFunctionality = GeneralFunctionality(),
    scrollCollections: ICollectionsFunctionality = CollectionsFunctionality()
) : AppCompatActivity(),
    IMandatoryOverrides,
    IGeneralFunctionality by general,
    ICollectionsFunctionality by scrollCollections {

    private lateinit var searchBar: EditText

    private lateinit var deleteText: ImageButton
    private lateinit var makeSearch: ImageButton

    private lateinit var menuBar: LinearLayout
    private lateinit var tagsLayout: LinearLayout

    private var tag: String? = null

    private val viewModel: GroupViewModel by lazy {
        ViewModelProvider(this)[GroupViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

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
        overridePendingTransition(0,0)
    }

    override fun loadViews() {
        this.searchBar = findViewById(R.id.search_search)
        this.menuBar = findViewById(R.id.search_menu_bar)
        this.tagsLayout = findViewById(R.id.search_tags_layout)
        this.deleteText = findViewById(R.id.search_delete_text)
        this.makeSearch = findViewById(R.id.search_make_search)

        this.generateDynamicTags(this.tagsLayout, this, this::getTagData)
    }

    override fun setDefaultValues() {
        LocalData.foundGroups.value = null
    }

    override fun attachListeners() {
        this.menuBarListeners(this.menuBar, this, ActivityTypes.SEARCH_ACTIVITY)

        this.deleteText.setOnClickListener {
            this.searchBar.text = null
        }

        this.makeSearch.setOnClickListener {
            this.searchGroups()
        }

        LocalData.foundGroups.observe(this) {

        }
    }

    override fun detachListeners() {
        this.deleteText.setOnClickListener(null)
        this.makeSearch.setOnClickListener(null)
    }

    private fun getTagData(text: String) {
        this.tag = text.ifEmpty { null }
    }

    private fun searchGroups() {
        val name = this.searchBar.text.toString()

        this.viewModel.searchGroups(
            category = if (this.tag.isNullOrBlank()) null else this.tag.toString(),
            name = name.ifBlank { null }
        )
    }
}