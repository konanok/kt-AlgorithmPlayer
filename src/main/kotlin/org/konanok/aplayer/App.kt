package org.konanok.aplayer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.konanok.aplayer.ui.player.PlayerView

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "kt-AlgorithmPlayer",
        state = rememberWindowState(width = 1600.dp, height = 900.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PlayerView(true)
        }
    }
}

