package com.example.oscar.appfororetrofit.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.oscar.appfororetrofit.R
import com.example.oscar.appfororetrofit.adapter.CommentAdapter
import com.example.oscar.appfororetrofit.api.ApiForoService
import com.example.oscar.appfororetrofit.model.ApiForoSingleton
import com.example.oscar.appfororetrofit.model.Comment

import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.content_comment.*
import org.jetbrains.anko.*

class CommentActivity : AppCompatActivity() {

    private lateinit var cliente: ApiForoService
    private lateinit var idpost: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        setSupportActionBar(toolbar)

        cliente = ApiForoSingleton.cliente as ApiForoService
        idpost = intent.getSerializableExtra("postPulsado") as String

        getComment()

        fab.setOnClickListener { view ->
            newComment()
        }
    }

    private fun newComment() {
        alert {
            customView {
                verticalLayout{
                    val etComment = editText{
                        hint = "Comentario"
                    }
                    positiveButton("Aceptar"){
                        addComent(etComment.text.toString())
                    }
                    negativeButton("Cancelar"){it.cancel()}
                }
            }
        }.show()
    }

    private fun addComent(comment: String) {
        val llamada = cliente.createComment(comment, idpost)
        doAsync {
            llamada.execute()
            uiThread {
                getComment()
            }
        }
    }

    private fun getComment(){
        val llamada = cliente.listaComment(idpost)
        doAsync {
            val listaComment = llamada.execute().body()
            uiThread {
                if (listaComment!=null){
                    pintarComment(listaComment)
                }else{
                    toast("Error")
                }
            }
        }
    }

    private fun pintarComment(listaComment: List<Comment>){
        listaComment.forEach {
            Log.d("commentResult", it.toString())
        }
        var adapter = CommentAdapter(this, R.layout.row_comment, listaComment)
        rvComment.layoutManager = LinearLayoutManager(this)
        rvComment.adapter = adapter
    }

}
