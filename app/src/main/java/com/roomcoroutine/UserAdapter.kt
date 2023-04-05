package me.amitshekhar.learn.kotlin.coroutines.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlincoroutinesdemo.R
import com.roomcoroutine.entity.User

import kotlinx.android.synthetic.main.item_layout.view.*


class UserAdapter(
    private val users: ArrayList<User>
) : RecyclerView.Adapter<UserAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.textViewUserName.text = user.name
            itemView.textViewUserEmail.text = user.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: List<User>) {
        users.addAll(list)
    }

}