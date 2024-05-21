package team.codemonsters.code.walletRegistration

data class ClientEntity(
    //uuid
    val id: String,
    //9 cyrilic characters
    val clientName: String,
    //26-62 alphanumeric character
    val walletId: String?)