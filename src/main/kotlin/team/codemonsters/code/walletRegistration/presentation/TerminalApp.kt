package team.codemonsters.code.walletRegistration.presentation

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import team.codemonsters.code.walletRegistration.application.WalletRegistrationService
import team.codemonsters.code.walletRegistration.domain.ClientId

@Component
class TerminalApp(
    val walletRegistrationService: WalletRegistrationService
) : CommandLineRunner {

    private val log = LoggerFactory.getLogger(TerminalApp::class.java)

    //
    override fun run(vararg args: String?) {
        if (args.isEmpty()) {
            log.info("Please type client id")
            return
        }
        //Пример формирования DTO из аргументов командной строки
        val registrationRequest = WalletRegistrationDTO(args[0].toString())
        println("Client id: ${registrationRequest.clientId}")

        //Проверяем Данные которые поступили на вход
        //val clientId = ClientId.emerge(registrationRequest.clientId)
//        val clientId = WalletRegistrationService.checkClientId(registrationRequest.clientId)
//        if (clientId.isFailure) {
//            println("Ошибка : ${clientId.exceptionOrNull()!!.message}")
//            return
//        }

        //Проверили запрос и передали в слой приложения
        val registrationResult = walletRegistrationService.registerWallet(registrationRequest)
        if(registrationResult.isFailure) {
            println("Ошибка : ${registrationResult.exceptionOrNull()!!.message}")
            return
        }
        println("Успешно зарегистрирован кошелек " +
                "${registrationResult.getOrThrow().walletId.value} " +
                "и привязан к клиенту ${registrationResult.getOrThrow().clientId.value}")

    }

}