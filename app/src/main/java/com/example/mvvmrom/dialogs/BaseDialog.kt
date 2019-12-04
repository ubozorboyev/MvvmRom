package com.example.mvvmrom.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog

abstract class BaseDialog(context: Context, @LayoutRes val resId:Int) {
private val dialog=AlertDialog.Builder(context).create()
private val view=LayoutInflater.from(context).inflate(resId,null,false)

    init {
        dialog.setView(view)
    }

    fun showDialog(){
        dialog.show()
        applyView(view)
    }

    fun hideDialog(){ dialog.dismiss() }

    abstract fun applyView(view: View)
}