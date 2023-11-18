package com.example.stellar.functionalities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.stellar.*
import com.example.stellar.enums.ActivityTypes
import com.example.stellar.interfaces.IGeneralFunctionality

class GeneralFunctionality : IGeneralFunctionality {

    override fun changeActivity(
        current: Context,
        new: Class<*>,
        data: Map<String, java.io.Serializable>?)
    {
        val intent = Intent(current, new)

        data?.forEach { (key, value) ->
            intent.putExtra(key, value)
        }

        current.startActivity(intent)
    }

    override fun menuBarListeners(
        menuBar: View,
        context: Context,
        activity: ActivityTypes
    )
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

        if (!menuHome.hasOnClickListeners()) {
            menuHome.setOnClickListener {
                this.changeActivity(context, MainActivity::class.java)
            }
        }

        if (!menuProfile.hasOnClickListeners()) {
            menuProfile.setOnClickListener {
                this.changeActivity(context, ProfileActivity::class.java)
            }
        }

        if (!menuGroup.hasOnClickListeners()) {
            menuGroup.setOnClickListener {
                this.changeActivity(context, CreateGroupActivity::class.java)
            }
        }

        if (!menuSearch.hasOnClickListeners()) {
            menuSearch.setOnClickListener {
                this.changeActivity(context, SearchActivity::class.java)
            }
        }
    }

    override fun checkForPermissions(
        type: String,
        code: Int,
        appContext: Context,
        activity: Activity,
        callback: () -> Unit
    ) {
        if (ContextCompat.checkSelfPermission(appContext, type) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(type), code)
        }
        else {
            callback()
        }
    }

    override fun openPopupMenu(context: Context, view: View, menuId: Int, callback: (item: MenuItem) -> Unit) {
        val popup = PopupMenu(context, view)
        popup.menuInflater.inflate(menuId, popup.menu)

        popup.setOnMenuItemClickListener { item ->
            callback(item)
            true
        }
    }
}