package com.example.awsapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amplifyframework.datastore.generated.model.User

class MessageListAdapter(private val list: List<User>) : RecyclerView.Adapter<MessageListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_design, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
       var textView: TextView = itemView.findViewById(R.id.text)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = list[position]

        holder.textView.text = itemsViewModel.name.toString()
        Log.i("listadapter", itemsViewModel.name.toString())
    }

    override fun getItemCount(): Int {
        return list.size
    }

}