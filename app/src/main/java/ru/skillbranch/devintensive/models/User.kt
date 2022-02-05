package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    val firstName: String?,
    var lastName: String?,
    val avatar: String?,
    val rating: Int = 0,
    val respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false
) {


    constructor(id: String, firstName: String?, lastName: String?) : this(
        id,
        firstName,
        lastName,
        null
    )

    constructor(id: String) : this(id, "John", "Doe")

    init {

        println("It's Alive!!! \n" +
                "${if(lastName==="Doe") "His name id $firstName $lastName" else "And his name is $firstName $lastName!!!"}\n")
    }

    companion object Factory{
        private var lastId:Int=-1
        fun makeUser(fullName:String?):User{
            lastId++

            val (firstName,lastName)=Utils.parseFullName(fullName)

            return User(id= "$lastId", firstName = firstName, lastName = lastName)
        }
    }
}