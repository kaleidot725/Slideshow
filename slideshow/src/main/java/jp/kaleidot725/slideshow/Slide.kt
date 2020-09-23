package jp.kaleidot725.slideshow

import android.widget.ImageSwitcher
import android.widget.ImageView

interface Slide {
    fun load(imageSwitcher: ImageSwitcher)
}
