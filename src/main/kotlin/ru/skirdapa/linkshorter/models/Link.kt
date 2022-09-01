package ru.skirdapa.linkshorter.models

import javax.persistence.*


@Entity
@Table(name = "links")
data class Link(
    val text: String = "",
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "links_sequence")
    @SequenceGenerator(name = "links_sequence", sequenceName = "links_seq", allocationSize = 1)
    val id: Long = 0
)