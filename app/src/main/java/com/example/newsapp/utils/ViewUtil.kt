package com.example.newsapp.utils

import android.os.Build
import android.text.Html
import androidx.lifecycle.MutableLiveData
import com.airbnb.lottie.LottieAnimationView

fun BookMarkLottieview.setLotteState(lotteView: LottieAnimationView, state: Boolean) {
    if(state){
        lotteView.setMinAndMaxFrame(0,62)
        lotteView.frame = 62
    }else{
        lotteView.setMinAndMaxFrame(70,126)
        lotteView.frame = 126
    }
}

fun String.parseHtmlString() : String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        Html.fromHtml(this).toString()
    }
}

fun <T : Any?> MutableLiveData<T>.default(initialValue:T) = apply {
    try {
        setValue(initialValue)
    }catch (e:java.lang.Exception){

    }
}