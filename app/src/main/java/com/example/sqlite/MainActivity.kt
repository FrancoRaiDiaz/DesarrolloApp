package com.example.sqlite


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import com.example.sqlite.data.local.base_datos.BaseDatosApp
import com.example.sqlite.ui.screen.PantallaFinalizarTraslado
import com.example.sqlite.ui.screen.PantallaInicioTraslado
import com.example.sqlite.ui.screen.PantallaPrincipal
import com.example.sqlite.ui.screen.PantallaTrasladoEnCurso
import com.example.sqlite.ui.viewmodel.TrasladoViewModel
import com.example.sqlite.ui.viewmodel.TrasladoViewModelFactory
import com.teqmed.teqruta.datos.local.entidad.TrasladoEntity
import com.teqmed.teqruta.datos.repositorio.RepositorioTrasladoImpl


class MainActivity : ComponentActivity() {

    private lateinit var trasladoViewModel: TrasladoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = BaseDatosApp.obtenerInstancia(applicationContext)
        val repositorio = RepositorioTrasladoImpl(db.trasladoDao(), db.peajeDaoo())
        val factory = TrasladoViewModelFactory(repositorio)
        trasladoViewModel = ViewModelProvider(this, factory)[TrasladoViewModel::class.java]

        setContent {
            trasladoViewModel.cargarTraslados()

            var pantallaActual by remember { mutableStateOf("principal") }
            var trasladoSeleccionado by remember { mutableStateOf<TrasladoEntity?>(null) }

            when (pantallaActual) {
                "principal" -> {
                    PantallaPrincipal(
                        viewModel = trasladoViewModel,
                        onNuevoTraslado = { pantallaActual = "iniciar" },
                        onVerEnCurso = { traslado ->
                            trasladoSeleccionado = traslado
                            pantallaActual = "en_curso"
                        },
                        onFinalizarTraslado = { traslado ->
                            trasladoSeleccionado = traslado
                            pantallaActual = "finalizar"
                        }
                    )
                }
                "iniciar" -> {
                    PantallaInicioTraslado(
                        viewModel = trasladoViewModel,
                        onVolver = { pantallaActual = "principal" }
                    )
                }
                "en_curso" -> {
                    trasladoSeleccionado?.let { traslado ->
                        PantallaTrasladoEnCurso(
                            traslado = traslado,
                            onIrAFinalizar = { pantallaActual = "finalizar" },
                            onVolver = { pantallaActual = "principal" }
                        )
                    }
                }
                "finalizar" -> {
                    trasladoSeleccionado?.let { traslado ->
                        PantallaFinalizarTraslado(
                            viewModel = trasladoViewModel,
                            traslado = traslado,
                            onVolver = { pantallaActual = "principal" }
                        )
                    }
                }
            }
        }
    }
}