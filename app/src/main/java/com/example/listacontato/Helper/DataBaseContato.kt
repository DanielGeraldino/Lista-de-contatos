package com.example.listacontato.Helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.listacontato.model.Contato

class DataBaseContato(context: Context) : SQLiteOpenHelper(context, DATA_BASE_NAME, null, DATA_BASE_VERSION) {

    private val dbWrite = this.writableDatabase

    companion object{
        private val DATA_BASE_VERSION = 1
        private val DATA_BASE_NAME = "contato"
        private val TABLE_CONTATO = "contatos"
        private val KEY_NOME_CONTATO = "nome"
        private val KEY_NUMERO_CONTATO = "numero"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_CONTATO = "CREATE TABLE" +
                TABLE_CONTATO + "(" +
                KEY_NUMERO_CONTATO + " INTEGER PRIMARY KEY," +
                KEY_NOME_CONTATO + " TEXT)"

        db?.execSQL(CREATE_TABLE_CONTATO)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun addContato(contato: Contato): Long{

        val contentValues = ContentValues()

        contentValues.put(KEY_NOME_CONTATO, contato.nome)
        contentValues.put(KEY_NUMERO_CONTATO, contato.numero)

        val sucesso = dbWrite.insert(TABLE_CONTATO, null,contentValues)

        return sucesso
    }

}
