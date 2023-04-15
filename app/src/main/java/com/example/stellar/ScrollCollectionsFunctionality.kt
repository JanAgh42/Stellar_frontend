package com.example.stellar

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.core.content.ContextCompat

class ScrollCollectionsFunctionality : IScrollCollectionsFunctionality {

    override fun generateDynamicTags(parent: LinearLayout, context: Context) {
        enumValues<GroupTags>().forEach {
            val tag: View = LayoutInflater.from(context).inflate(R.layout.app_tag, null)

            val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(this.marginToDp(10), 0, 0, 0)
            tag.layoutParams = layoutParams

            tag.findViewById<TextView>(R.id.tag_title).text = it.groupName

            parent.addView(tag)
        }
    }

    override fun generateDynamicIcons(parent: LinearLayout, context: Context) {
        enumValues<GroupIcons>().forEach {
            val view: ImageView = ImageView(context)
            val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(0, 0, this.marginToDp(10), 0)
            view.layoutParams = layoutParams

            view.background = ContextCompat.getDrawable(context, it.resource)
            view.backgroundTintList = ColorStateList
                .valueOf(ContextCompat.getColor(context, R.color.white))

            parent.addView(view)
        }
    }

    private fun marginToDp(value: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            Resources.getSystem().displayMetrics).toInt()
    }
}