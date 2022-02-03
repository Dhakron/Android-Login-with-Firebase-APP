package com.app.multicount

import android.app.Application
import android.content.res.Resources
import com.app.data.repositories.AuthenticationRepository
import com.app.data.source.authentication.AuthenticationDataSource
import com.app.multicount.data.datasource.AuthenticationDataSourceImpl
import com.app.multicount.ui.main.MainViewModel
import com.app.data.usecases.authentication.*
import com.app.data.utils.DataValidator
import com.app.multicount.ui.home.HomeViewModel
import com.app.multicount.ui.register.RegisterViewModel
import com.app.multicount.utils.DataValidatorImpl
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun Application.initDI(){
    startKoin {
        androidContext(this@initDI)
        modules(listOf(
            androidModule,
            firebaseModule,
            utilsModule,
            dataSourceModule,
            repositoriesModule,
            usesCasesModule,
            viewModelsModule,
        ))
    }
}

private val androidModule = module {
    single<Resources> { androidContext().resources }

}

private val firebaseModule = module {
    single{ FirebaseAuth.getInstance().apply {
        useAppLanguage()
    } }
}

private val utilsModule = module {

    single<DataValidator> { DataValidatorImpl(get()) }

}

private val dataSourceModule = module {

    factory<AuthenticationDataSource>{ AuthenticationDataSourceImpl(get()) }

}

private val repositoriesModule = module {

    single { AuthenticationRepository(get()) }

}

private val usesCasesModule = module {

    //region Authentication
    factory { AuthenticationLogOut(get()) }
    factory { ChangeLoggedUserPassword(get()) }
    factory { GetLoggedUserUID(get()) }
    factory { IsUserAuthenticated(get()) }
    factory { RecoverUserAccountPassword(get()) }
    factory { AccountAuthentication(get()) }
    //endregion Authentication

}

private val viewModelsModule = module {
    viewModel {
        MainViewModel(get())
    }

    viewModel {
        RegisterViewModel(get(),get())
    }

    viewModel {
        HomeViewModel(get())
    }
}