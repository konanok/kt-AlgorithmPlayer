package org.konanok.aplayer.ui.player

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


private const val GridLineWidth = 1
private const val GridLightLineGap = 10
private const val GridSolidLineGap = GridLightLineGap * 4

// 在使用 drawLine 绘制线时，定义线的 Offset 是线宽的中点的坐标。
// 为了使 PlayerView 的 border 能够完全落在 PlayerView 内，同时保证 PlayView 的有效区域大小不变，这里给整体额外增加 border 的宽度。
private const val PlayerViewWidth = 1080 + GridLineWidth
private const val PlayerViewHeight = 720 + GridLineWidth

private val GridLightLineColor = Color(242, 242, 242)
private val GridSolidLineColor = Color(221, 221, 221)
private val BackgroundColor = Color.White


/**
 * 算法动画播放器
 */
@Composable
fun PlayerView(
    showGrid: Boolean = true
) {
    Box(
        modifier = Modifier
            .requiredSize(width = PlayerViewWidth.dp, height = PlayerViewHeight.dp)
            .background(BackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        if (showGrid) {
            GridLines()
        }
    }
}

/**
 * 背景网格线
 *
 */
@Composable
private fun GridLines() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val lightLines = mutableListOf<Pair<Offset, Offset>>()
        val solidLines = mutableListOf<Pair<Offset, Offset>>()

        val offset = GridLineWidth / 2f // 向内移动 border 宽度的一半，使 border 的上、左两侧也位于 PlayerView 中。

        // 垂直线
        for (x in 0..PlayerViewWidth step GridLightLineGap) {
            val line = Pair(
                Offset(x = (x + offset) * density, y = 0f),
                Offset(x = (x + offset) * density, y = PlayerViewHeight * density),
            )
            if (x % GridSolidLineGap == 0) {
                solidLines.add(line)
            } else {
                lightLines.add(line)
            }
        }
        // 水平线
        for (y in 0..PlayerViewHeight step GridLightLineGap) {
            val line = Pair(
                Offset(y = (y + offset) * density, x = 0f),
                Offset(y = (y + offset) * density, x = PlayerViewWidth * density),
            )
            if (y % GridSolidLineGap == 0) {
                solidLines.add(line)
            } else {
                lightLines.add(line)
            }
        }
        // 先绘制浅色的线，再绘制深色的线，防止出现斑马线。
        lightLines.forEach {
            drawLine(
                color = GridLightLineColor,
                strokeWidth = GridLineWidth * density,
                start = it.first,
                end = it.second
            )
        }
        solidLines.forEach {
            drawLine(
                color = GridSolidLineColor,
                strokeWidth = GridLineWidth * density,
                start = it.first,
                end = it.second
            )
        }
    }
}
