package com.example.sqlite.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teqmed.teqruta.datos.repositorio.RepositorioTrasladoImpl

class TrasladoViewModelFactory(
    private val repositorio: RepositorioTrasladoImpl
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrasladoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TrasladoViewModel(repositorio) as T
        }
        throw IllegalArgumentException("Clase ViewModel no encontrada")
    }
}
