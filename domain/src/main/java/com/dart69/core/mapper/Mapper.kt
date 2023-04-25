package com.dart69.core.mapper

fun interface Mapper<I, O> {
    fun map(from: I): O
}