package jp.kaleidot725.slideshow

import android.content.Context
import android.util.AttributeSet
import android.view.animation.Animation
import android.widget.FrameLayout
import android.widget.ImageSwitcher
import android.widget.ImageView
import java.util.*

class SlideshowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageSwitcher(context, attrs) {
    private var timer: Timer? = null
    private var cycleIterator: CycleIterator<Slide>? = null

    var slides: List<Slide> = emptyList()
        set(value) {
            cycleIterator = CycleIterator(value)
            field = value
        }

    var interval: Long = DEFAULT_INTERVAL

    init {
        this.setFactory {
            ImageView(context).also { imageView ->
                // FIXME get scale type from xml attributes
                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                imageView.layoutParams = LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT
                )
            }
        }
    }

    fun start() {
        this.timer?.cancel()
        this.timer = Timer().apply { schedule(createTimerTask(), interval, interval) }
    }

    fun stop() {
        this.timer?.cancel()
        this.timer = null
    }

    private fun createTimerTask(): TimerTask {
        return object : TimerTask() {
            override fun run() {
                this@SlideshowView.post {
                    cycleIterator?.next()?.load(this@SlideshowView)
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
