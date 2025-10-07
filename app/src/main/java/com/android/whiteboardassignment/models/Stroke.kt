package com.android.whiteboardassignment.models

import android.graphics.Color

data class Stroke(
    val points: MutableList<Pair<Float, Float>> = mutableListOf(),
    var color: Int = Color.BLACK,
    var width: Float = 5f
)