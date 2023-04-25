package com.dart69.data.cache

interface Cache<K, V> {
    fun loadBy(key: K): V?

    interface Mutable<K, V> : Cache<K, V> {
        fun cacheBy(key: K, data: V)

        fun clear()
    }
}

operator fun <K, V> Cache<K, V>.get(key: K): V? = loadBy(key)

operator fun <K, V> Cache.Mutable<K, V>.set(key: K, data: V) = cacheBy(key, data)