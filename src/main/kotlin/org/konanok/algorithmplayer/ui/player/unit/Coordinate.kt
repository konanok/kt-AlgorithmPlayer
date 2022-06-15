package org.konanok.algorithmplayer.ui.player.unit

data class Coordinate(
    val x: Int,
    val y: Int
) {

    companion object {
        val Origin = Coordinate(x = 0, y = 0)
    }
}

data class Size(
    val width: Int,
    val height: Int
)
