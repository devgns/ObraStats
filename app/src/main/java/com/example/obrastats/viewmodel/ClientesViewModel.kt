package com.example.obrastats.viewmodel

import com.example.obrastats.classes.Cliente

class ClientesViewModel {
    private var currentIndex: Int? = null

    private val clientes: MutableList<Cliente> = mutableListOf(
        Cliente(
            id = 1,
            nome = "Cliente 1",
            sexo = "Feminino",
            celular = "34999999999",
            email = "cliente1@example.com",
            cidade = "Uberaba",
            endereco = "Rua A"
        ),
        Cliente(
            id = 2,
            nome = "Cliente 2",
            sexo = "Masculino",
            celular = "34888888888",
            email = "cliente2@example.com",
            cidade = "Uberlândia",
            endereco = "Rua B"
        ),
        Cliente(
            id = 3,
            nome = "Cliente 3",
            sexo = "Feminino",
            celular = "34777777777",
            email = "cliente3@example.com",
            cidade = "Uberlândia",
            endereco = "Rua C"
        )
    )

    fun getClientesList(): List<Cliente> {
        return clientes
    }

    fun addCliente(cliente: Cliente) {
        clientes.add(cliente)
    }

    fun getCurrentIndex(): Int? {
        return currentIndex
    }

    fun changeIndex(newIndex: Int?) {
        if(newIndex == null){
            currentIndex = newIndex;
        }else if (newIndex >= 0 && newIndex < clientes.size) {
            currentIndex = newIndex
        }
    }

    fun updateClienteAtIndex(index: Int, novoCliente: Cliente) {
        if (index >= 0 && index < clientes.size) {
            clientes[index] = novoCliente
        }
    }
}
