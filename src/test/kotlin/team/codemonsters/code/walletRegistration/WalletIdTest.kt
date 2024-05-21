package team.codemonsters.code.walletRegistration

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WalletIdTest {

    @Test
    fun validAddress() {
        val walletId = WalletId.emerge("3J98t1WpEZ73CNmQviecrnyiWrnqRhWNLy")
        assertThat(walletId.isSuccess).isTrue
    }

    @Test
    fun invalidShortAddressLength() {
        val walletId = WalletId.emerge("3J98t")
        assertThat(walletId.isFailure).isTrue()
    }

    @Test
    fun invalidLongAddressLength() {
        val walletId = WalletId.emerge("3J98t1WpEZ73CNmQviecrnyiWrnqRhWNLy3J98t1WpEZ73CNmQviecrnyiWrnqRhWNLy3J98t1WpEZ73CNmQviecrnyiWrnqRhWNLy")
        assertThat(walletId.isFailure).isTrue()
    }

}