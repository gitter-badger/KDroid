package org.chx.kdroid.sample

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_user.view.vUserName
import kotlinx.android.synthetic.main.item_ya.view.*
import org.chx.kandroid.R
import org.chx.kdroid.autolayout.DesignLayout
import org.chx.kdroid.kadapter.HolderView
import org.chx.kdroid.kadapter.KAdapter
import org.chx.kdroid.kadapter.adapter.adapt
import kotlinx.android.synthetic.main.item_user.view.vDescription as vUserDescription

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val design = DesignLayout(this, 480)
        design.auto(window.decorView)

        val dataList = (1..64).map { YaData(Math.random() > 0.5, "Title$it", "Description$it", "Detail$it") }

        KAdapter.with(dataList) { container, position ->
            if (dataList[position].isUser) {
                HolderView.with(container, R.layout.item_user) { data, _ ->
                    vUserName.text = data.title
                    vUserDescription.text = data.description
                }
            } else {
                HolderView.with<YaData>(container, R.layout.item_ya) { data, _ ->
                    vTitle.text = data.title
                    vDescription.text = data.description
                    vDetail.text = data.detail
                }.apply {
                    design.auto(this)
                }
            }
        }.adapt(vListView)
    }

    data class YaData(
            val isUser: Boolean,
            val title: String,
            val description: String,
            val detail: String
    )
}