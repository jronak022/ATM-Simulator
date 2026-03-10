package atm.simulator;

import atm.simulator.data.DatabaseConnection;
import atm.simulator.data.TransactionDAO;
import atm.simulator.data.UserDAO;
import atm.simulator.domain.ATMService;
import atm.simulator.domain.repository.ITransactionDAO;
import atm.simulator.domain.repository.IUserDAO;

/**
 * AppContext provides centralized access to application-wide singletons.
 * This ensures that only one instance of the database connection, DAOs,
 * and Services exist throughout the application lifecycle.
 */
public class AppContext {
    private static AppContext instance;

    private final DatabaseConnection dbConnection;
    private final IUserDAO userDAO;
    private final ITransactionDAO transactionDAO;
    private final ATMService atmService;

    private AppContext() {
        // Initialize core components once
        dbConnection = new DatabaseConnection();
        userDAO = new UserDAO(dbConnection);
        transactionDAO = new TransactionDAO(dbConnection);
        atmService = new ATMService(userDAO, transactionDAO);
    }

    /**
     * Gets the singleton instance of AppContext.
     * 
     * @return the AppContext instance
     */
    public static synchronized AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    /**
     * Gets the ATM service.
     * 
     * @return the atm service instance
     */
    public ATMService getATMService() {
        return atmService;
    }

    /**
     * Gets the user DAO.
     * 
     * @return the user dao instance
     */
    public IUserDAO getUserDAO() {
        return userDAO;
    }

    /**
     * Gets the transaction DAO.
     * 
     * @return the transaction dao instance
     */
    public ITransactionDAO getTransactionDAO() {
        return transactionDAO;
    }
}
