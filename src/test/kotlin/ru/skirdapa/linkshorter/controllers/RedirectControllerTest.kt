package ru.skirdapa.linkshorter.controllers

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import ru.skirdapa.linkshorter.LinkShorterApplication
import ru.skirdapa.linkshorter.services.KeyMapperService

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [LinkShorterApplication::class])
@WebAppConfiguration
class RedirectControllerTest {


    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    lateinit var mockMvc: MockMvc

    @Mock
    lateinit var service: KeyMapperService

    @Autowired
    @InjectMocks
    lateinit var controller: RedirectController

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .build()

        Mockito.`when`(service.getLink(PATH)).thenReturn(KeyMapperService.Get.Link(HEADER_VALUE))
        Mockito.`when`(service.getLink(BAD_PATH)).thenReturn(KeyMapperService.Get.NotFound(BAD_PATH))

    }

    private val HEADER_VALUE = "https://ya.ru"
    private val HEADER_NAME = "Location"
    private val REDIRECT_STATUS: Int = 302
    private val PATH = "redirectPath"
    private val BAD_PATH = "badPath"
    private val NOT_FOUND: Int = 404

    @Test
    fun `Контроллер должен редиректить запросы`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/$PATH"))
            .andExpect(status().`is`(REDIRECT_STATUS))
            .andExpect(header().string(HEADER_NAME, HEADER_VALUE))
    }


    @Test
    fun `Контроллер должен возвращать 404`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/$BAD_PATH"))
            .andExpect(status().`is`(NOT_FOUND))
    }

}