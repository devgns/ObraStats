package com.example.obrastats.classes
data class Obra(
    var id: String?,
    val nome: String,
    val cliente: Cliente,
    val cidade: String,
    val endereco: String
) {
}


