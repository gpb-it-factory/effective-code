package team.codemonsters.code.walletRegistration

class WalletRegistrationService(
    val clientGateway: ClientGateway,
    val walletGateway: WalletGateway
) {

    fun registerWallet(clientId: String) : Result<Client> {
        val clientId = ClientId.emerge(clientId)
        if(clientId.isFailure)
            return Result.failure(clientId.exceptionOrNull()!!)

        val client = clientGateway.findClient(clientId.getOrThrow())
        if(client.isFailure)
            return Result.failure(clientId.exceptionOrNull()!!)

        val walletId = walletGateway.generate()
        if(walletId.isFailure)
            return Result.failure(walletId.exceptionOrNull()!!)

        val walletRegistrationRequest = WalletRegistrationRequest.emerge(
            client.getOrThrow(),
            walletId.getOrThrow()
        )
        if(walletRegistrationRequest.isFailure)
            return Result.failure(walletRegistrationRequest.exceptionOrNull()!!)

        return clientGateway.registerWallet(walletRegistrationRequest.getOrThrow())

    }

}