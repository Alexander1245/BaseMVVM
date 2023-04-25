package com.dart69.data.cache

import java.util.concurrent.ConcurrentHashMap

class CacheImpl<K : Any, V : Any> : Cache.Mutable<K, V> {
    private val map = ConcurrentHashMap<K, V>()

    override fun loadBy(key: K): V? = map[key]

    override fun cacheBy(key: K, data: V) {
        map[key] = data
    }

    override fun clear() {
        map.clear()
    }
}