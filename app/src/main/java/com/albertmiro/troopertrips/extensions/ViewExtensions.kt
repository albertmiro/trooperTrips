package com.albertmiro.troopertrips.extensions

import android.view.View

fun View.isVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}
