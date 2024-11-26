package com.example.mywhatsapp

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun Estrella() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val image = AnimatedImageVector.animatedVectorResource(R.drawable.avd_estrella)
        var atEnd by rememberSaveable() { mutableStateOf(false) }

        Image(
            painter = rememberAnimatedVectorPainter(image, atEnd),
            contentDescription = "VectorDrawable",
            modifier = Modifier.clickable {
                atEnd = !atEnd
            }
        )
    }
}