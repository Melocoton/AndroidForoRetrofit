package com.example.oscar.appfororetrofit.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.oscar.appfororetrofit.model.Comment
import kotlinx.android.synthetic.main.row_comment.view.*

/**
 * Created by Oscar on 18/03/2018.
 */
class CommentAdapter (val context: Context, val layout:Int, val dataList: List<Comment>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>(){

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

        fun bind(dataItem: Comment) {

            itemView.txtAutor.text = dataItem.user_username
            itemView.txtComentario.text = dataItem.comment

        }
    }
}