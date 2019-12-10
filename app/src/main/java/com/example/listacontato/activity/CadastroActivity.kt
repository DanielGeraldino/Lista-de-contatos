package com.example.listacontato.activity

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
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
import java.util.jar.Manifest
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            PERMISSION_CODE -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //PERMISSÃO CONCEDIDA
                    selecionaImagemGaleria()
                } else {
                    Toast.makeText(applicationContext, "Permissão negada para seleciona imagem!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            imageView.setImageURI(data?.data)
        }
    }

    fun adicionarFoto(v: View){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            // Verifica se tem permissão
            if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                //Solicita permissão
                val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)

                requestPermissions(permission, PERMISSION_CODE)
            } else {
                // TEM PERMISSÃO
                selecionaImagemGaleria()
            }
        } else {
            selecionaImagemGaleria()
        }
    }

    fun selecionaImagemGaleria(){

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"

        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
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

