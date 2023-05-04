package com.dart69.basemvvm.main

data class Pet(
    val id: Int,
    val name: String,
    val age: Int,
)

data class Person(
    val number: Int,
    val fullName: String,
)

fun createPets(count: Int): List<Pet> = List(count) { index ->
    Pet(index, "Name $index", index + 10)
}

fun createPersons(count: Int): List<Person> = List(count) { index ->
    Person(index, "FullName $index")
}