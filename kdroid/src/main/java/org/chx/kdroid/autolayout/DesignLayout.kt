package org.chx.kdroid.autolayout

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DesignLayout(context: Context, designSize: Int) {
    val ratio = 1F * context.applicationContext.resources.displayMetrics.widthPixels / designSize

    fun auto(view: View) {
        view.autoLayout()
        view.autoPadding()
        if (view is TextView) view.autoTextSize()
        if (view is ViewGroup) {
            for (i in 0..view.childCount - 1) {
                auto(view.getChildAt(i))
            }
        }
    }

    fun View.autoLayout() {
        layoutParams?.let {
            it.width = (it.width * ratio).toInt()
            it.height = (it.height * ratio).toInt()
            if (it is ViewGroup.MarginLayoutParams) {
                it.leftMargin = (it.leftMargin * ratio).toInt()
                it.topMargin = (it.topMargin * ratio).toInt()
                it.rightMargin = (it.rightMargin * ratio).toInt()
                it.bottomMargin = (it.bottomMargin * ratio).toInt()
            }
        }
    }

    fun View.autoPadding() {
        setPadding(
                (paddingLeft * ratio).toInt(),
                (paddingTop * ratio).toInt(),
                (paddingRight * ratio).toInt(),
                (paddingBottom * ratio).toInt()
        )
    }

    fun TextView.autoTextSize() {
        includeFontPadding = false
        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize * ratio)
    }
}