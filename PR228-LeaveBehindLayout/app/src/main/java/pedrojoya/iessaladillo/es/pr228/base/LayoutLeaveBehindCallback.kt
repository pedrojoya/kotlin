package pedrojoya.iessaladillo.es.pr228.base

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/*
    Usa elementos del layout como leave-behinds derecho e izquierdo, situados en el fondo. Se
    basa en sólo desplazar la vista foreground, cuyo fondo no puede ser transparente.
 */
abstract class LayoutLeaveBehindCallback(dragDirs: Int, swipeDirs: Int) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (viewHolder != null) {
            // Para que sólo afecte la vista foreground.
            ItemTouchHelper.Callback.getDefaultUIUtil().onSelected(getForegroundView(viewHolder))
        }
    }

    override fun onChildDrawOver(c: Canvas, recyclerView: RecyclerView,
                                 viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int,
                                 isCurrentlyActive: Boolean) {
        // Para que sólo afecte la vista foreground.
        ItemTouchHelper.Callback.getDefaultUIUtil().onDrawOver(c, recyclerView, getForegroundView(viewHolder), dX, dY, actionState,
                isCurrentlyActive)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        // Para que sólo afecte la vista foreground.
        ItemTouchHelper.Callback.getDefaultUIUtil().clearView(getForegroundView(viewHolder))
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                             dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        // Para que sólo afecte la vista foreground.
        ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(c, recyclerView, getForegroundView(viewHolder), dX, dY, actionState,
                isCurrentlyActive)
        val rightLeaveBehindView = getRightLeaveBehindView(viewHolder)
        val leftLeaveBehindView = getLeftLeaveBehindView(viewHolder)
        if (dX < 0) {
            if (rightLeaveBehindView != null) rightLeaveBehindView.visibility = View.VISIBLE
            if (leftLeaveBehindView != null) leftLeaveBehindView.visibility = View.INVISIBLE
        } else if (dX > 0) {
            if (rightLeaveBehindView != null) rightLeaveBehindView.visibility = View.INVISIBLE
            if (leftLeaveBehindView != null) leftLeaveBehindView.visibility = View.VISIBLE
        } else {
            if (rightLeaveBehindView != null) rightLeaveBehindView.visibility = View.INVISIBLE
            if (leftLeaveBehindView != null) leftLeaveBehindView.visibility = View.INVISIBLE
        }
    }

    protected abstract fun getForegroundView(viewHolder: RecyclerView.ViewHolder): View
    protected abstract fun getRightLeaveBehindView(viewHolder: RecyclerView.ViewHolder): View?
    protected abstract fun getLeftLeaveBehindView(viewHolder: RecyclerView.ViewHolder): View?

}
