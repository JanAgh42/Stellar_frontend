package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class CreateGroupActivity(
    general: IGeneralFunctionality = GeneralFunctionality(),
    scrollCollections: IScrollCollectionsFunctionality = ScrollCollectionsFunctionality()
) : AppCompatActivity(),
    IMandatoryOverrides,
    IGeneralFunctionality by general,
    IScrollCollectionsFunctionality by scrollCollections {

    private lateinit var groupExample: LinearLayout
    private lateinit var iconsLayout: LinearLayout
    private lateinit var tagsLayout: LinearLayout

    private lateinit var exampleIconBackground: LinearLayout
    private lateinit var exampleIcon: ImageView
    private lateinit var exampleName: TextView
    private lateinit var exampleNumMembers: TextView

    private lateinit var groupName: EditText

    private lateinit var groupBkg: LinearLayout

    private lateinit var groupBkgViolet: ImageButton
    private lateinit var groupBkgOrange: ImageButton
    private lateinit var groupBkgRed: ImageButton
    private lateinit var groupBkgBlue: ImageButton
    private lateinit var groupBkgGreen: ImageButton
    private lateinit var groupBkgPink: ImageButton
    private lateinit var groupBkgPurple: ImageButton
    private lateinit var groupBkgTurquoise: ImageButton

    private lateinit var groupIconBkg: LinearLayout

    private lateinit var groupIconBkgViolet: ImageButton
    private lateinit var groupIconBkgOrange: ImageButton
    private lateinit var groupIconBkgRed: ImageButton
    private lateinit var groupIconBkgBlue: ImageButton
    private lateinit var groupIconBkgGreen: ImageButton
    private lateinit var groupIconBkgPink: ImageButton
    private lateinit var groupIconBkgPurple: ImageButton
    private lateinit var groupIconBkgTurquoise: ImageButton

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
        this.groupBkgViolet = groupBkg.findViewById(R.id.group_violet)
        this.groupBkgOrange = groupBkg.findViewById(R.id.group_orange)
        this.groupBkgRed = groupBkg.findViewById(R.id.group_red)
        this.groupBkgBlue = groupBkg.findViewById(R.id.group_blue)
        this.groupBkgGreen = groupBkg.findViewById(R.id.group_green)
        this.groupBkgPink = groupBkg.findViewById(R.id.group_pink)
        this.groupBkgPurple = groupBkg.findViewById(R.id.group_purple)
        this.groupBkgTurquoise = groupBkg.findViewById(R.id.group_turquoise)
        this.groupIconBkg = findViewById(R.id.cgroup_icon_bkg_color)
        this.groupIconBkgViolet = groupIconBkg.findViewById(R.id.group_violet)
        this.groupIconBkgOrange = groupIconBkg.findViewById(R.id.group_orange)
        this.groupIconBkgRed = groupIconBkg.findViewById(R.id.group_red)
        this.groupIconBkgBlue = groupIconBkg.findViewById(R.id.group_blue)
        this.groupIconBkgGreen = groupIconBkg.findViewById(R.id.group_green)
        this.groupIconBkgPink = groupIconBkg.findViewById(R.id.group_pink)
        this.groupIconBkgPurple = groupIconBkg.findViewById(R.id.group_purple)
        this.groupIconBkgTurquoise = groupIconBkg.findViewById(R.id.group_turquoise)
        this.iconsLayout = findViewById(R.id.cgroup_icons_layout)
        this.tagsLayout = findViewById(R.id.cgroup_tags_layout)
        this.menuBar = findViewById(R.id.cgroup_menu_bar)
        this.topBar = findViewById(R.id.cgroup_top_bar)
        this.topGoBack = this.topBar.findViewById(R.id.top_back)
        this.topTitle = this.topBar.findViewById(R.id.top_title)
        this.topSettings = this.topBar.findViewById(R.id.top_settings)
        this.topButton = this.topBar.findViewById(R.id.top_button)
        this.topMessage = this.topBar.findViewById(R.id.top_add_message)

        this.generateDynamicIcons(this.iconsLayout, this, this::getTagData)
        this.generateDynamicTags(this.tagsLayout, this, this::getTagData)
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

    private fun getTagData(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}