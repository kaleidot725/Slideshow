package jp.kaleidot725.slideshow

import android.graphics.drawable.Drawable
import android.net.Uri

object SlideFactory {
    fun create(drawable: Drawable) : Slide {
        return SlideFromDrawable(drawable)
    }

    fun create(resource: Int) : Slide {
        return SlideFromResource(resource)
    }

    fun create(uri: Uri): Slide {
        return SlideFromUri(uri)
    }
}
