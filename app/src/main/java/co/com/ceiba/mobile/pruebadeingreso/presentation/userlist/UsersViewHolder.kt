package co.com.ceiba.mobile.pruebadeingreso.presentation.userlist

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.UsersUI
import co.com.ceiba.mobile.pruebadeingreso.utils.OnItemSelected

class UsersViewHolder(private val view: View, private val onItemSelectedListener: OnItemSelected<UsersUI>) : RecyclerView.ViewHolder(view) {

    fun bind(item: UsersUI) {

        val name = view.findViewById<TextView>(R.id.name)
        name.text = item.name

        val email = view.findViewById<TextView>(R.id.email)
        email.text = item.email

        val phone = view.findViewById<TextView>(R.id.phone)
        phone.text = item.phone

        val btnShowPost = view.findViewById<Button>(R.id.btn_view_post)

        btnShowPost.setOnClickListener {
            onItemSelectedListener.onItemSelected(item)
        }
    }
}