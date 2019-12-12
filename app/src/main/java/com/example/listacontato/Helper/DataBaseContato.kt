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
        private val KEY_IMAGEM_CONTATO = "image"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_CONTATO = "CREATE TABLE " +
                TABLE_CONTATO + "(" +
                KEY_ID + " integer PRIMARY KEY autoincrement," +
                KEY_NUMERO_CONTATO + " INT," +
                KEY_NOME_CONTATO + " TEXT, " +
                KEY_IMAGEM_CONTATO + " BLOD)"

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
        val imageArray: ByteArray? = contato.imageArrayByte

        contentValues.put(KEY_NOME_CONTATO, nome)
        contentValues.put(KEY_NUMERO_CONTATO, numero)
        imageArray.let{ contentValues.put(KEY_IMAGEM_CONTATO, imageArray)}

        //dbWrite.execSQL("INSERT TABLE $TABLE_CONTATO (numero, nome) VALUES ($numero, $nome)")

        val sucesso = dbWrite.insert(TABLE_CONTATO, null,contentValues)

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
        var imageArray: ByteArray?

        if (cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                nome = cursor.getString(cursor.getColumnIndex(KEY_NOME_CONTATO))
                numero = cursor.getInt(cursor.getColumnIndex(KEY_NUMERO_CONTATO))
                imageArray = cursor.getBlob(cursor.getColumnIndex(KEY_IMAGEM_CONTATO))

                listContato.add(Contato(id, nome, numero))
                //listContato.add(Contato(id, nome, numero, imageArray))
            } while (cursor.moveToNext())
        }

        return listContato
    }

    fun deleteContato(idContato: Int){

        val dbWrite = this.writableDatabase
        val query = "DELETE FROM $TABLE_CONTATO WHERE $KEY_ID = $idContato"

        dbWrite.execSQL(query)
        dbWrite.close()
    }

    fun editarContato(contato: Contato){

        val nome = contato.nome
        val numero = contato.numero
        val id = contato.id

        writableDatabase.execSQL("UPDATE $TABLE_CONTATO SET $KEY_NOME_CONTATO = '$nome', " +
                "$KEY_NUMERO_CONTATO = $numero " +
                "WHERE $KEY_ID = $id")

        writableDatabase.close()
    }

    fun addImage(imageArray: ByteArray, id: Int){
        //writableDatabase.execSQL("UPDATE $TABLE_CONTATO  SET $KEY_IMAGEM_CONTATO = $imageArray WHERE $KEY_ID = $id")

        val cv = ContentValues()
        cv.put("$KEY_IMAGEM_CONTATO", imageArray)
        writableDatabase.update("$TABLE_CONTATO", cv, "$KEY_ID = $id", null)
        writableDatabase.close()
    }
}
