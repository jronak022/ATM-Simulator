package atm.simulator.ui;

import atm.simulator.AppContext;
import atm.simulator.domain.ATMService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * UI screen for balance enquiry.
 */
public class BalanceEnquiry extends JFrame implements ActionListener {
    JLabel balanceLabel;
    JButton backButton;
    String cardNumber;
    ATMService atmService;

    /**
     * Constructs the BalanceEnquiry frame.
     * 
     * @param cardNumber the card number
     */
    public BalanceEnquiry(String cardNumber) {
        this.cardNumber = cardNumber;
        this.atmService = AppContext.getInstance().getATMService();

        ImageIcon rawAtmIcon = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image scaledAtmImage = rawAtmIcon.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon atmImageIcon = new ImageIcon(scaledAtmImage);
        JLabel atmBackgroundLabel = new JLabel(atmImageIcon);
        atmBackgroundLabel.setBounds(0, 0, 1550, 830);
        add(atmBackgroundLabel);

        JLabel balanceTitleLabel = new JLabel("Your Available Balance is Rs ");
        balanceTitleLabel.setForeground(Color.WHITE);
        balanceTitleLabel.setFont(new Font("System", Font.BOLD, 16));
        balanceTitleLabel.setBounds(430, 180, 700, 35);
        atmBackgroundLabel.add(balanceTitleLabel);

        balanceLabel = new JLabel();
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setFont(new Font("System", Font.BOLD, 16));
        balanceLabel.setBounds(430, 220, 400, 35);
        atmBackgroundLabel.add(balanceLabel);

        backButton = new JButton("Back");
        backButton.setBounds(700, 406, 150, 35);
        backButton.setBackground(new Color(65, 125, 128));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        atmBackgroundLabel.add(backButton);

        double currentBalance = atmService.getBalance(cardNumber);
        balanceLabel.setText("" + currentBalance);
        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new ATMMenu(cardNumber);
    }

}
