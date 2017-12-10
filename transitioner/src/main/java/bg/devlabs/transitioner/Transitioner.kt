package bg.devlabs.transitioner

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator


/**
 * Created by Radoslav Yankov on 07.12.2017
 * Dev Labs
 * radoslavyankov@gmail.com
 * Main and only library class
 */
class Transitioner(startingView: View, endingView: View) {

    /**
     * The interpolator of the animation
     */
    var interpolator: TimeInterpolator = AccelerateDecelerateInterpolator()

    /**
     * The duration of the animation
     */
    var duration = 400
    set(value) {
        if (value >= 0) field = value
    }

    /**
     * The current progress of the animation
     */
    var currentProgress = 0f
        private set

    /**
     * Manually set animation progress based on a float number between 0 and 1
     * @param progress The float to which the animation progress should be set
     */
    fun setProgress(progress: Float) {
        currentProgress = progress
        onPercentChanged(progress)
        mappedViews.forEach {
            it.apply{
            startV.x = origDimens.x + (endV.x - origDimens.x) * progress
            startV.y = origDimens.y + (endV.y - origDimens.y) * progress
            startV.layoutParams.width = origDimens.width + ((endV.width - origDimens.width) * progress).toInt()
            startV.layoutParams.height = origDimens.height + ((endV.height - origDimens.height) * progress).toInt()
            startV.requestLayout()
            }
        }
    }

    /**
     * Animate to a given percent. Optional interpolator
     * @param percent The float to which the animation progress should be set
     * @param interpolator The interpolator for the animation. Optional
     * @param duration The duration of the animation. Optional
     */
    fun animateTo(percent: Float, duration: Long? = null, interpolator: TimeInterpolator? = null) {
        if (currentProgress == percent || percent < 0f || percent > 1f) return
        ValueAnimator.ofFloat(currentProgress, percent).apply{
            this.duration = duration ?: this@Transitioner.duration.toLong()
            this.interpolator = interpolator ?: this@Transitioner.interpolator
            addUpdateListener { animation ->
                setProgress(animation.animatedValue as Float)
            }
            start()
        }
    }

    /**
     * A function, invoked on every progress change
     * @param func Function to be invoked on every progress change
     */
    fun onProgressChanged(func: (percent: Float) -> Unit) {
        this.onPercentChanged = func
    }

    /**
     * Manually set animation progress based on a integer number between 0 and 100
     * @param percent The integer to which the animation progress should be set
     */
    fun setProgress(percent: Int) = setProgress(percent.toFloat() / 100)

    private var mappedViews: ArrayList<StateOfViews> = arrayListOf()
    private var startingChildViews = getAllChildren(startingView)
    private var endingChildViews = getAllChildren(endingView)
    private var onPercentChanged: (percent: Float) -> Unit = {}

    init {
        startingChildViews.forEach { old ->
            endingChildViews
                    .filter { old.tag == it.tag }
                    .forEach { mappedViews.add(StateOfViews(old, it, Dimensions(old.x.toInt(), old.y.toInt(), old.width, old.height)))
                    }
        }
    }

    private fun getAllChildren(v: View): ArrayList<View> {
        val visited = ArrayList<View>()
        val unvisited = ArrayList<View>()
        unvisited.add(v)
        while (!unvisited.isEmpty()) {
            val child = unvisited.removeAt(0)
            visited.add(child)
            if (child !is ViewGroup) continue
            (0 until child.childCount).mapTo(unvisited) { child.getChildAt(it) }
        }
        return visited
    }

    private data class StateOfViews(var startV: View, var endV: View, var origDimens: Dimensions)
    private data class Dimensions(var x: Int, var y: Int, var width: Int, var height: Int)
}

