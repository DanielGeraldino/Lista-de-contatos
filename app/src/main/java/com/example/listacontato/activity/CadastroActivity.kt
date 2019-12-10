package com.example.listacontato.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
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
        finish()
    }

    fun alterarContato(v: View){
        dados.editarContato(contato)
        finish()
    }
}

