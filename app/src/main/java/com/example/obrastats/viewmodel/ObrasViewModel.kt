package com.example.obrastats.viewmodel

import com.example.obrastats.classes.Cliente
import com.example.obrastats.classes.Obra

class ObrasViewModel {
    private var currentIndex: Int? = null

    private val obras: MutableList<Obra> = mutableListOf(
        Obra(
            id = 1,
            nome = "Obra 1",
            cliente = Cliente(
                id = 1,
                nome = "Cliente 1",
                sexo = "Feminino",
                celular = "34999999999",
                email = "cliente1@example.com",
                cidade = "Uberaba",
                endereco = "Rua A"
            ),
            cidade = "Uberaba",
            endereco = "Endereço 1"
        ),
        Obra(
            id = 2,
            nome = "Obra 2",
            cliente = Cliente(
                id = 2,
                nome = "Cliente 2",
                sexo = "Masculino",
                celular = "34888888888",
                email = "cliente2@example.com",
                cidade = "Uberlândia",
                endereco = "Rua B"
            ),
            cidade = "Uberlândia",
            endereco = "Endereço 2"
        ),
        Obra(
            id = 3,
            nome = "Obra 3",
            cliente = Cliente(
                id = 3,
                nome = "Cliente 3",
                sexo = "Feminino",
                celular = "34777777777",
                email = "cliente3@example.com",
                cidade = "Uberlândia",
                endereco = "Rua C"
            ),
            cidade = "Uberlândia",
            endereco = "Endereço 3"
        )
    )

    fun getObrasList(): List<Obra> {
        return obras
    }

    fun addObra(obra: Obra) {
        obras.add(obra)
    }

    fun getCurrentIndex(): Int? {
        return currentIndex
    }

    fun changeIndex(newIndex: Int?) {
        if (newIndex == null) {
            currentIndex = newIndex
        } else if (newIndex >= 0 && newIndex < obras.size) {
            currentIndex = newIndex
        }
    }

    fun updateObraAtIndex(index: Int, novaObra: Obra) {
        if (index >= 0 && index < obras.size) {
            obras[index] = novaObra
        }
    }
}
