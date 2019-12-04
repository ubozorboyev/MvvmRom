package com.example.mvvmrom.ui.students

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmrom.R
import com.example.mvvmrom.room.StudentData

class StudentAdapter :RecyclerView.Adapter<StudentAdapter.ViewHolder>(){
private var data= emptyList<StudentData>()
var listener:((StudentData)->Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.student_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount()=data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val studentData=data[position]
        holder.fistName.text=studentData.firstName
        holder.lastName.text=studentData.lastName
        holder.age.text=studentData.age.toString()
        holder.itemView.setOnClickListener { listener!!(studentData) }
    }

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val fistName=view.findViewById<TextView>(R.id.studentFirstName)
        val lastName=view.findViewById<TextView>(R.id.studentLastName)
        val age=view.findViewById<TextView>(R.id.studentAge)
    }

    fun setStudentList(list:List<StudentData>){
        data=list
        notifyDataSetChanged()
    }

}