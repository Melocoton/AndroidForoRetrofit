package com.example.oscar.appfororetrofit.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.oscar.appfororetrofit.activity.CommentActivity
import com.example.oscar.appfororetrofit.model.Post
import kotlinx.android.synthetic.main.row_post.view.*

/**
 * Created by Oscar on 18/03/2018.
 */
class PostAdapter (val context: Context, val layout:Int, val dataList: List<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewLayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewLayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(viewLayout: View, val context: Context) : RecyclerView.ViewHolder(viewLayout) {

        fun bind(dataItem: Post) {

            itemView.txtTitulo.text = dataItem.post
            itemView.txtAutor.text = dataItem.user_username

            itemView.setOnClickListener({ onItemClick(dataItem) })

        }

        private fun onItemClick(dataItem: Post) {

            val intent = Intent(context, CommentActivity::class.java)
            intent.putExtra("postPulsado", dataItem.idpost)
//            context.startActivity(intent) as Activity
            (context as Activity).startActivityForResult(intent,0)


        }

    }

}