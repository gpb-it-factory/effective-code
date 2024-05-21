package team.codemonsters.code

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class TerminalApp : CommandLineRunner {

    private val log = LoggerFactory.getLogger(TerminalApp::class.java)

    override fun run(vararg args: String?) {
        lateinit var clientId: String
        if (args.isEmpty()) {
            log.info("Please type client id")
            var inputId = readlnOrNull()
            if (null == inputId) return
            clientId = inputId
        } else {
            clientId = args[0].toString()
        }
        println("client id: $clientId")
    }

}