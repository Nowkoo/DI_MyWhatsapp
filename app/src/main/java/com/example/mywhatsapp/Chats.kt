package com.example.mywhatsapp

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .size(70.dp)
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
        Column {
            Row {
                Text(
                    text = contacto.nombre,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = contacto.fechaUltimoMensaje,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }

            Text(
                text = "${contacto.nombre}: ${contacto.ultimoMensaje}",
                fontSize = 15.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 10.dp),
                color = Color.Gray
            )
        }
    }
}

fun getContactos(): List<Contacto> {
    return listOf(
        Contacto(
            "María Mata",
            "Departamento de informática",
            R.drawable.pj_image1,
            "El viernes que viene hay mantenimiento",
            "11:26 AM"
        ),
        Contacto(
            "Antonio Sanz",
            "Departamento de informática",
            R.drawable.pj_image2,
            "Sí",
            "Ayer"
        ),
        Contacto(
            "Carlos Pérez",
            "Comunidad de propietarios",
            R.drawable.pj_image3,
            "Estimados vecinos, les recordamos que la próxima reunión se llevará a a cabo el martes que viene a las 18:00",
            "8:15 AM"
        ),
        Contacto(
            "Beatriz Martos",
            "Comunidad de propietarios",
            R.drawable.pj_image4,
            "Lo siento, acabo de ver tu mensaje.",
            "10:20 AM"
        ),
        Contacto(
            "Sandra Alegre",
            "Gimnasio",
            R.drawable.pj_image5,
            "¿Mañana vas a spinning?",
            "11:50 AM"
        ),
        Contacto(
            "Alex Serrat",
            "Gimnasio",
            R.drawable.pj_image6,
            "Es festivo, no sé si abrirán",
            "Ayer"
        ),
        Contacto(
            "Ana Peris",
            "Departamento de informática",
            R.drawable.pj_image7,
            "Nos vemos en la reunión",
            "Ayer"
        ),
        Contacto(
            "Rodrigo Rodríguez",
            "Gimnasio",
            R.drawable.pj_image8,
            "Estoy agotado",
            "Ayer"
        )
    )
}

data class Contacto(
    var nombre: String,
    var grupo: String,
    @DrawableRes var foto: Int,
    var ultimoMensaje: String,
    var fechaUltimoMensaje: String
)