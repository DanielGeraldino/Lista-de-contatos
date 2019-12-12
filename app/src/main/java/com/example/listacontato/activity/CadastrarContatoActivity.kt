package com.example.listacontato.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.listacontato.Helper.DataBaseContato
import com.example.listacontato.R
import com.example.listacontato.model.Contato
import com.github.clans.fab.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_cadastrar_contato.*
import kotlinx.android.synthetic.main.activity_main.*

class CadastrarContatoActivity : AppCompatActivity() {

    private lateinit var contato: Contato
    private lateinit var nome: EditText
    private lateinit var textoNumero: String

    //private lateinit var numero: EditText
    private val bancoDados: DataBaseContato = DataBaseContato(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_contato)
        nome = editNome

        fabSalvarContato.setOnClickListener {
            salvarContato()
        }
    }

    fun salvarContato(){

        val textoNome = nome.text.toString()
        textoNumero = editTelefone.rawText!!

        if(!textoNome.isEmpty()){
            if(!textoNumero.isEmpty()){
                contato = Contato(textoNome, textoNumero.toLong())
                val status = bancoDados.addContato(contato)
                if(status > -1) {
                    Toast.makeText(applicationContext, "Contato cadastrado", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
                Toast.makeText(applicationContext, "Digite o numero", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(applicationContext, "Digite o nome!", Toast.LENGTH_SHORT).show()
        }
    }
}
