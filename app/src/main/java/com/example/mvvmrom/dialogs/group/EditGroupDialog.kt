package com.example.mvvmrom.dialogs.group

import android.content.Context
import android.util.Log
import android.view.View
import com.example.mvvmrom.R
import com.example.mvvmrom.dialogs.BaseDialog
import com.example.mvvmrom.room.GroupData
import kotlinx.android.synthetic.main.edit_group_dialog.view.*


class EditGroupDialog(context: Context, position: Int, val data:List<GroupData>):
    BaseDialog(context, R.layout.edit_group_dialog) {
var listener:((GroupData)->Unit)?=null
private val group=data.get(position)

    override fun applyView(view: View) {
        view.apply {
            editGroupName.setText(group.name)
            cancelEditButton.setOnClickListener { hideDialog() }
            okEditButton.setOnClickListener {
                val name=editGroupName.text.toString()
                val d=GroupData(group.id,name)
                Log.d("FFF","group $group  newGroup $d")
                if (name.isEmpty()){
                    editGroupName.error="name is can not empty"
                }
                else if (data.contains(d) && group!=d){
                    editGroupName.error="name already exists"
                }else{
                    group.name=name
                    listener?.invoke(group)
                    hideDialog()
                }
            }
        }
    }
}