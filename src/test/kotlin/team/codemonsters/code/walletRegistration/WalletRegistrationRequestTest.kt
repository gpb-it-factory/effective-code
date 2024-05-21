package team.codemonsters.code.walletRegistration

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WalletRegistrationRequestTest {

    @Test
    fun `should be valid wallet registration request`() {
        //arrange
        val walletId = WalletId.emerge("bc1qar0srrr7xfkvy5l643lydnw9re59gtzzwf5mdq")
        val entity = ClientEntity(
            id = "96104464-54ea-44e6-876b-8d45428776e3",
            clientName = "Рома",
            walletId = null
        )
        val client = Client.emerge(entity)

        //act
        val walletRegistrationRequest = WalletRegistrationRequest.emerge(client.getOrThrow(), walletId.getOrThrow())

        //assert
        assertThat(walletRegistrationRequest.isSuccess).isTrue
    }
}