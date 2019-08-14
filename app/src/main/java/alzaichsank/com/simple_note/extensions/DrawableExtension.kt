package alzaichsank.com.simple_note.extensions

import alzaichsank.com.simple_note.R
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

fun Drawable.filter(context: Context) {
    val color = ContextCompat.getColor(context, R.color.colorPrimary)
    this.setColorFilter(color, PorterDuff.Mode.SRC_IN)
}