package com.example.mywhatsapp

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Chats() {
    val contactosAgrupados = getContactos().groupBy { it.grupo }
    LazyColumn (

    ){
        contactosAgrupados.forEach { (grupo, contactosPorGrupo) ->
            stickyHeader {
                Text(
                    text = grupo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(8.dp)
                )
            }

            items(contactosPorGrupo) { contacto ->
                ItemContacto(contacto)
            }
        }
    }
}

@Composable
fun ItemContacto(contacto: Contacto) {
    Row (
        Modifier
            .clickable {  }
            .fillMaxWidth()
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .size(90.dp)
        ) {
            Image(
                painter = painterResource(contacto.foto),
                contentDescription = contacto.nombre,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .fillMaxSize()
            )
        }
        Text(
            text = contacto.nombre,
            fontSize = 20.sp
        )
    }
}

fun getContactos(): List<Contacto> {
    return listOf(
        Contacto(
            "María Mata",
            "Departamento de informática",
            R.drawable.pj_image1
        ),
        Contacto(
            "Antonio Sanz",
            "Departamento de informática",
            R.drawable.pj_image2
        ),
        Contacto(
            "Carlos Pérez",
            "Comunidad de propietarios",
            R.drawable.pj_image3
        ),
        Contacto(
            "Beatriz Martos",
            "Comunidad de propietarios",
            R.drawable.pj_image4
        ),
        Contacto(
            "Sandra Alegre",
            "Gimnasio",
            R.drawable.pj_image5
        ),
        Contacto(
            "Alex Serrat",
            "Gimnasio",
            R.drawable.pj_image6
        ),
        Contacto(
            "Ana Peris",
            "Departamento de informática",
            R.drawable.pj_image7
        ),
        Contacto(
            "Rodrigo Rodríguez",
            "Gimnasio",
            R.drawable.pj_image8
        )
    )
}

data class Contacto(
    var nombre: String,
    var grupo: String,
    @DrawableRes var foto: Int,
)