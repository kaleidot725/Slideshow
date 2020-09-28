package jp.kaleidot725.sample

import android.os.Bundle
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import jp.kaleidot725.slideshow.SlideFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        slide_show_view.slides = DEFAULT_SLIDES
        slide_show_view.interval = DEFAULT_INTERVAL
        slide_show_view.inAnimation = DEFAULT_IN_ANIMATION
        slide_show_view.outAnimation = DEFAULT_OUT_ANIMATION
        slide_show_view.start()
    }

    companion object {
        private val DEFAULT_SLIDES = arrayListOf(
            SlideFactory.create(R.drawable.ic_launcher_background),
            SlideFactory.create(R.drawable.ic_launcher_foreground)
        )
        private const val DEFAULT_INTERVAL = 1000L
        private val DEFAULT_IN_ANIMATION = AlphaAnimation(0.0f, 1.0f).apply { duration = 1000 }
        private val DEFAULT_OUT_ANIMATION = AlphaAnimation(1.0f, 0.0f).apply { duration = 1000 }
    }
}
