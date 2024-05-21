package team.codemonsters.code.walletRegistration


data class WalletRegistrationRequest private constructor(
    val clientId: ClientId,
    val name: Name,
    val walletId: WalletId,
) {
    companion object {
        fun emerge(client: Client, walletId: WalletId) : Result<WalletRegistrationRequest> {
            if(WalletId.Empty==client.walletId) {
                return Result.success(WalletRegistrationRequest(client.clientId, client.name, walletId))
            }
            return Result.failure(RuntimeException("Клиент уже имеет кошелек"))
        }

    }
}