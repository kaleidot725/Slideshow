package jp.kaleidot725.slideshow

import android.content.Context
import android.util.AttributeSet
import android.view.animation.Animation
import android.widget.ImageSwitcher
import android.widget.ImageView
import java.util.*

class SlideshowView : ImageSwitcher {
    private var timer: Timer? = null
    private var slides: CycleIterator<Slide>? = null
    private var interval : Long= DEFAULT_INTERVAL

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    fun start() {
        this.timer?.cancel()
        this.timer = Timer().apply { schedule(createTimerTask(), interval, interval) }
    }

    fun stop() {
        this.timer?.cancel()
        this.timer = null
    }

    fun setInterval(interval: Long) {
        this.interval = interval
    }

    fun setSlides(slides: List<Slide>) {
        this.slides = CycleIterator(slides)
    }

    fun setSlideInAnimation(animation: Animation) {
        this.inAnimation = animation
    }

    fun setSlideOutAnimation(animation: Animation) {
        this.outAnimation = animation
    }

    private fun init(context: Context?, attrs: AttributeSet?) {
        this.setFactory {
            ImageView(context).also {imageView ->
                // FIXME get scale type from xml attributes
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                imageView.layoutParams = this.layoutParams
            }
        }
    }

    private fun createTimerTask(): TimerTask {
        return object : TimerTask() {
            override fun run() {
                this@SlideshowView.post {
                    slides?.next()?.load(this@SlideshowView)
                }
            }
        }
    }

    private class CycleIterator<T>(private val list: List<T>) {
        private var iterator: Iterator<T> = list.iterator()
        fun next(): T {
            if (!iterator.hasNext()) {
                iterator = list.iterator()
            }

            return iterator.next()
        }
    }

    companion object {
        private const val DEFAULT_INTERVAL: Long = 1000
    }
}
