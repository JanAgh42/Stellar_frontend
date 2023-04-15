package com.example.stellar

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat

class GeneralFunctionality : IGeneralFunctionality {

    override fun changeActivity(
        current: Context,
        new: Class<*>,
        data: Map<String, java.io.Serializable>?)
    {
        val intent: Intent = Intent(current, new)

        data?.forEach { (key, value) ->
            intent.putExtra(key, value)
        }

        current.startActivity(intent)
    }

    override fun menuBarListeners(
        menuBar: View,
        context: Context,
        activity: ActivityTypes)
    {
        val menuHome = menuBar.findViewById<ImageButton>(R.id.menu_home)
        val menuProfile = menuBar.findViewById<ImageButton>(R.id.menu_profile)
        val menuGroup = menuBar.findViewById<ImageButton>(R.id.menu_group)
        val menuSearch = menuBar.findViewById<ImageButton>(R.id.menu_search)

        menuHome.background.setTint(ContextCompat.getColor(context, R.color.details_blue))
        menuProfile.background.setTint(ContextCompat.getColor(context, R.color.details_blue))
        menuGroup.background.setTint(ContextCompat.getColor(context, R.color.details_blue))
        menuSearch.background.setTint(ContextCompat.getColor(context, R.color.details_blue))

        when (activity) {
            ActivityTypes.MAIN_ACTIVITY -> {
                menuHome.background.setTint(ContextCompat.getColor(context, R.color.menu_blue))
            }
            ActivityTypes.PROFILE_ACTIVITY -> {
                menuProfile.background.setTint(ContextCompat.getColor(context, R.color.menu_blue))
            }
            ActivityTypes.CREATE_GROUP_ACTIVITY -> {
                menuGroup.background.setTint(ContextCompat.getColor(context, R.color.menu_blue))
            }
            ActivityTypes.SEARCH_ACTIVITY -> {
                menuSearch.background.setTint(ContextCompat.getColor(context, R.color.menu_blue))
            }
            else -> {}
        }

        menuHome.setOnClickListener {
            this.changeActivity(context, MainActivity::class.java)
        }

        menuProfile.setOnClickListener {
            this.changeActivity(context, ProfileActivity::class.java)
        }

        menuGroup.setOnClickListener {
            this.changeActivity(context, CreateGroupActivity::class.java)
        }

        menuSearch.setOnClickListener {
            this.changeActivity(context, SearchActivity::class.java)
        }
    }
}