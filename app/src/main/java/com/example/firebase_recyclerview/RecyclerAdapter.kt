package com.example.firebase_recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapter (var  list:ArrayList<ItemData>, var context: Context):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        var designImage: ImageView = itemView.findViewById(R.id.design_image)
        var title: TextView = itemView.findViewById(R.id.textvieww)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            var view = LayoutInflater.from(context).inflate(R.layout.design_layout,parent,false)
            return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem=list[position]

        Glide.with(context).load(currentItem.image).into(holder.designImage)
        holder.title.text=currentItem.tile
    }

    override fun getItemCount(): Int {
        return list.size
    }
}