package com.example.mvvmrom.repository

import androidx.lifecycle.LiveData
import com.example.mvvmrom.room.GroupData
import com.example.mvvmrom.room.StudentDao
import com.example.mvvmrom.room.StudentData

class StudentRepository(private val studentDao: StudentDao) : StudentDao{

    override fun deleteStudentByGroup(groupId: Int) {
        studentDao.deleteStudentByGroup(groupId)
    }

    override fun updateGroup(group: GroupData) {
         studentDao.updateGroup(group)
    }

    override fun deleteGroup(groupId: Int) {
        studentDao.deleteGroup(groupId)
    }

    override fun updateStudent(firstName: String, lastName: String, groupId: Int) {
        studentDao.updateStudent(firstName,lastName,groupId)
    }

    override fun deleteStudent(groupId: Int, studentId: Int) {
        studentDao.deleteStudent(groupId,studentId)
    }

    override fun getAllStudents(groupId:Int): LiveData<List<StudentData>> {
        return studentDao.getAllStudents(groupId)
    }

    override fun getAllGroups(): LiveData<List<GroupData>> {
        return studentDao.getAllGroups()
    }

    override fun insertStudent(student: StudentData) {
        studentDao.insertStudent(student)
    }

    override fun insertGroup(group: GroupData) {
            studentDao.insertGroup(group)
    }
}