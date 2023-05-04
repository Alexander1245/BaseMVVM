package com.dart69.mvvm.recyclerview.decoration.linear

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class MarginItemDecoration(
    private val margin: Int = DEFAULT_MARGIN,
    private val orientation: Int = LinearLayoutManager.VERTICAL,
) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val decoration = if (orientation == LinearLayoutManager.VERTICAL) {
            VerticalMarginItemDecoration(margin)
        } else {
            HorizontalMarginItemDecoration(margin)
        }
        decoration.getItemOffsets(outRect, view, parent, state)
    }

    companion object {
        const val DEFAULT_MARGIN = 8
    }
}