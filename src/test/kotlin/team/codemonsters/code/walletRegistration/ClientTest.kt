package team.codemonsters.code.walletRegistration

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ClientTest {

    @Test
    fun `should be valid client`() {
        //arrange
        val entity = ClientEntity(
            id = "96104464-54ea-44e6-876b-8d45428776e3",
            clientName = "Рома",
            walletId = null
        )
        //act
        val client = Client.emerge(entity)
        //assert
        assertThat(client.isSuccess).isTrue
    }
}