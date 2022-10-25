package com.example.learnwordshelper.presentation.utils.Animation

import android.animation.Animator
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import androidx.core.animation.addListener
import kotlin.math.hypot

class AnimationCircularReveal {

    companion object {
        fun createOpenAnim(
            view: View,
            duration: Long,
            start: AnimPosStart = AnimPosStart.CENTER
        ): Animator {
            var centerX = 0
            var centerY = 0
            when (start) {
                AnimPosStart.CENTER -> {
                    centerX = view.width / 2
                    centerY = view.height / 2
                }
                AnimPosStart.TOP -> {
                    centerX = view.width / 2
                    centerY = view.width / 2
                }
                AnimPosStart.LEFT -> {
                    centerX = 0
                    centerY = 0
                }
            }
            view.visibility = View.VISIBLE
            val finalRadius = hypot(view.width.toDouble(), view.width.toDouble()).toFloat()
            return ViewAnimationUtils.createCircularReveal(
                view, centerX, centerY, 0f, finalRadius
            ).setDuration(duration)
        }

        fun createCloseAnim(
            view: View,
            duration: Long,
            start: AnimPosStart = AnimPosStart.CENTER
        ): Animator {
            val centerX = view.width / 2
            val centerY = view.height / 2
            val finalRadius = hypot(view.width.toDouble(), view.height.toDouble()).toFloat()
            Log.d("ANIMATOR_TAG", "createCloseAnim: ${finalRadius}")
            val anim = ViewAnimationUtils.createCircularReveal(
                view, centerX, centerY, finalRadius / 3, 0f
            )
            anim.duration = duration
            anim.addListener(onEnd = {
                view.visibility = View.INVISIBLE
            })
            return anim
        }
    }

    enum class AnimPosStart {
        CENTER, TOP, LEFT
    }

}