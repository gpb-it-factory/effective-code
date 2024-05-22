package team.codemonsters.code.walletRegistration.application

import org.springframework.stereotype.Service
import team.codemonsters.code.walletRegistration.domain.*
import team.codemonsters.code.walletRegistration.infrastructure.ClientEntity
import team.codemonsters.code.walletRegistration.infrastructure.ClientRepository

/**
 * Транслирует данные из уровня хранения данных в уровень приложения.
 * Обеспечивает интеграцию с репозиторием и абстрагирует доменную модель от уровня хранения.
 */
@Service
class ClientGateway(val clientRepository: ClientRepository) {

    fun findClient(clientId: ClientId): Result<Client> {
        val client = clientRepository.find(clientId.value.toString())
        if (client.isFailure) {
            return Result.failure(client.exceptionOrNull()!!)
        }
        return map(client.getOrThrow())
    }

    private fun map(clientEntity: ClientEntity): Result<Client> {
        val clientId = clientEntity.id
        val name = clientEntity.clientName
        val walletId = clientEntity.walletId
        return Client.emerge(clientId, name, walletId)
    }

    fun registerWallet(walletRegistrationRequest: WalletRegistrationRequest): Result<Client> {
        val updatedClient = clientRepository.update(ClientEntity.from(walletRegistrationRequest))
        if(updatedClient.isFailure) {
            return Result.failure(updatedClient.exceptionOrNull()!!)
        }
        return map(updatedClient.getOrThrow())
    }

}