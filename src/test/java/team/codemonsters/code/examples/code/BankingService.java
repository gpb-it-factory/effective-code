package team.codemonsters.code.examples.code;

import java.math.BigDecimal;

/**
 * Упрощённый пример сервиса для транзакций между счетами для демонстрации
 */
public class BankingService {
    private final TransactionLogger transactionLogger;

    public BankingService(TransactionLogger transactionLogger) {
        this.transactionLogger = transactionLogger;
    }

    public boolean transferMoney(Account fromAccount, Account toAccount, BigDecimal amount) {
        if (fromAccount == null || toAccount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            return false;
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        transactionLogger.logTransaction(fromAccount, toAccount, amount);

        return true;
    }

    public static class TransactionLogger {
        public void logTransaction(Account fromAccount, Account toAccount, BigDecimal amount) {
            System.out.println("Transaction logged: " + fromAccount.getAccountId() + " transferred " + amount + " to " + toAccount.getAccountId());
        }
    }

    public static class Account {
        private final String accountId;
        private BigDecimal balance;

        public Account(String accountId, BigDecimal balance) {
            this.accountId = accountId;
            this.balance = balance;
        }

        public String getAccountId() {
            return accountId;
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public void deposit(BigDecimal amount) {
            balance = balance.add(amount);
        }

        public void withdraw(BigDecimal amount) {
            balance = balance.subtract(amount);
        }
    }
}
