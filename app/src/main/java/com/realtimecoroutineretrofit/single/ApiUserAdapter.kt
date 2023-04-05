package com.realtimecoroutineretrofit.single

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlincoroutinesdemo.R
import com.realtimecoroutineretrofit.model.ApiUser

class ApiUserAdapter(private val users: ArrayList<ApiUser>) : RecyclerView.Adapter<ApiUserAdapter.DataViewHolder>() {
    class DataViewHolder(item: View) : RecyclerView.ViewHolder(item){
        val textViewUserName: TextView = item.findViewById(R.id.textViewUserName)
        val textViewUserEmail: TextView = item.findViewById(R.id.textViewUserEmail)
        fun bind(user: ApiUser){
            textViewUserName.text = user.name
            textViewUserEmail.text = user.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addData(list: List<ApiUser>){
        users.addAll(list)
    }
}