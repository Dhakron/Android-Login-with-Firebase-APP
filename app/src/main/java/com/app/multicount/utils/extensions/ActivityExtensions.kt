package com.app.multicount.utils.extensions

import androidx.fragment.app.FragmentActivity
import com.app.multicount.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

val FragmentActivity.mainViewModel: MainViewModel?
    get() = (getViewModel() as? MainViewModel)