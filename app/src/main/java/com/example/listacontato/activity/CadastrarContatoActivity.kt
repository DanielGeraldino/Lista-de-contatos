package com.example.listacontato.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.listacontato.Helper.DataBaseContato
import com.example.listacontato.R
import com.example.listacontato.model.Contato
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_cadastrar_contato.*

class CadastrarContatoActivity : AppCompatActivity() {

    private lateinit var contato: Contato
    private lateinit var nome: EditText
    private lateinit var numero: EditText
    private val bancoDados: DataBaseContato = DataBaseContato(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_contato)

        nome = editNome
        numero = editTelefone
    }

    fun salvarContato(v: View){

        val textoNome = nome.text.toString()
        val textoNumero = numero.text.toString()

        if(!textoNome.isEmpty()){
            if(!textoNumero.isEmpty()){
                contato = Contato(textoNome, textoNumero.toInt())
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
