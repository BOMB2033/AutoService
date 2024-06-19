package com.example.aitoservice.repository
data class DataClass(
    var user: User,
    var services: List<Service>,
)
data class User(
    var uid: String = "",
    var email:String = "",
    var service:List<String> = emptyList()
)
data class Service(
    var uid:String = "",
    var name:String = "",
    var cast:String = "",
)