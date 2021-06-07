package com.example.androidservice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*
import java.io.File

class FilesAdapter(val fileList: ArrayList<File>) : RecyclerView.Adapter<FilesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilesViewHolder {
        return FilesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        );
    }

    override fun onBindViewHolder(holder: FilesViewHolder, position: Int) {
        val file = fileList[position]
        holder.setData(file)

    }

    override fun getItemCount(): Int {
        return fileList.size
    }
}

class FilesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setData(file: File) {
        itemView.itemFileName.text = "Name : " + file.name.toString()
        itemView.itemFileType.text = "Path : " + file.absolutePath.toString()
    }
}