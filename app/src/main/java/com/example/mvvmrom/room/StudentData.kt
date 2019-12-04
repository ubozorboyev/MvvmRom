package com.example.mvvmrom.room

import androidx.room.*

@Entity(tableName = "stdudents",foreignKeys =
     arrayOf(ForeignKey(entity = GroupData::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("group_id"),
        onDelete = ForeignKey.NO_ACTION
        )
     ))
data class StudentData(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "first_name")var firstName:String,
    @ColumnInfo(name = "last_name")var lastName:String,
    @ColumnInfo(name = "group_id",index = true)var group_id:Int?=null,
    @ColumnInfo(name = "age") var age:Int=22
)
@Entity(tableName = "groups",indices = arrayOf(Index(value= arrayOf("name"),unique = true)))
data class GroupData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name="name")var name:String
)