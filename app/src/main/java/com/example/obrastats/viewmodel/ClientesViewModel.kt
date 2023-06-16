package com.example.obrastats.viewmodel

import com.example.obrastats.classes.Cliente

class ClientesViewModel {

    fun getClientes(): List<Cliente> {
        return listOf(
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
                id = 1,
                nome = "Cliente 1",
                sexo = "Feminino",
                celular = "34999999999",
                email = "cliente1@example.com",
                cidade = "Uberaba",
                endereco = "Rua A"
            )
        )
    }
}