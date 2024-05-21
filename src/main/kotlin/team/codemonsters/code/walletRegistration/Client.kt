package team.codemonsters.code.walletRegistration

data class Client(
    val clientId: ClientId,
    val name: Name,
    val walletId: WalletId
) {

    companion object {
        fun emerge(entity: ClientEntity): Result<Client> {
            val clientId = ClientId.emerge(entity.id)
            if (clientId.isFailure)
                return Result.failure(clientId.exceptionOrNull()!!)

            val name = Name.emerge(entity.clientName)
            if (name.isFailure)
                return Result.failure(name.exceptionOrNull()!!)

            lateinit var walletId: WalletId
            if (null == entity.walletId)
                walletId = WalletId.Empty
            else {
                val walletIdResult = WalletId.emerge(entity.walletId)
                if (walletIdResult.isFailure)
                    return Result.failure(walletIdResult.exceptionOrNull()!!)
                walletId = walletIdResult.getOrThrow()
            }

            return Result.success(Client(clientId.getOrThrow(), name.getOrThrow(), walletId))

        }
    }
}