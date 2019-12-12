package com.example.listacontato.model

import java.io.Serializable

class Contato : Serializable{

    var id: Int? = null
    var nome: String
    var numero: Long
    var imageArrayByte: ByteArray? = null

    constructor(nome: String, numero: Long) {
        this.nome = nome
        this.numero = numero
    }
    constructor(id: Int, nome: String, numero: Long) : this(nome, numero){
        this.id = id
    }

    constructor(id: Int, nome: String, numero: Long, imageArrayByte: ByteArray) : this(id, nome, numero){
        this.imageArrayByte = imageArrayByte
    }

}