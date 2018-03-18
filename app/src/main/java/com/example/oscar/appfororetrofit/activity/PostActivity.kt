package com.example.oscar.appfororetrofit.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.oscar.appfororetrofit.R
import com.example.oscar.appfororetrofit.adapter.PostAdapter
import com.example.oscar.appfororetrofit.api.ApiForoService
import com.example.oscar.appfororetrofit.model.Post
import com.example.oscar.appfororetrofit.model.ApiForoSingleton

import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.content_post.*
import org.jetbrains.anko.*

class PostActivity : AppCompatActivity() {

    private lateinit var cliente: ApiForoService
    //private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        setSupportActionBar(toolbar)

        cliente = ApiForoSingleton.cliente as ApiForoService
        //username = intent.getSerializableExtra("username") as String
        getPosts()

        fab.setOnClickListener { view ->
            newPost()
        }
    }

    private fun newPost() {
        alert {
            customView {
                verticalLayout{
                    val etTitulo = editText{
                        hint = "titulo"
                    }
                    positiveButton("Aceptar"){
                        //val post = Post(etTitulo.text.toString(), username)
                        addPost(etTitulo.text.toString())
                    }
                    negativeButton("Cancelar"){it.cancel()}
                }
            }
        }.show()
    }

    private fun addPost(titulo: String) {
        val llamada = cliente.createPost(titulo)
        doAsync {
            llamada.execute()
            uiThread {
                getPosts()
            }
        }
    }

    private fun pintarPost(listaPost: List<Post>) {
        listaPost.forEach {
            Log.d("PostResult", it.toString())
        }
        var adapter = PostAdapter(this, R.layout.row_post, listaPost)
        rvPost.layoutManager = LinearLayoutManager(this)
        rvPost.adapter = adapter
    }

    private fun getPosts() {
        val llamada = cliente.listaPost()
        doAsync {
            val listaPost = llamada.execute().body()
            uiThread {
                if (listaPost!=null){
                    pintarPost(listaPost)
                }else{
                    toast("Error")
                }
            }
        }
    }

}
