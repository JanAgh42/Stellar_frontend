package com.example.stellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class CreateGroupActivity(
    scrollCollections: IScrollCollectionsFunctionality = ScrollCollectionsFunctionality()
) : AppCompatActivity(),
    IScrollCollectionsFunctionality by scrollCollections{

    private lateinit var groupExample: LinearLayout
    private lateinit var iconsLayout: LinearLayout

    private lateinit var exampleBackground: LinearLayout
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_group)

        this.groupName = findViewById(R.id.cgroup_group_name)
        this.groupExample = findViewById(R.id.cgroup_example_group)
        this.exampleBackground = groupExample.findViewById(R.id.group_bkg)
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
    }
}