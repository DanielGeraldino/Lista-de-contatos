package com.example.listacontato.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.listacontato.Helper.DataBaseContato
import com.example.listacontato.R
import com.example.listacontato.model.Contato
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlin.properties.Delegates

class CadastroActivity : AppCompatActivity() {

    private lateinit var contato: Contato
    private lateinit var dados: DataBaseContato

    private lateinit var nome: EditText
    private lateinit var numero: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        dados = DataBaseContato(this)
        contato = intent.getSerializableExtra("contato") as Contato

        nome = editNome
        numero = editTelefone

        val textoNome = contato.nome
        val textoNumero = contato.numero.toString()

        nome.setText(textoNome)
        numero.setText(textoNumero)

    }

    fun deletaContato(v: View){

        contato.id?.let { dados.deleteContato(it) }
        Toast.makeText(applicationContext, "Contato deletado", Toast.LENGTH_SHORT).show()
        finish()
    }

    fun alterarContato(v: View){

        val textoNome: String = nome.text.toString()
        val textoNumero = numero.text.toString()

        if(!textoNome.isEmpty()){
            if(!textoNumero.isEmpty()){
                contato.nome = textoNome
                contato.numero = textoNumero.toInt()
                dados.editarContato(contato)

                Toast.makeText(applicationContext, "Alteração salvar!", Toast.LENGTH_SHORT).show()

                finish()
            } else {
                Toast.makeText(applicationContext, "Digite um numero para salvar!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(applicationContext, "Digite um nome para salvar!", Toast.LENGTH_SHORT).show()
        }
    }
}

