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

    var scaleType: ImageView.ScaleType = ImageView.ScaleType.CENTER_CROP
    var interval: Long = DEFAULT_INTERVAL

    init {
        context.obtainStyledAttributes(attrs, R.styleable.SlideshowView).apply {
            val scaleTypeIndex = this.getInteger(R.styleable.SlideshowView_scaleType, 0)
            this@SlideshowView.scaleType =  ImageView.ScaleType.values()[scaleTypeIndex]
            this@SlideshowView.interval = this.getInteger(R.styleable.SlideshowView_interval, 1000).toLong()
            recycle()
        }
    }

    fun start() {
        this.setFactory {
            ImageView(context).also { imageView ->
                imageView.scaleType = scaleType
                imageView.layoutParams = LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT
                )
            }
        }
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
