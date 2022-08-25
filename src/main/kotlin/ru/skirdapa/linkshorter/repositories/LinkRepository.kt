package ru.skirdapa.linkshorter.repositories

import org.springframework.data.repository.Repository
import ru.skirdapa.linkshorter.models.Link
import java.util.*

interface LinkRepository : Repository<Link, Long> {
    fun findById(id: Long?): Optional<Link>
    fun save(link: Link): Optional<Link>
    fun findAll(): List<Link>
}