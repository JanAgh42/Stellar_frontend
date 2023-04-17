package com.example.stellar.functionalities

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.example.stellar.enums.GroupIcons
import com.example.stellar.enums.GroupTags
import com.example.stellar.R
import com.example.stellar.interfaces.IScrollCollectionsFunctionality

class ScrollCollectionsFunctionality : IScrollCollectionsFunctionality {

    override fun generateDynamicTags(parent: LinearLayout, context: Context, callback: (text: String) -> Unit) {
        enumValues<GroupTags>().forEach {
            val tag: View = LayoutInflater.from(context).inflate(R.layout.app_tag, null)

            val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(this.marginToDp(10), 0, 0, 0)
            tag.layoutParams = layoutParams

            tag.setOnClickListener { view ->
                callback(
                    (view as FrameLayout).findViewById<TextView>(R.id.tag_title).text.toString()
                )
                this.changeSiblingColor(parent, context)

                view.backgroundTintList = ColorStateList
                    .valueOf(ContextCompat.getColor(context, R.color.group_orange))
            }

            tag.findViewById<TextView>(R.id.tag_title).text = it.groupName
            parent.addView(tag)
        }
    }

    override fun generateDynamicIcons(parent: LinearLayout, context: Context, callback: (tag: String) -> Unit) {
        enumValues<GroupIcons>().forEach {
            val view = ImageView(context)

            val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(0, 0, this.marginToDp(12), 0)
            view.layoutParams = layoutParams

            view.tag = it.iconName
            view.background = ContextCompat.getDrawable(context, it.resource)
            view.backgroundTintList = ColorStateList
                .valueOf(ContextCompat.getColor(context, R.color.white))

            view.setOnClickListener { imageView ->
                callback((imageView as ImageView).tag.toString())
            }

            parent.addView(view)
        }
    }

    private fun marginToDp(value: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            Resources.getSystem().displayMetrics).toInt()
    }

    private fun changeSiblingColor(parent: LinearLayout, context: Context) {
        parent.children.forEach {
            it.backgroundTintList = ColorStateList
                .valueOf(ContextCompat.getColor(context, R.color.details_blue))
        }
    }
}