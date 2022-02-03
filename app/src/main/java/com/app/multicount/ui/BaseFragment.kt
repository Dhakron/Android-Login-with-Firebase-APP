package com.app.multicount.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.app.multicount.utils.extensions.navigate
import com.app.multicount.utils.extensions.navigateUp
import com.app.multicount.utils.extensions.observeEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

open class BaseFragment:Fragment() {
    open val viewModel:BaseViewModel = BaseViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvent(viewModel.event){
            when(it){
                is BaseViewModel.Event.Navigate -> {
                    navigate(it.direction)
                }
                BaseViewModel.Event.NavigateUp -> {
                    navigateUp()
                }
                is BaseViewModel.Event.ShowSnackBar -> {
                }
                else -> {}
            }
        }
    }
}