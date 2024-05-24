package team.codemonsters.code.walletRegistration.application

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import team.codemonsters.code.walletRegistration.domain.Client
import team.codemonsters.code.walletRegistration.domain.ClientId
import team.codemonsters.code.walletRegistration.domain.WalletRegistrationRequest
import team.codemonsters.code.walletRegistration.presentation.WalletRegistrationDTO

@Service
class WalletRegistrationService(
    private val clientGateway: ClientGateway,
    private val walletGateway: WalletGateway
) {
    private val log = LoggerFactory.getLogger(WalletRegistrationService::class.java)
    fun registerWallet(walletRegistrationDTO: WalletRegistrationDTO): Result<Client> {

        val clientIdResult = ClientId.emerge(walletRegistrationDTO.clientId)
        if (clientIdResult.isFailure) {
            log.info("Wrong client id")
            return Result.failure(clientIdResult.exceptionOrNull()!!)
        }

        val clientResult = clientGateway.findClient(clientIdResult.getOrThrow())
        if (clientResult.isFailure)
            return Result.failure(clientResult.exceptionOrNull()!!)

        val walletId = walletGateway.registerWallet()
        if (walletId.isFailure)
            return Result.failure(walletId.exceptionOrNull()!!)

        val walletRegistrationRequest = WalletRegistrationRequest.emerge(
            clientResult.getOrThrow(),
            walletId.getOrThrow()
        )
        if (walletRegistrationRequest.isFailure)
            return Result.failure(walletRegistrationRequest.exceptionOrNull()!!)

        return clientGateway.registerWallet(walletRegistrationRequest.getOrThrow())
    }

//    companion object {
//        fun checkClientId(clientId: String): Result<ClientId> {
//            val clientIdResult = ClientId.emerge(clientId)
//            if (clientIdResult.isFailure) {
//                println("Ошибка : ${clientIdResult.exceptionOrNull()!!.message}")
//                return Result.failure(clientIdResult.exceptionOrNull()!!)
//            }
//            return clientIdResult;
//        }
//    }
}