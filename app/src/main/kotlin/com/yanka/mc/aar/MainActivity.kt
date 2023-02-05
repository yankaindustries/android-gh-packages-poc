package com.yanka.mc.aar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.yanka.mc.module.a.Person
import com.yanka.mc.module.b.InitialsGenerator

class MainActivity : AppCompatActivity() {

    companion object {
        val people = listOf(
            Person("Carlos", "Hwa"),
            Person("Elam", "Parithi"),
            Person("Freddy", "Montano"),
            Person("Krishna", "Achanta"),
            Person("Nimitha", "Ramesh"),
            Person("Omair", "Baskanderi"),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showPeople()
    }

    private fun showPeople() {
        val initialsGenerator = InitialsGenerator()
        findViewById<TextView>(R.id.peopleTv).text =
            people.joinToString(separator = "\n") {
                initialsGenerator.getInitials(it)
                    .plus(" - ")
                    .plus(it.firstName)
                    .plus(" ")
                    .plus(it.lastName)
            }
    }
}