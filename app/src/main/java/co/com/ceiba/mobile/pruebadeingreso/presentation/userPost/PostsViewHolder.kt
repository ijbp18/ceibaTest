package co.com.ceiba.mobile.pruebadeingreso.presentation.userPost

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.PostUI

class PostsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: PostUI) {

        val title = view.findViewById<TextView>(R.id.title)
        title.text = item.title

        val body = view.findViewById<TextView>(R.id.body)
        body.text = item.body

    }
}