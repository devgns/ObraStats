package com.example.obrastats.classes
data class  Cliente(
    var id: String?,
    val nome: String,
    val sexo: String,
    val celular: String,
    val email: String,
    val cidade: String,
    val endereco: String,
    val cpfCnpj: String?
) {
}

//val clienteComId = Cliente(1, 'M', "123456789", "cliente@example.com", "São Paulo", "Rua A")
//val clienteSemId = Cliente(null, 'F', "987654321", "cliente2@example.com", "Rio de Janeiro", "Rua B")
