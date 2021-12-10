package co.com.ceiba.mobile.pruebadeingreso.presentation.userPost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.PostUI

class PostsAdapter : RecyclerView.Adapter<PostsViewHolder>(){

    private var postList: List<PostUI> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder =
            PostsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_list_item, parent, false))

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) = holder.bind(postList[position])

    fun setData(posts: List<PostUI>) {
        this.postList = posts
        notifyDataSetChanged()
    }
}