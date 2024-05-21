package team.codemonsters.code.walletRegistration

class WalletGateway {

    fun generate(): Result<WalletId> =
        WalletId.emerge("3J98t1WpEZ73CNmQviecrnyiWrnqRhWNLy")
}