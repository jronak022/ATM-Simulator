package atm.simulator.domain.repository;

public interface IUserDAO {
    boolean authenticate(String cardNumber, String pin);

    boolean updatePin(String cardNumber, String newPin);
}
