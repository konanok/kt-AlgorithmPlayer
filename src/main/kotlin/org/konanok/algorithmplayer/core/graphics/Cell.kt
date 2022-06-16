package org.konanok.algorithmplayer.core.graphics

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.konanok.algorithmplayer.core.unit.Coordinate
import org.konanok.algorithmplayer.core.unit.Size


private const val CellBorderWidth = 1

private const val DefaultCellWidth = 40
private const val DefaultCellHeight = 40
private val DefaultCellSize = Size(DefaultCellWidth, DefaultCellHeight)


/**
 * 文本框
 */
@Composable
fun Cell(
    text: String = "",
    topLeft: Coordinate = Coordinate.Origin,
    size: Size = DefaultCellSize,
    shape: Shape = RectangleShape,
    drawBorder: Boolean = true,
) {
    // 计算坐标和大小
    // 有边框时，长宽各增加 CellBorderWidth，向左/上平移 CellBorderWidth / 2，使得边框可以覆盖网格线；
    // 无边框时，长宽各减小 CellBorderWidth，向右/下平移 CellBorderWidth / 2，避免网格线被覆盖。
    val (actualOffset, actualSize) = if (drawBorder) {
        Pair(
            DpOffset(x = (topLeft.x - CellBorderWidth / 2f).dp, y = (topLeft.y - CellBorderWidth / 2f).dp),
            DpSize(width = (size.width + CellBorderWidth).dp, height = (size.height + CellBorderWidth).dp)
        )
    } else {
        Pair(
            DpOffset(x = (topLeft.x + CellBorderWidth / 2f).dp, y = (topLeft.y + CellBorderWidth / 2f).dp),
            DpSize(width = (size.width - CellBorderWidth).dp, height = (size.height - CellBorderWidth).dp)
        )
    }
    // 计算背景图形
    // 有边框时，仅支持 CircleShape / RectangleShape；
    // 无边框时，强制设置为 RectangleShape。
    val actualShape = if (drawBorder) {
        if (shape == CircleShape) CircleShape else RectangleShape
    } else {
        RectangleShape
    }

    Box(
        modifier = Modifier
            .requiredSize(actualSize)
            .offset(x = actualOffset.x, y = actualOffset.y)
            .border(width = CellBorderWidth.dp, color = Color.Black, shape = actualShape, drawBorder = drawBorder)
            .background(Color.White, shape = actualShape),
        contentAlignment = Alignment.Center
    ) {
        if (text.isNotBlank()) {
            Text(text)
        }
    }
}

// 绘制边框
private fun Modifier.border(width: Dp, color: Color, shape: Shape, drawBorder: Boolean): Modifier {
    if (drawBorder) {
        return this.border(width = width, color = color, shape = shape)
    }
    return this
}
