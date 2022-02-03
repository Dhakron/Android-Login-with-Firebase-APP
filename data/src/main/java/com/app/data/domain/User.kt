package com.app.data.domain


data class User(
    val id:Int,
    val name:String,
    val userName:String?=null,
    val email:String,
)