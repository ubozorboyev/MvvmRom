package com.example.mvvmrom.dialogs.group

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.mvvmrom.R
import com.example.mvvmrom.dialogs.BaseDialog
import com.example.mvvmrom.room.GroupData
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.add_group_dialog.view.*

class AddGroupDialog(context: Context, val data:List<GroupData>):
    BaseDialog(context, R.layout.add_group_dialog) {
var listener:((GroupData)->Unit)?=null

    override fun applyView(view: View) {
        view.findViewById<Button>(R.id.addButton)
            .setOnClickListener {
                Log.d("TTT","Click")
                addGroup(view) }

         view.findViewById<Button>(R.id.cancelButton)
             .setOnClickListener { hideDialog() }
         view.findViewById<TextInputEditText>(R.id.inputGroupName).setText("")
    }

    fun addGroup(view: View){
        view.apply {
            val name=inputGroupName.text.toString()
            val d=GroupData(0,name)

            if (name.isEmpty()){
                inputGroupName.error="name is can not empty"
            }else if (data.contains(d)){
                inputGroupName.error="name already exists"
            }else{
                listener!!(d)
                hideDialog()
            }
        }
    }
}