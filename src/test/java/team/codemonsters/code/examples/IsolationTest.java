package team.codemonsters.code.examples;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import team.codemonsters.code.examples.code.BankingService;

import java.math.BigDecimal;


public class IsolationTest {
    @Nested
    public class WhiteBoxTest {
        @Test
        public void testTransferMoney_SuccessfulTransaction_LogsTransaction() {
            BankingService.Account fromAccount = Mockito.mock(BankingService.Account.class);
            BankingService.Account toAccount = Mockito.mock(BankingService.Account.class);
            Mockito.when(fromAccount.getBalance()).thenReturn(BigDecimal.valueOf(1000.0));
            Mockito.when(toAccount.getBalance()).thenReturn(BigDecimal.valueOf(500.0));
            BankingService.TransactionLogger transactionLogger = Mockito.mock(BankingService.TransactionLogger.class);
            BankingService bankingService = new BankingService(transactionLogger);

            bankingService.transferMoney(fromAccount, toAccount, BigDecimal.valueOf(500));

            Mockito.verify(transactionLogger, Mockito.times(1)).logTransaction(fromAccount, toAccount, BigDecimal.valueOf(500));
        }
    }

    @Nested
    public class BlackBoxTest {
        @Test
        @DisplayName("Успешная транзакция между счетами")
        public void successfulTransaction() {
            BankingService.Account fromAccount = new BankingService.Account("12345", BigDecimal.valueOf(1000));
            BankingService.Account toAccount = new BankingService.Account("54321", BigDecimal.valueOf(500));
            BankingService bankingService = new BankingService(new BankingService.TransactionLogger());

            boolean isTransferSuccessful = bankingService.transferMoney(fromAccount, toAccount, BigDecimal.valueOf(500));

            assertSuccessfulTransfer(isTransferSuccessful, fromAccount, toAccount);
        }

        private static void assertSuccessfulTransfer(boolean success, BankingService.Account fromAccount, BankingService.Account toAccount) {
            SoftAssertions.assertSoftly(softly -> {
                        softly.assertThat(success).isTrue();
                        softly.assertThat(fromAccount.getBalance()).isEqualTo(BigDecimal.valueOf(500));
                        softly.assertThat(toAccount.getBalance()).isEqualTo(BigDecimal.valueOf(1000));
                    }
            );
        }
    }
}
