package ru.skirdapa.linkshorter.services

import org.junit.Assert.*
import org.junit.Test


class DefaultKeyMapperServiceTest {

    private val LINK_NEW: String = "https://rambler.ru"
    private val LINK: String = "https://ya.ru"
    private val KEY: String = "test_key"

    val service: KeyMapperService = DefaultKeyMapperService()

    @Test
    fun `Клиент может добавить новый ключ со ссылкой`(){
        assertEquals(KeyMapperService.Add.Success(KEY, LINK), service.add(KEY, LINK))
        assertEquals(KeyMapperService.Get.Link(LINK), service.getLink(KEY))
    }

    @Test
    fun `Клиент не может добавить ключ если он уже существует`(){
        service.add(KEY, LINK)
        assertEquals(KeyMapperService.Add.AlreadyExist(KEY), service.add(KEY, LINK_NEW))
        assertEquals(KeyMapperService.Get.Link(LINK), service.getLink(KEY))
    }

    @Test
    fun `Клиент не может получить получить ссылку по несуществующему ключу`(){
        assertEquals(KeyMapperService.Get.NotFound(KEY), service.getLink(KEY))
    }

}