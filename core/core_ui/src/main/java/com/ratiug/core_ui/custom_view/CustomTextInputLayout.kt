package com.ratiug.core_ui.custom_view

import android.content.Context
import android.graphics.ColorFilter
import android.util.AttributeSet
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.textfield.TextInputLayout

class CustomTextInputLayout : TextInputLayout {
    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    )

    override fun setError(error: CharSequence?) {
        val defaultColorFilter = backgroundDefaultColorFilter
        super.setError(error)
        //Reset EditText's background color to default.
        val layout = getChildAt(1)
        if (layout != null) {
            if (error != null && "" != error.toString().trim { it <= ' ' }) {
                layout.visibility = VISIBLE
            } else {
                layout.visibility = GONE
            }
        }
        updateBackgroundColorFilter(defaultColorFilter)
    }

    override fun drawableStateChanged() {
        val defaultColorFilter = backgroundDefaultColorFilter
        super.drawableStateChanged()
        //Reset EditText's background color to default.
        updateBackgroundColorFilter(defaultColorFilter)
    }

    private fun updateBackgroundColorFilter(colorFilter: ColorFilter?) {
        if (editText != null && editText!!.background != null) editText!!.background.colorFilter =
            colorFilter
    }

    private val backgroundDefaultColorFilter: ColorFilter?
        get() {
            var defaultColorFilter: ColorFilter? = null
            if (editText != null && editText!!.background != null) defaultColorFilter =
                DrawableCompat.getColorFilter(
                    editText!!.background
                )
            return defaultColorFilter
        }
}