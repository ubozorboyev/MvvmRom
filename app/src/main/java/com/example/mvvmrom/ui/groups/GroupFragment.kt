package com.example.mvvmrom.ui.groups

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mvvmrom.R
import com.example.mvvmrom.dialogs.group.AddGroupDialog
import com.example.mvvmrom.dialogs.group.EditGroupDialog
import com.example.mvvmrom.room.GroupData
import kotlinx.android.synthetic.main.group_fragment.*

class GroupFragment :Fragment(R.layout.group_fragment){
private val  viewModel: GroupViewModel by viewModels()
private val adapter by lazy { GroupAdapter(context!!)}
private val dialog by lazy { AddGroupDialog(context!!, data) }
private var data = listOf<GroupData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        gruopList.adapter=adapter
        adapter.listener=::startStudentFragment
        adapter.editListener=::editGroupData
        adapter.deleteListener=::deleteGroupData

        val observe=Observer<List<GroupData>>(::setLoadData)
        viewModel.gropList.observe(this.viewLifecycleOwner, observe)

        addGroup.setOnClickListener {
            dialog.showDialog()
            dialog.listener= { groupData: GroupData -> viewModel.insertGroup(groupData) }
        }
    }

    fun setLoadData(list:List<GroupData>){
        Log.d("TTT","list =$list")
        data=list
        adapter.setData(list)
    }

    fun startStudentFragment(groupData: GroupData){
        val action=GroupFragmentDirections.actionGroupFragmentToStudentFragment(groupData.id)
        findNavController().navigate(action)
    }

    fun editGroupData(position: Int){
    val editDialog= EditGroupDialog(context!!, position, data)
        editDialog.showDialog()
        editDialog.listener= { group: GroupData -> viewModel.updateGroupData(group) }
    }

    fun deleteGroupData(groupId:Int){
        Log.d("TTT","deleteGroupData$groupId")
        val deleteDialog=AlertDialog.Builder(context!!).create()
        deleteDialog.setMessage("Are you delete ?")
        deleteDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Cancel",DialogInterface.OnClickListener { dialogInterface, ins ->
            dialogInterface.dismiss()
        })
        deleteDialog.setButton(AlertDialog.BUTTON_POSITIVE,"Ok",DialogInterface.OnClickListener { dialogInterface, ins ->
         viewModel.deleteGroup(groupId)
         dialogInterface.dismiss()
        })
        deleteDialog.show()
    }
}