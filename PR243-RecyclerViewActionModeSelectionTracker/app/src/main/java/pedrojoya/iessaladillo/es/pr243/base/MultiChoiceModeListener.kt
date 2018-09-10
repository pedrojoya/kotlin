package pedrojoya.iessaladillo.es.pr243.base

import androidx.appcompat.view.ActionMode

// Extiende ActionMode.Callback para añadirle el evento de que cambia la selección.
interface MultiChoiceModeListener : ActionMode.Callback {

    fun onSelectionChanged(actionMode: ActionMode, selected: Int)

}