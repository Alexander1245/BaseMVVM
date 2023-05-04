package com.dart69.mvvm.recyclerview.decoration.linear

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

internal class VerticalMarginItemDecoration(
    private val margin: Int,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = margin
        }
        outRect.bottom = margin
        outRect.left = margin
        outRect.right = margin
    }
}