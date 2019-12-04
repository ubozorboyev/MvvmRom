package com.example.mvvmrom.ui.students

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrom.repository.StudentRepository
import com.example.mvvmrom.room.AppDatabase
import com.example.mvvmrom.room.StudentData
import com.example.mvvmrom.ui.groups.ioThread
import kotlinx.coroutines.launch

class StudentViewModel :ViewModel(){
 private val repository:StudentRepository
 private val _studentList=MutableLiveData<List<StudentData>>()
 var studentList:LiveData<List<StudentData>> =_studentList

    init {
        val studentDao=AppDatabase.getInstanse().studentDao()
        repository= StudentRepository(studentDao)
    }

    fun insetStudent(studentData: StudentData)= ioThread {
        repository.insertStudent(studentData)
    }

    fun getStudentsByGroup(groupId:Int){
        studentList=repository.getAllStudents(groupId)
    }
}