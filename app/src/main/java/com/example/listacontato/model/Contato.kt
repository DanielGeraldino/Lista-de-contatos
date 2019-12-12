package com.example.listacontato.model

import java.io.Serializable

class Contato : Serializable{

    var id: Int? = null
    var nome: String
    var numero: Int
    var imageArrayByte: ByteArray? = null

    constructor(nome: String, numero: Int) {
        this.nome = nome
        this.numero = numero
    }
    constructor(id: Int, nome: String, numero: Int) : this(nome, numero){
        this.id = id
    }

    constructor(id: Int, nome: String, numero: Int, imageArrayByte: ByteArray) : this(id, nome, numero){
        this.imageArrayByte = imageArrayByte
    }

}