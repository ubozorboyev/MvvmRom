package com.example.mvvmrom.ui.groups

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmrom.R
import com.example.mvvmrom.room.GroupData
import kotlinx.android.synthetic.main.group_item_layout.view.*

class GroupAdapter(val context: Context):RecyclerView.Adapter<GroupAdapter.ViewHolder>(){
private var data= emptyList<GroupData>()
 var editListener:((Int)->Unit)?=null
 var deleteListener:((Int)->Unit)?=null
 var listener:((GroupData)->Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     val view=LayoutInflater.from(parent.context).inflate(R.layout.group_item_layout,parent,false)
     return ViewHolder(view)
    }

    override fun getItemCount(): Int=data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val pos=data[position]
       holder.name.text=pos.name
       holder.itemView.setOnClickListener {listener?.invoke(pos) }
       holder.ButtonMore.setOnClickListener {
           val popupMenu=PopupMenu(context,it)
           popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)
           popupMenu.setOnMenuItemClickListener {
               when(it.itemId){
                   R.id.edit->{editListener?.invoke(position)}
                   R.id.delete->{deleteListener?.invoke(position)}
               }
               true
           }
           popupMenu.show()
       }
    }

     class ViewHolder(view:View):RecyclerView.ViewHolder(view){
     val name=view.findViewById<TextView>(R.id.groupName)
     val ButtonMore=view.findViewById<ImageButton>(R.id.moreVert)

  }
    fun setData(list:List<GroupData>){
        data=list
        notifyDataSetChanged()
    }
}