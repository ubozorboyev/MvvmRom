package com.example.mvvmrom.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {
    @Query("SELECT * FROM groups")
    fun getAllGroups():LiveData<List<GroupData>>

    @Insert(entity = GroupData::class,onConflict = OnConflictStrategy.ABORT)
    fun insertGroup(group: GroupData)

    @Update(entity = GroupData::class,onConflict = OnConflictStrategy.REPLACE)
    fun updateGroup(group: GroupData)

    @Query("DELETE FROM groups WHERE id=:groupId")
    fun deleteGroup(groupId: Int)

    @Query("DELETE FROM stdudents WHERE group_id=:groupId")
    fun deleteStudentByGroup(groupId: Int)

    @Query("SELECT * FROM stdudents WHERE group_id=:groupId")
    fun getAllStudents(groupId:Int):LiveData<List<StudentData>>

    @Insert(entity = StudentData::class,onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(student: StudentData)

    @Query("UPDATE stdudents set first_name=:firstName, last_name=:lastName WHERE group_id=:groupId")
    fun updateStudent(firstName:String,lastName:String, groupId: Int)

    @Query("DELETE FROM stdudents WHERE id=:studentId AND group_id=:groupId ")
    fun deleteStudent(groupId: Int,studentId:Int)
}