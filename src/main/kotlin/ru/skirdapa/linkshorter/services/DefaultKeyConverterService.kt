package ru.skirdapa.linkshorter.services

import org.springframework.stereotype.Component

@Component
class DefaultKeyConverterService : KeyConverterService {

    val chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-_".toCharArray()
    val charsMap = (0 until chars.size)
        .map { i -> Pair(chars[i], i.toLong()) }
        .toMap()

    override fun keyToId(key: String) = key
        .map { c -> charsMap[c]!! }
        .fold(0L) { a, b -> a * chars.size + b }

    override fun idToKey(id: Long): String {
        var n = id
        val builder = java.lang.StringBuilder()
        while (n != 0L) {
            builder.append(chars[(n % chars.size).toInt()])
            n /= chars.size
        }
        return builder.reverse().toString()
    }

}
