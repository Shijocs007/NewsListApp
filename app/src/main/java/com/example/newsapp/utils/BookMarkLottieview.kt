package com.example.newsapp.utils

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import androidx.annotation.IntDef
import com.airbnb.lottie.LottieAnimationView

class BookMarkLottieview : LottieAnimationView {

    companion object{
        const val ITEM_NORMAL = 0
        const val ITEM_BOOKMARKED = 1
        const val ITEM_UNBOOKMARKED = 2
    }

    @IntDef(ITEM_NORMAL,ITEM_BOOKMARKED, ITEM_UNBOOKMARKED)
    @Retention(AnnotationRetention.SOURCE)
    annotation class BookmarkState{}

    interface BookMarkLottieAnimationListener {
        fun onAnimationStarted()
        fun onAnimationEnd()
    }

    @BookmarkState var sBookMarkButtonState = ITEM_NORMAL


    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    fun setAnimationListener(animatorListener : BookMarkLottieAnimationListener) {
        addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {
                animatorListener?.let {
                    it.onAnimationStarted()
                }
            }

            override fun onAnimationEnd(animation: Animator?) {
                animatorListener?.let {
                    it.onAnimationEnd()
                }
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })
    }

    fun setBookMarkState(@BookmarkState bookmarkState: Int){
        sBookMarkButtonState = bookmarkState
        when(bookmarkState){
            ITEM_BOOKMARKED->{
                setMinAndMaxFrame(1,62)
            }
            ITEM_UNBOOKMARKED ->{
                setMinAndMaxFrame(70,126)
            }
        }
    }


}