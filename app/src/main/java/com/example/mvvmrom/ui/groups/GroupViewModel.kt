package com.example.mvvmrom.ui.groups

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmrom.repository.StudentRepository
import com.example.mvvmrom.room.AppDatabase
import com.example.mvvmrom.room.GroupData
import java.util.concurrent.Executors

class GroupViewModel :ViewModel(){
private val _groupList= MutableLiveData<List<GroupData>>()
var gropList: LiveData<List<GroupData>> =_groupList
private val repository:StudentRepository

    init {
        val studentDao=AppDatabase.getInstanse().studentDao()
        repository= StudentRepository(studentDao)
        gropList=repository.getAllGroups()
    }

    fun insertGroup(groupData: GroupData)=Executors.newSingleThreadExecutor().execute{
        try {
            repository.insertGroup(groupData)
        }catch (e:SQLiteConstraintException){
            e.printStackTrace()
        }
    }

    fun deleteGroup(groupId:Int) = ioThread {
//                repository.deleteStudentByGroup(groupId)
                repository.deleteGroup(groupId)
    }

    fun updateGroupData(groupData: GroupData)= ioThread{
        try {
            repository.updateGroup(groupData)
        }catch (e:SQLiteConstraintException){
            e.printStackTrace()
        }
    }
}
fun ioThread(f:()->Unit)=Executors.newSingleThreadExecutor().execute(f)