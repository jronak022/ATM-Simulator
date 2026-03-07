package atm.simulator.domain;

import atm.simulator.domain.repository.ITransactionDAO;
import atm.simulator.domain.repository.IUserDAO;
import atm.simulator.model.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 * THIS IS A TEMPLATE FOR UNIT TESTING.
 * In a real project, you would use a framework like JUnit and Mockito.
 * This class demonstrates how the Clean Architecture allows you to test
 * business logic in isolation using "Mocks" or "Stubs".
 */
public class ATMServiceTest {

    public static void main(String[] args) {
        testWithdrawalSuccess();
        testWithdrawalInsufficientBalance();
    }

    private static void testWithdrawalSuccess() {
        System.out.println("Testing Withdrawal Success...");

        // Mocking dependencies
        IUserDAO mockUserDAO = null; // Not needed for withdrawal test
        ITransactionDAO mockTransactionDAO = new ITransactionDAO() {
            @Override
            public void addTransaction(String aid, String type, double amt) {
            }

            @Override
            public List<Transaction> getTransactions(String aid) {
                return new ArrayList<>();
            }

            @Override
            public double getBalance(String aid) {
                return 5000.0;
            } // Initial balance
        };

        ATMService service = new ATMService(mockUserDAO, mockTransactionDAO);
        ATMService.TransactionResult result = service.withdraw("12345678", 1000.0);

        if (result == ATMService.TransactionResult.SUCCESS) {
            System.out.println("PASSED: Withdrawal was successful.");
        } else {
            System.out.println("FAILED: Expected SUCCESS but got " + result);
        }
    }

    private static void testWithdrawalInsufficientBalance() {
        System.out.println("Testing Withdrawal Insufficient Balance...");

        ITransactionDAO mockTransactionDAO = new ITransactionDAO() {
            @Override
            public void addTransaction(String aid, String type, double amt) {
            }

            @Override
            public List<Transaction> getTransactions(String aid) {
                return new ArrayList<>();
            }

            @Override
            public double getBalance(String aid) {
                return 500.0;
            } // Low balance
        };

        ATMService service = new ATMService(null, mockTransactionDAO);
        ATMService.TransactionResult result = service.withdraw("12345678", 1000.0);

        if (result == ATMService.TransactionResult.INSUFFICIENT_BALANCE) {
            System.out.println("PASSED: Correctly identified insufficient balance.");
        } else {
            System.out.println("FAILED: Expected INSUFFICIENT_BALANCE but got " + result);
        }
    }
}
