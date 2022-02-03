package com.app.multicount.data.datasource

import com.app.data.out_data.LoginResult
import com.app.data.out_data.RegisterUserResult
import com.app.data.source.authentication.AuthenticationDataSource
import com.app.data.utils.RemoteTask
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthenticationDataSourceImpl(
    private val firebaseAuth: FirebaseAuth,
): AuthenticationDataSource {

    override fun isUserLogged() = firebaseAuth.currentUser!=null

    override fun getLoggedUserUID() = firebaseAuth.currentUser?.uid

    override fun isGuestUserLogged() = firebaseAuth.currentUser?.isAnonymous?:false

    override fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ) = flow<RemoteTask<RegisterUserResult>>{
        try {
            emit(RemoteTask.Started())
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(RemoteTask.Completed(RegisterUserResult.Success))
        }catch(e:Exception){
            emit(RemoteTask.Error(e))
        }
    }

    override fun createUserWithGoogleAccount(): Flow<RemoteTask<RegisterUserResult>> {
        TODO("Not yet implemented")
    }

    override fun createUserWithFacebookAccount(): Flow<RemoteTask<RegisterUserResult>> {
        TODO("Not yet implemented")
    }

    override fun createGuestUser() = flow<RemoteTask<RegisterUserResult>>{
        try {
            emit(RemoteTask.Started())
            firebaseAuth.signInAnonymously().await()
            emit(RemoteTask.Completed(RegisterUserResult.Success))
        }catch(e:Exception){
            emit(RemoteTask.Error(e))
        }
    }

    override fun loginWithEmailAndPassword(
        email: String,
        password: String
    ) = flow<RemoteTask<LoginResult>>{
        try {
            emit(RemoteTask.Started())
            firebaseAuth.signInWithEmailAndPassword(email,password).await()
            emit(RemoteTask.Completed(LoginResult.Success))
        }catch(e:Exception){
            emit(RemoteTask.Error(e))
        }
    }

    override fun loginWithGoogleAccount(): Flow<RemoteTask<LoginResult>> {
        TODO("Not yet implemented")
    }

    override fun loginWithFacebookAccount(): Flow<RemoteTask<LoginResult>> {
        TODO("Not yet implemented")
    }

    override fun logOut() = try {
        firebaseAuth.signOut()
        true
    }catch (e:Exception){
        false
    }

    override fun changePassword(newPassword: String) = flow<RemoteTask<Boolean>>{
        try {
            emit(RemoteTask.Started())
            if(firebaseAuth.currentUser?.updatePassword(newPassword)?.await()!=null){
                emit(RemoteTask.Completed(true))
            }else{
                emit(RemoteTask.Completed(false))
            }
        }catch(e:Exception){
            emit(RemoteTask.Error(e))
        }
    }

    override fun recoverPassword(email: String) = flow<RemoteTask<Boolean>>{
        try {
            emit(RemoteTask.Started())
            firebaseAuth.sendPasswordResetEmail(email).await()
            emit(RemoteTask.Completed(true))
        }catch(e:Exception){
            emit(RemoteTask.Error(e))
        }
    }

}