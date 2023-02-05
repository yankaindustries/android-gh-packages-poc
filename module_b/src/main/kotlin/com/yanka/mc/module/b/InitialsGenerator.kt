package com.yanka.mc.module.b

import com.yanka.mc.module.a.Person

class InitialsGenerator {

    fun getInitials(person: Person) =
        (person.firstName.takeIf { it.isNotBlank() }?.take(1) ?: "") +
                (person.lastName.takeIf { it.isNotBlank() }?.take(1) ?: "")
}