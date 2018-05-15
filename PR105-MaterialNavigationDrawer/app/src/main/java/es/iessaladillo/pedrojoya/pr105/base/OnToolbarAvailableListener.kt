package es.iessaladillo.pedrojoya.pr105.base

import android.support.v7.widget.Toolbar

interface OnToolbarAvailableListener {
    fun onToolbarAvailable(toolbar: Toolbar, title: String)
}
