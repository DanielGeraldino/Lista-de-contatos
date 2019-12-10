package com.example.listacontato.model

import java.io.Serializable

class Contato(var nome: String, var numero: Int) : Serializable{

    var id: Int? = null

    constructor(id: Int, nome: String, numero: Int) : this(nome, numero){
        this.id = id
    }

}