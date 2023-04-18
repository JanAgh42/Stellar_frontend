package com.example.stellar

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.stellar.enums.ActivityTypes
import com.example.stellar.enums.Colors
import com.example.stellar.functionalities.GeneralFunctionality
import com.example.stellar.functionalities.CollectionsFunctionality
import com.example.stellar.interfaces.IGeneralFunctionality
import com.example.stellar.interfaces.IMandatoryOverrides
import com.example.stellar.interfaces.ICollectionsFunctionality

class CreateGroupActivity(
    general: IGeneralFunctionality = GeneralFunctionality(),
    scrollCollections: ICollectionsFunctionality = CollectionsFunctionality()
) : AppCompatActivity(),
    IMandatoryOverrides,
    IGeneralFunctionality by general,
    ICollectionsFunctionality by scrollCollections {

    private lateinit var groupExample: LinearLayout
    private lateinit var iconsLayout: LinearLayout
    private lateinit var tagsLayout: LinearLayout

    private lateinit var exampleIconBackground: LinearLayout
    private lateinit var exampleIcon: ImageView
    private lateinit var exampleName: TextView
    private lateinit var exampleNumMembers: TextView

    private lateinit var groupName: EditText

    private lateinit var groupBkg: LinearLayout

    private lateinit var groupIconBkg: LinearLayout

    private lateinit var menuBar: LinearLayout

    private lateinit var topBar: LinearLayout

    private lateinit var topGoBack: ImageButton
    private lateinit var topTitle: TextView
    private lateinit var topSettings: ImageButton
    private lateinit var topButton: TextView
    private lateinit var topMessage: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_group)

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
    }

    override fun loadViews() {
        this.groupName = findViewById(R.id.cgroup_group_name)
        this.groupExample = findViewById(R.id.cgroup_example_group)
        this.exampleIconBackground = groupExample.findViewById(R.id.group_icon_bkg)
        this.exampleIcon = groupExample.findViewById(R.id.group_icon)
        this.exampleName = groupExample.findViewById(R.id.group_name)
        this.exampleNumMembers = groupExample.findViewById(R.id.group_num_members)
        this.groupBkg = findViewById(R.id.cgroup_bkg_color)
        this.groupIconBkg = findViewById(R.id.cgroup_icon_bkg_color)
        this.iconsLayout = findViewById(R.id.cgroup_icons_layout)
        this.tagsLayout = findViewById(R.id.cgroup_tags_layout)
        this.menuBar = findViewById(R.id.cgroup_menu_bar)
        this.topBar = findViewById(R.id.cgroup_top_bar)
        this.topGoBack = this.topBar.findViewById(R.id.top_back)
        this.topTitle = this.topBar.findViewById(R.id.top_title)
        this.topSettings = this.topBar.findViewById(R.id.top_settings)
        this.topButton = this.topBar.findViewById(R.id.top_button)
        this.topMessage = this.topBar.findViewById(R.id.top_add_message)

        this.generateDynamicIcons(this.iconsLayout, this, this::getIconData)
        this.generateDynamicTags(this.tagsLayout, this, this::getTagData)
        this.generateDynamicColors(this.groupBkg, this, this::getGroupColorData, Colors.groupColors)
        this.generateDynamicColors(this.groupIconBkg, this, this::getIconColorData, Colors.groupColors)
    }

    override fun setDefaultValues() {
        this.topTitle.visibility = View.INVISIBLE
        this.topSettings.visibility = View.INVISIBLE
        this.topMessage.visibility = View.INVISIBLE
        this.topButton.visibility = View.VISIBLE

        this.topButton.setText(R.string.cgroup_create_button)
    }

    override fun attachListeners() {
        this.menuBarListeners(this.menuBar, this, ActivityTypes.CREATE_GROUP_ACTIVITY)

        this.topGoBack.setOnClickListener {
            finish()
        }

        this.topButton.setOnClickListener {
            finish()
        }
    }

    override fun detachListeners() {
        this.topGoBack.setOnClickListener(null)
        this.topButton.setOnClickListener(null)
    }

    private fun getIconData(text: String) {
        val icon = this.resources.getIdentifier(text, "drawable", this.packageName)
        this.exampleIcon.background = ContextCompat.getDrawable(this, icon)
    }

    private fun getTagData(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    private fun getGroupColorData(text: String) {
        this.groupExample.backgroundTintList = ColorStateList
            .valueOf(ContextCompat.getColor(this, text.toInt()))
        Toast.makeText(this, "group " + text, Toast.LENGTH_LONG).show()
    }

    private fun getIconColorData(text: String) {
        this.exampleIconBackground.backgroundTintList = ColorStateList
            .valueOf(ContextCompat.getColor(this, text.toInt()))
        Toast.makeText(this, "anyad " + text, Toast.LENGTH_LONG).show()
    }
}