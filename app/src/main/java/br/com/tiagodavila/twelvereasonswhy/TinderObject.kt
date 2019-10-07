package br.com.tiagodavila.twelvereasonswhy

import android.graphics.drawable.Drawable

data class TinderObject(
    var name: String,
    var image: Drawable,
    var like: Boolean = false
)