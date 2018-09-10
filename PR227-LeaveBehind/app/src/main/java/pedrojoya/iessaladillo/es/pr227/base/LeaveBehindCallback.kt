package pedrojoya.iessaladillo.es.pr227.base

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class LeaveBehindCallback(dragDirs: Int, swipeDirs: Int) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    private var leftIconicDrawable: IconicDrawable? = null
    private var rightIconicDrawable: IconicDrawable? = null

    fun withLeftIconicDrawable(iconicDrawable: IconicDrawable): LeaveBehindCallback {
        this.leftIconicDrawable = iconicDrawable
        return this
    }

    fun withRightIconicDrawable(iconicDrawable: IconicDrawable): LeaveBehindCallback {
        this.rightIconicDrawable = iconicDrawable
        return this
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                             dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        if (dX < 0 && rightIconicDrawable != null) {
            showRightLeaveBehind(c, viewHolder, dX)
        } else if (dX > 0 && leftIconicDrawable != null) {
            showLeftLeaveBehind(c, viewHolder, dX)
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun showRightLeaveBehind(c: Canvas, viewHolder: RecyclerView.ViewHolder, dX: Float) {
        // Draw background.
        ColorDrawable().run {
            color = rightIconicDrawable!!.backgroundColor
            setBounds(viewHolder.itemView.right + dX.toInt(),
                    viewHolder.itemView.top, viewHolder.itemView.right,
                    viewHolder.itemView.bottom)
            draw(c)
        }
        // Draw icon.
        rightIconicDrawable!!.icon.run {
            val itemHeight = viewHolder.itemView.bottom - viewHolder.itemView.top
            val iconMargin = (itemHeight - intrinsicHeight) / 2
            val iconTop = viewHolder.itemView.top + (itemHeight - intrinsicHeight) / 2
            val iconLeft = viewHolder.itemView.right - iconMargin - intrinsicWidth
            val iconRight = viewHolder.itemView.right - iconMargin
            val iconBottom = iconTop + intrinsicHeight
            setBounds(iconLeft, iconTop, iconRight, iconBottom)
            draw(c)
        }
    }

    private fun showLeftLeaveBehind(c: Canvas, viewHolder: RecyclerView.ViewHolder, dX: Float) {
        // Draw background.
        ColorDrawable().run {
            color = leftIconicDrawable!!.backgroundColor
            setBounds(viewHolder.itemView.left, viewHolder.itemView.top,
                    viewHolder.itemView.left + dX.toInt(), viewHolder.itemView.bottom)
            draw(c)
        }
        // Draw icon.
        leftIconicDrawable!!.icon.run {
            val itemHeight = viewHolder.itemView.bottom - viewHolder.itemView.top
            val iconMargin = (itemHeight - intrinsicHeight) / 2
            val iconTop = viewHolder.itemView.top + (itemHeight - intrinsicHeight) / 2
            val iconLeft = viewHolder.itemView.left + iconMargin
            val iconRight = viewHolder.itemView.left + iconMargin + intrinsicWidth
            val iconBottom = iconTop + intrinsicHeight
            setBounds(iconLeft, iconTop, iconRight, iconBottom)
            draw(c)
        }
    }

}
