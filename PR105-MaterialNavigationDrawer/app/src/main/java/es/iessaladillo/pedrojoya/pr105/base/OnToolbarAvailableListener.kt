package es.iessaladillo.pedrojoya.pr105.base

import androidx.appcompat.widget.Toolbar

interface OnToolbarAvailableListener {
    fun onToolbarAvailable(toolbar: Toolbar, title: String)
}
