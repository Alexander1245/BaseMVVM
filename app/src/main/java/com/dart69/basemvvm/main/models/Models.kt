package com.dart69.basemvvm.main.models

data class Pet(
    val id: Int,
    val name: String,
    val age: Int,
)

fun createPets(count: Int): List<Pet> = List(count) { index ->
    Pet(index, "Name $index", index + 10)
}