package com.example.listacontato.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listacontato.Helper.DataBaseContato
import com.example.listacontato.R
import com.example.listacontato.adapter.ContatoAdapter
import com.example.listacontato.model.Contato
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tela_principal.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var recycleViewContato: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManeger: RecyclerView.LayoutManager
    private lateinit var listaContatos: List<Contato>
    private val bancoDados: DataBaseContato = DataBaseContato(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaContatos = carregaListaContato()

        viewManeger = LinearLayoutManager(this) as RecyclerView.LayoutManager
        viewAdapter = ContatoAdapter(listaContatos)

        recycleViewContato = tela_principal.recyclerContato.apply {
            setHasFixedSize(true)
            layoutManager = viewManeger
            adapter = viewAdapter
        }

    }

    override fun onStart() {
        super.onStart()
        listaContatos = carregaListaContato()
        recycleViewContato.adapter = ContatoAdapter(listaContatos)
        recycleViewContato.adapter?.notifyDataSetChanged()

        Toast.makeText(applicationContext, "OnStat", Toast.LENGTH_LONG).show()
    }

    fun cadastrarContato(v: View) = startActivity(Intent(this, CadastrarContatoActivity::class.java))

    fun carregaListaContato() = bancoDados.viewContatos()
}
