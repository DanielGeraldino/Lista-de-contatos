package com.example.listacontato.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listacontato.R
import com.example.listacontato.adapter.ContatoAdapter
import com.example.listacontato.model.Contato
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tela_principal.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var recycleViewContato: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManeger: RecyclerView.LayoutManager
    private var listaContatos = listOf(
        Contato(1, "daniel", "995215421")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManeger = LinearLayoutManager(this) as RecyclerView.LayoutManager
        viewAdapter = ContatoAdapter(listaContatos)

        recycleViewContato = tela_principal.recyclerContato.apply {
            setHasFixedSize(true)
            layoutManager = viewManeger
            adapter = viewAdapter
        }

    }
}
