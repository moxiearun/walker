package com.example.mukesh.walker.base


import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View

open class BaseActivity : AppCompatActivity() {

    fun showSnackBarMessage(view : View, messageResId : Int) {
        Snackbar.make(view, messageResId, Snackbar.LENGTH_LONG).show()
    }

    fun showSnackBarMessage(view : View, message : String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

}