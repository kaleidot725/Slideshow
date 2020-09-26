package jp.kaleidot725.sample

import android.os.Bundle
import android.view.animation.AlphaAnimation
import androidx.appcompat.app.AppCompatActivity
import jp.kaleidot725.slideshow.SlideFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        slide_show_view.apply {
            setSlides(
                arrayListOf(
                    SlideFactory.create(R.drawable.ic_launcher_background),
                    SlideFactory.create(R.drawable.ic_launcher_foreground)
                )
            )
            inAnimation = AlphaAnimation(0.0f, 1.0f).apply { duration = 1000 }
            outAnimation = AlphaAnimation(1.0f, 0.0f).apply { duration = 1000 }
            setInterval(1000)
            start()
        }
    }
}
