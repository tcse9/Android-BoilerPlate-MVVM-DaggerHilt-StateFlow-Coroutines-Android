package com.taufiq.hiltdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.taufiq.hiltdemo.databinding.EachRowBinding
import com.taufiq.hiltdemo.model.Post

class PostAdapter(private var postList:List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private lateinit var binding:EachRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = EachRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        binding.tasks.text = postList[position].body
    }

    override fun getItemCount() = postList.size

    fun setData(postList: List<Post>){
        this.postList = postList
        notifyDataSetChanged()
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

}