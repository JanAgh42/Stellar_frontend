package com.example.stellar.functionalities

import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.example.stellar.R

class WatcherFunctionality(view: EditText) : TextWatcher {

    private var view: EditText
    private var validator: AuthFunctionality

    init {
        this.view = view
        this.validator = AuthFunctionality()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(p0: Editable?) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (this.validator.validatePassword(p0.toString())) {
            this.view.setTextColor(ContextCompat.getColor(this.view.context, R.color.group_green))
            this.view.backgroundTintList = ColorStateList
                .valueOf(ContextCompat.getColor(this.view.context, R.color.group_green))
        }
        else{
            this.view.setTextColor(ContextCompat.getColor(this.view.context, R.color.group_red))
            this.view.backgroundTintList = ColorStateList
                .valueOf(ContextCompat.getColor(this.view.context, R.color.group_red))
        }
    }
}