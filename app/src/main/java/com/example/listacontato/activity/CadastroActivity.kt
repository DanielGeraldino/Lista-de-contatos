package com.example.listacontato.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.listacontato.Helper.DataBaseContato
import com.example.listacontato.R
import com.example.listacontato.model.Contato
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlin.properties.Delegates

class CadastroActivity : AppCompatActivity() {

    private lateinit var contato: Contato
    //private val dados = DataBaseContato(applicationContext)

    private lateinit var nome: EditText
    private lateinit var numero: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        contato = intent.getSerializableExtra("contato") as Contato

        nome = editNome
        numero = editTelefone

        val textoNome = contato.nome
        val textoNumero = contato.numero.toString()

        nome.setText(textoNome)
        numero.setText(textoNumero)

    }
}

