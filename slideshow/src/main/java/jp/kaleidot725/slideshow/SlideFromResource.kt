package jp.kaleidot725.slideshow

import android.widget.ImageSwitcher
import android.widget.ImageView

internal class SlideFromResource(private val resource: Int) : Slide {
    override fun load(imageSwitcher: ImageSwitcher) {
        imageSwitcher.setImageResource(resource)
    }
}
