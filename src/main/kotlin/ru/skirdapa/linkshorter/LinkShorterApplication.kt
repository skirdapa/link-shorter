package ru.skirdapa.linkshorter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LinkShorterApplication

fun main(args: Array<String>) {
	runApplication<LinkShorterApplication>(*args)
}
