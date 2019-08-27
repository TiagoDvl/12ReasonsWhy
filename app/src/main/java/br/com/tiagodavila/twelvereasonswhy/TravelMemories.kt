package br.com.tiagodavila.twelvereasonswhy

import android.graphics.drawable.Drawable

data class TravelMemories(
    var title: String,
    var image: Drawable,
    var latitude: Double,
    var longitude: Double
)