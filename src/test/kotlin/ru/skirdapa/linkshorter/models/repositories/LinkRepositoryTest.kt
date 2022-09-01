package ru.skirdapa.linkshorter.models.repositories

import com.github.springtestdbunit.annotation.DatabaseOperation
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DatabaseTearDown
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.TestPropertySource
import ru.skirdapa.linkshorter.models.AbstractRepositoryTest
import ru.skirdapa.linkshorter.models.Link
import ru.skirdapa.linkshorter.repositories.LinkRepository
import java.util.*

@DatabaseSetup(LinkRepositoryTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = [LinkRepositoryTest.DATASET])
open class LinkRepositoryTest : AbstractRepositoryTest() {

    @Autowired
    lateinit var repository: LinkRepository

    @Test
    fun `Найдена существующая запись`() {
        val got: Optional<Link> = repository.findById(LINK1_ID)
        assertThat(got.isPresent, equalTo(true))
        val link = got.get()
        assertEquals(link, Link(LINK1_TEXT, LINK1_ID))
    }

    @Test
    fun `Не находится несуществующая запись`() {
        val got: Optional<Link> = repository.findById(LINK_NOT_FOUND)
        assertFalse(got.isPresent)
    }

    @Test
    fun `Сохранено новое`() {
        val toBeSaved: Link = Link(LINK_TBS_TEXT)
        val got: Optional<Link> = repository.save(toBeSaved)
        val list: List<Link> = repository.findAll()

        assertEquals(list.size, 4)
        assertEquals(got.get().text, LINK_TBS_TEXT)
    }

    companion object {
        const val DATASET = "classpath:datasets/link-table.xml"
        const val LINK1_ID: Long = 100500L
        const val LINK1_TEXT = "https://ya.ru"
        const val LINK_NOT_FOUND = 123L
        const val LINK_TBS_TEXT = "https://rambler.ru"
    }

}