package com.josuearevalodev.base_android.extensions

import android.view.Menu
import androidx.annotation.ColorInt
import androidx.core.graphics.drawable.DrawableCompat

fun Menu.tintMenuItem(idItem: Int, @ColorInt color: Int) {
    val menuItem = findItem(idItem)
    val drawable = DrawableCompat.wrap(menuItem.icon)
    DrawableCompat.setTint(drawable, color)
    findItem(idItem).icon = drawable
}
