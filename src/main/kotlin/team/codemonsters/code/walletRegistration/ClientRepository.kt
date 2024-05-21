package team.codemonsters.code.walletRegistration

class ClientRepository {

    private val clients: MutableList<ClientEntity> = mutableListOf(
        ClientEntity(
            id = "96104464-54ea-44e6-876b-8d45428776e3",
            clientName = "Максим",
            walletId = "bc1qar0srrr7xfkvy5l643lydnw9re59gtzzwf5mdq"
        ),
        ClientEntity(
            id = "96104464-54ea-44e6-876b-8d45428776e3",
            clientName = "Рома",
            walletId = null
        )
    )

    fun find(clientId: String): Result<ClientEntity> {
        val client = clients.find { client -> clientId == client.id }
        return if (null == client) {
            Result.failure(RuntimeException("Client not found"))
        } else {
            Result.success(client)
        }
    }

    fun update(updatedClient: ClientEntity): Result<ClientEntity> {
        val client = clients.find { client -> updatedClient.id == client.id }
        if(null == client) {
            return Result.failure(RuntimeException("Client not found"))
        }
        clients.remove(client)
        clients.add(updatedClient)
        return Result.success(updatedClient)
    }

}