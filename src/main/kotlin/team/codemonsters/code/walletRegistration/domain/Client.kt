package team.codemonsters.code.walletRegistration.domain

import team.codemonsters.code.walletRegistration.infrastructure.ClientEntity

data class Client(
    val clientId: ClientId,
    val name: Name,
    val walletId: WalletId
) {

    companion object {
        fun emerge(clientId : String, name : String, wllId: String?): Result<Client> {
            val clientIdResult = ClientId.emerge(clientId)
            if (clientIdResult.isFailure)
                return Result.failure(clientIdResult.exceptionOrNull()!!)

            val nameResult = Name.emerge(name)
            if (nameResult.isFailure)
                return Result.failure(nameResult.exceptionOrNull()!!)

            lateinit var walletId: WalletId
            if (null == wllId) {
                walletId = WalletId.Empty
                return Result.success(Client(clientIdResult.getOrThrow(), nameResult.getOrThrow(), walletId))
            }

            val walletIdResult = WalletId.emerge(wllId)
            if (walletIdResult.isFailure)
                return Result.failure(walletIdResult.exceptionOrNull()!!)
            walletId = walletIdResult.getOrThrow()

            return Result.success(Client(clientIdResult.getOrThrow(), nameResult.getOrThrow(), walletId))

        }
    }
}