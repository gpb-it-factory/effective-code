package team.codemonsters.code.walletRegistration.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NameTest {
    @Test
    fun `should be valid name`() {
        val name = Name.emerge("Рома")

        assertValidName(name)
    }

    private fun assertValidName(name: Result<Name>) {
        assertThat(name.isSuccess).isTrue
        assertThat(name.getOrThrow().value).isEqualTo("Рома")
    }

    @Test
    fun `should be invalid name cause longer`() {
        val name = Name.emerge("Имядлиннеечемдевятьсимволов")

        assertThat(name.isFailure).isTrue
    }

    @Test
    fun `should be invalid name cause lowercase`() {
        val name = Name.emerge("имя")

        assertThat(name.isFailure).isTrue
    }

}