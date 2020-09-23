package jp.kaleidot725.slideshow

import android.net.Uri
import android.widget.ImageSwitcher

internal class SlideFromUri(private val uri: Uri): Slide {
    override fun load(imageSwitcher: ImageSwitcher) {
        imageSwitcher.setImageURI(uri)
    }
}
