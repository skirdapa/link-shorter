package ru.skirdapa.linkshorter.services

import org.junit.Assert
import org.junit.Test
import org.springframework.stereotype.Component
import java.util.Random

@Component
class DefaultKeyConverterServiceTest {

    val service: KeyConverterService = DefaultKeyConverterService()

    @Test
    fun `Ид должен конвертироваться в обе стороны`(){
        val rand = Random()
        for( i in 0..1000){
            val initialId = Math.abs(rand.nextLong())
            val key = service.idToKey(initialId)
            val id = service.keyToId(key)
            Assert.assertEquals(initialId, id)
        }
    }

}