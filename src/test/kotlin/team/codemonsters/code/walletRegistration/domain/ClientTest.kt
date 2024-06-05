package team.codemonsters.code.walletRegistration.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import team.codemonsters.code.walletRegistration.infrastructure.ClientEntity

class ClientTest {

    @Test
    fun `should emerge valid client`() {
        val entity = ClientEntity(
            id = "96104464-54ea-44e6-876b-8d45428776e3",
            clientName = "Рома",
            walletId = null
        )

        val client = Client.emerge(entity.id, entity.clientName, entity.walletId)

        assertClient(client)
    }

    private fun assertClient(client: Result<Client>) {
        assertThat(client.isSuccess).isTrue
        assertThat(client.getOrThrow().clientId.value.toString()).isEqualTo("96104464-54ea-44e6-876b-8d45428776e3")
        assertThat(client.getOrThrow().name.value).isEqualTo("Рома")
        assertThat(client.getOrThrow().walletId).isEqualTo(WalletId.Empty)
    }
}