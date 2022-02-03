package com.app.multicount.utils.binding_adapters

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visible")
fun View.visible(isVisible:Boolean?){
    visibility=if(isVisible==true)View.VISIBLE else View.GONE
}

@BindingAdapter("enabled")
fun View.enabled(isEnabled:Boolean?){
    setEnabled(isEnabled==true)
}