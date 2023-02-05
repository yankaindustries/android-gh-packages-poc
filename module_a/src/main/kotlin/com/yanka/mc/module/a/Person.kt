package com.yanka.mc.module.a

data class Person(
    val firstName: String,
    val lastName: String,
) {

    fun getFullName() = "$firstName $lastName"
}
