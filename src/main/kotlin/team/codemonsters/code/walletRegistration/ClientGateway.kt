package team.codemonsters.code.walletRegistration

class ClientGateway(val clientRepository: ClientRepository) {

    fun findClient(clientId: ClientId): Result<Client> {
        val client = clientRepository.find(clientId.value.toString())
        return if (null == client) {
            Result.failure(RuntimeException("Client Not Found"))
        } else {
            map(client.getOrThrow())
        }
    }

    private fun map(clientEntity: ClientEntity): Result<Client> {
        return Client.emerge(clientEntity)
    }

    fun registerWallet(walletRegistrationRequest: WalletRegistrationRequest): Result<Client> {
        val updatedClient = clientRepository.update(
            ClientEntity(
                id = walletRegistrationRequest.clientId.value.toString(),
                clientName = walletRegistrationRequest.name.value,
                walletId = walletRegistrationRequest.walletId.value.toString()
            )
        )
        if(updatedClient.isFailure) {
            return Result.failure(updatedClient.exceptionOrNull()!!)
        }
        return map(updatedClient.getOrThrow())
    }

//    //look
//    private fun map(clientEntity: ClientEntity): Result<Client> {
//        val clientIdResult = ClientId.emerge(clientEntity.id)
//        if (clientIdResult.isSuccess) {
//            return
//        }
//        return Result.failure(RuntimeException("Невозможно собрать идентификатор клиента из хранилища"))
//    }





}