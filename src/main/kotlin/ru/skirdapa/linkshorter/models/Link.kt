package ru.skirdapa.linkshorter.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "links")
data class Link (
    val text: String = "",
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    val id: Long = 0
)