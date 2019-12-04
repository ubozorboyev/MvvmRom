package com.example.mvvmrom.ui.students

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.mvvmrom.R
import com.example.mvvmrom.dialogs.LoaddingDialog
import com.example.mvvmrom.dialogs.sudenet.AddStudentDialog
import com.example.mvvmrom.room.StudentData
import kotlinx.android.synthetic.main.student_fragmnet.*

class StudentFragment :Fragment(R.layout.student_fragmnet){
private val groupId by lazy {  StudentFragmentArgs.fromBundle(arguments!!).groupId }
private val viewmodel:StudentViewModel by viewModels()
private val adapter=StudentAdapter()
private val dialog by lazy { LoaddingDialog(context!!) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewmodel.getStudentsByGroup(groupId)
        studentRecylaer.adapter=adapter
        adapter.listener=this::itemClick

        val observerList= Observer<List<StudentData>> {
            dialog.hideDialog()
            adapter.setStudentList(it)
        }
        viewmodel.studentList.observe(this.viewLifecycleOwner, observerList)
        addStudent.setOnClickListener {
            val dialog=AddStudentDialog(context!!)
            dialog.listener= { studentData: StudentData -> studentData.group_id=groupId; viewmodel.insetStudent(studentData) }
            dialog.showDialog()
        }
    }

     fun itemClick(student: StudentData) {
        Toast.makeText(context,"Item ${student.firstName} clicledd",Toast.LENGTH_SHORT).show()
     }
}