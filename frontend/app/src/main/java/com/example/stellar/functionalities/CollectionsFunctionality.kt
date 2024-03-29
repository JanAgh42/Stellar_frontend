package com.example.stellar.functionalities

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.example.stellar.enums.GroupIcons
import com.example.stellar.enums.GroupTags
import com.example.stellar.R
import com.example.stellar.interfaces.ICollectionsFunctionality

class CollectionsFunctionality : ICollectionsFunctionality {

    override fun generateDynamicTags(parent: LinearLayout, context: Context, callback: (text: String) -> Unit) {
        enumValues<GroupTags>().forEach {
            val tag: View = LayoutInflater.from(context).inflate(R.layout.app_tag, null)

            val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(this.marginToDp(10), 0, 0, 0)
            tag.layoutParams = layoutParams

            tag.setOnClickListener { view ->
                val wasSelected = view.backgroundTintList == ColorStateList
                    .valueOf(ContextCompat.getColor(context, R.color.group_orange))

                callback(
                    if (wasSelected)
                    (view as FrameLayout).findViewById<TextView>(R.id.tag_title).text.toString()
                    else ""
                )
                this.changeSiblingColor(parent, context)

                if (!wasSelected) {
                    view.backgroundTintList = ColorStateList
                        .valueOf(ContextCompat.getColor(context, R.color.group_orange))
                }
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

    override fun generateDynamicColors(parent: LinearLayout, context: Context, callback: (text: String) -> Unit, colors: List<Int>, isGroup: Boolean) {
        val colorsNum = colors.size

        for ((currentColor, color) in colors.withIndex()) {
            val iconShape = FrameLayout(context)
            val iconBorder = ImageButton(context)
            val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            iconShape.layoutParams = layoutParams
            iconShape.background = ContextCompat.getDrawable(context, R.drawable.select_color_circle)
            iconShape.backgroundTintList = ColorStateList
                .valueOf(ContextCompat.getColor(context, color))
            iconBorder.tag = color

            iconBorder.layoutParams = layoutParams
            iconBorder.background = null
            iconShape.addView(iconBorder)

            parent.addView(iconShape)

            if (currentColor < colorsNum) {
                val view = View(context)
                val viewParams = LayoutParams(LayoutParams.WRAP_CONTENT, 0, 1.0f)
                view.layoutParams = viewParams
                parent.addView(view)
            }
        }
        if(isGroup) {
            makeColorButtonsClickable(parent, context, callback)
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

    private fun changeSiblingBorder(parent: LinearLayout) {
        for ((index, child) in parent.children.withIndex()) {
            if (index % 2 != 0) {
                continue
            }
            val layout = child as FrameLayout
            layout.getChildAt(0).background = null
        }
    }

    override fun makeColorButtonsClickable(parent: LinearLayout, context: Context, callback: (text: String) -> Unit) {
        for ((index, child) in parent.children.withIndex()) {
            if (index % 2 != 0) {
                continue
            }
            val iconShape = child as FrameLayout
            val iconBorder = iconShape.getChildAt(0)

            iconShape.setOnClickListener{ btn ->
                callback((btn as FrameLayout).getChildAt(0).tag.toString())
                this.changeSiblingBorder(parent)
                iconBorder.background = ContextCompat.getDrawable(context, R.drawable.color_btn_border)
            }
            iconBorder.setOnClickListener{ btn ->
                callback(btn.tag.toString())
                this.changeSiblingBorder(parent)
                iconBorder.background = ContextCompat.getDrawable(context, R.drawable.color_btn_border)
            }
        }
    }
}