package com.example.listacontato.Helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import android.widget.Toast
import com.example.listacontato.model.Contato
import java.lang.Exception

class DataBaseContato(context: Context) : SQLiteOpenHelper(context, DATA_BASE_NAME, null, DATA_BASE_VERSION) {

    companion object{
        private val DATA_BASE_VERSION = 1
        private val DATA_BASE_NAME = "contato"
        private val TABLE_CONTATO = "contatos"
        private val KEY_ID = "id"
        private val KEY_NOME_CONTATO = "nome"
        private val KEY_NUMERO_CONTATO = "numero"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_CONTATO = "CREATE TABLE " +
                TABLE_CONTATO + "(" +
                KEY_ID + " integer PRIMARY KEY autoincrement," +
                KEY_NUMERO_CONTATO + " INT," +
                KEY_NOME_CONTATO + " TEXT)"

        db.execSQL(CREATE_TABLE_CONTATO)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTATO)
        onCreate(db)
    }

    fun addContato(contato: Contato): Long{
        val dbWrite = this.writableDatabase

        val contentValues = ContentValues()
        val nome: String = contato.nome
        val numero: Int = contato.numero

        contentValues.put(KEY_NOME_CONTATO, nome)
        contentValues.put(KEY_NUMERO_CONTATO, numero)

        //dbWrite.execSQL("INSERT TABLE $TABLE_CONTATO (numero, nome) VALUES ($numero, $nome)")

        val sucesso = dbWrite.insert(TABLE_CONTATO, null,contentValues)

        Log.i("db", sucesso.toString())
        Log.i("db_nome", contato.nome)
        Log.i("db_numero", contato.numero.toString())

        dbWrite.close()

        return sucesso
    }

    fun viewContatos(): List<Contato>{

        val dbRead = this.readableDatabase

        val listContato: ArrayList<Contato> = ArrayList<Contato>()
        val query = "SELECT * FROM $TABLE_CONTATO"
        var cursor: Cursor

        try{
            cursor = dbRead.rawQuery(query, null)
        } catch (e: SQLException){
            dbRead.execSQL(query)
            return listContato
        }

        var id: Int
        var nome: String
        var numero: Int

        if (cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                nome = cursor.getString(cursor.getColumnIndex(KEY_NOME_CONTATO))
                numero = cursor.getInt(cursor.getColumnIndex(KEY_NUMERO_CONTATO))

                listContato.add(Contato(id, nome, numero))

            } while (cursor.moveToNext())
        }

        return listContato
    }

    fun deleteContato(idContato: Int){

        val dbWrite = this.writableDatabase
        val query = "DELETE FROM $TABLE_CONTATO WHERE $KEY_ID = $idContato"

        dbWrite.execSQL(query)
    }

    fun editarContato(contato: Contato){

        val nome = contato.nome
        val numero = contato.numero
        val id = contato.id

        writableDatabase.execSQL("UPDATE $TABLE_CONTATO SET $KEY_NOME_CONTATO = '$nome', " +
                "$KEY_NUMERO_CONTATO = $numero " +
                "WHERE $KEY_ID = $id")

        Log.i("UPDATE", "NOME: " + nome)
    }
}
