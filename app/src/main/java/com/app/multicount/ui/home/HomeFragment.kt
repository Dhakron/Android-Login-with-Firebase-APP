package com.app.multicount.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.multicount.databinding.HomeFragmentBinding
import com.app.multicount.ui.BaseFragment
import com.app.multicount.utils.extensions.mainViewModel
import com.app.multicount.utils.extensions.observeEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private lateinit var binding :HomeFragmentBinding

    override val viewModel : HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= HomeFragmentBinding.inflate(inflater,container,false).apply {
            lifecycleOwner = viewLifecycleOwner
            this.viewModel = this@HomeFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    private fun setObservers(){
        viewModel.apply {
            observeEvent(homeEvent) {
                when(it){
                    HomeViewModel.HomeEvent.LogOut -> {
                        activity?.mainViewModel?.onUserLogOut()
                    }
                }
            }
        }
    }

}