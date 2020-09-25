package jp.kaleidot725.slideshow

import android.graphics.drawable.Drawable
import android.widget.ImageSwitcher

internal class SlideFromDrawable(private val drawable: Drawable) : Slide {
    override fun load(imageSwitcher: ImageSwitcher) {
        imageSwitcher.setImageDrawable(drawable)
    }
}
