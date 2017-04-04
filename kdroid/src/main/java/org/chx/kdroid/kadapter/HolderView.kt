package org.chx.kdroid.kadapter

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.chx.kdroid.kandy.etc.inflateLayout

abstract class HolderView<in D>(itemView: View) : LinearLayout(itemView.context) {
    init {
        setMeasuredDimension(itemView.measuredWidth, itemView.measuredHeight)
        layoutParams = itemView.layoutParams
        this.addView(itemView)
    }

    abstract fun convert(data: D, position: Int)

    companion object {
        fun <D> with(container: ViewGroup, layoutRes: Int, func: HolderView<D>.(D, Int) -> Unit): HolderView<D> =
                object : HolderView<D>(container.inflateLayout(layoutRes, false)) {
                    override fun convert(data: D, position: Int) = func(data, position)
                }
    }

    abstract class Factory<D>(dataList: List<D>) : List<D> by dataList {
        abstract fun createView(container: ViewGroup, position: Int): HolderView<D>

        fun bindView(holder: HolderView<D>, position: Int) {
            holder.convert(this[position], position)
        }
    }
}