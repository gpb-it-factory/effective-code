package team.codemonsters.code.walletRegistration.application

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import team.codemonsters.code.walletRegistration.domain.Client
import team.codemonsters.code.walletRegistration.domain.ClientId
import team.codemonsters.code.walletRegistration.infrastructure.ClientRepository

class WalletRegistrationServiceTest {

    @Test
    fun `should register wallet`() {
        //arrange
        val service = WalletRegistrationService(ClientGateway(ClientRepository()), WalletGateway())
        val clientId = ClientId.emerge("96104464-54ea-44e6-876b-8d45428776e3").getOrThrow()
        //act
        val result = service.registerWallet(clientId)
        //assert
        assertThatWalletRegistered(result)
    }

    private fun assertThatWalletRegistered(result: Result<Client>) {
        assertThat(result.isSuccess).isTrue
        assertThat(result.getOrThrow().walletId.value).isEqualTo("3J98t1WpEZ73CNmQviecrnyiWrnqRhWNLy")
    }

}