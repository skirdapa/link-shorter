package ru.skirdapa.linkshorter.services

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class DefaultKeyMapperServiceTest {

    private val LINK_A: String = "https://rambler.ru"
    private val LINK_B: String = "https://ya.ru"
    private val KEY: String = "test_key"
    private val KEY_A: String = "abc"
    private val KEY_B: String = "cda"
    private val ID_A: Long = 10000000L
    private val ID_B: Long = 10000001L

    @InjectMocks
    val service: KeyMapperService = DefaultKeyMapperService()

    @Mock
    lateinit var converter: KeyConverterService

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(converter.keyToId(KEY_A)).thenReturn(ID_A)
        Mockito.`when`(converter.idToKey(ID_A)).thenReturn(KEY_A)
        Mockito.`when`(converter.keyToId(KEY_B)).thenReturn(ID_B)
        Mockito.`when`(converter.idToKey(ID_B)).thenReturn(KEY_B)
    }

    @Test
    fun `Клиент может добавить ссылку`(){
        val keyA = service.add(LINK_A)
        assertEquals(KeyMapperService.Get.Link(LINK_A), service.getLink(keyA))
        val keyB = service.add(LINK_B)
        assertEquals(KeyMapperService.Get.Link(LINK_B), service.getLink(keyB))
        assertNotEquals(keyA, keyB)
    }


    @Test
    fun `Клиент не может получить получить ссылку по несуществующему ключу`(){
        assertEquals(KeyMapperService.Get.NotFound(KEY), service.getLink(KEY))
    }

}