package com.dart69.mvvm.recyclerview.decoration.linear

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

internal class HorizontalMarginItemDecoration(
    private val margin: Int,
) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = margin
        }
        outRect.right = margin
        outRect.top = margin
        outRect.bottom = margin
    }
}