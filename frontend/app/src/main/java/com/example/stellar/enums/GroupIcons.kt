package com.example.stellar.enums

import androidx.annotation.DrawableRes
import com.example.stellar.R

enum class GroupIcons (@DrawableRes val resource: Int, val iconName: String) {
    BIOTECH(R.drawable.biotech, "biotech"),
    FOREST(R.drawable.forest, "forest"),
    LAPTOP(R.drawable.laptop_chromebook, "laptop_chromebook"),
    BOOK(R.drawable.menu_book, "menu_book"),
    GRILL(R.drawable.outdoor_grill, "outdoor_grill"),
    PALETTE(R.drawable.palette, "palette"),
    RADIO(R.drawable.radio, "radio"),
    GRAPH(R.drawable.monitoring, "monitoring")
}