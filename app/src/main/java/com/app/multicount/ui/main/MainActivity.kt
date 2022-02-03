package com.app.multicount.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.NavHostFragment
import com.app.multicount.R
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val navController:NavController by lazy{
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    private val viewModel:MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.uiEvent.onEach { event->
            when(event){
                MainViewModel.UIEvent.IncompleteUserAuthenticated -> TODO()
                is MainViewModel.UIEvent.Navigate -> {
                    navController.navigate(event.direction)
                }
                MainViewModel.UIEvent.NavigateUp -> {
                    navController.navigateUp()
                }
                is MainViewModel.UIEvent.ShowSnackBar -> {
                    TODO()
                }
                MainViewModel.UIEvent.NeedUserAuthentication -> {
                    navController.setGraph(R.navigation.login_nav_graph)
                }
                MainViewModel.UIEvent.UserAuthenticated -> {
                    navController.setGraph(R.navigation.main_nav_graph)
                }
                MainViewModel.UIEvent.LogOut -> {
                    navController.setGraph(R.navigation.login_nav_graph)
                }
            }
        }.launchIn(lifecycleScope)
    }
}
