package com.example.mvvmrom.dialogs.sudenet

import android.content.Context
import android.view.View
import com.example.mvvmrom.R
import com.example.mvvmrom.dialogs.BaseDialog
import com.example.mvvmrom.room.StudentData
import kotlinx.android.synthetic.main.add_student_dialog.view.*

class AddStudentDialog(context: Context) :BaseDialog(context, R.layout.add_student_dialog){
var listener:((StudentData)->Unit)?=null
    override fun applyView(view: View) {
        view.apply {
            addStudentButton.setOnClickListener {
                val name=inputStudentName.text.toString()
                val surname=inputStudentSurname.text.toString()
                val age=inputStudentAge.text.toString()
                var valid=true

                if (name.isEmpty()){
                    inputStudentName.error="name is not empty"
                    valid=false
                }
                if (surname.isEmpty()){
                    inputStudentSurname.error="surname is not empty"
                    valid=false
                }
                if (age.isEmpty()){
                    inputStudentAge.error="age is not empty"
                    valid=false
                }
                if (valid){
                    listener?.invoke(StudentData(0,name,surname))
                    hideDialog()
                }

            }
            cancelStudentButton.setOnClickListener { hideDialog() }
        }
    }
}