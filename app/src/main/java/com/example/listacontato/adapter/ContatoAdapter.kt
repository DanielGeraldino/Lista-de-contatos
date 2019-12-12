package com.example.listacontato.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.listacontato.Helper.DataBaseContato
import com.example.listacontato.R
import com.example.listacontato.Util.transformaByteArrayEmUri
import com.example.listacontato.activity.CadastroActivity
import com.example.listacontato.model.Contato
import kotlinx.android.synthetic.main.model_recycler_contato.view.*

class ContatoAdapter(
    private val listaContato: List<Contato>,
    val context: Context
) : RecyclerView.Adapter<ContatoAdapter.MyViewHolder>(){

    class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup, val context: Context) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.model_recycler_contato, parent, false))
    {

        private lateinit var contato: Contato
        private var nome: TextView = itemView.tNome
        private var numero: TextView = itemView.tNumero
        private var image: ImageView = itemView.imgContato
        private var id: Int? = null

        init{
            itemView.setOnClickListener { _ ->
                val intent = Intent(context, CadastroActivity::class.java)
                intent.putExtra("contato", contato)
                context.startActivity(intent)
            }
        }

        fun preencheDadoContato(contato: Contato) {

            this.contato = contato
            nome.text = contato.nome
            numero.text = contato.numero.toString()
            id = contato.id
            image.setImageURI(
                contato.imageArrayByte?.let { transformaByteArrayEmUri(it) }
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater, parent, context)
    }

    override fun getItemCount(): Int {
        return listaContato.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contato: Contato = listaContato[position]

        holder.preencheDadoContato(contato)
    }

}
