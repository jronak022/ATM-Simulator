package atm.simulator.ui;

import atm.simulator.data.TransactionDAO;
import atm.simulator.data.UserDAO;
import atm.simulator.domain.ATMService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Withdrawal extends JFrame implements ActionListener {
    JButton withdrawButton, backButton;
    TextField amountInputField;
    String cardNumber;
    ATMService atmService;

    public Withdrawal(String cardNumber) {
        this.cardNumber = cardNumber;
        this.atmService = new ATMService(new UserDAO(), new TransactionDAO());

        ImageIcon rawAtmIcon = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image scaledAtmImage = rawAtmIcon.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon atmImageIcon = new ImageIcon(scaledAtmImage);
        JLabel atmBackgroundLabel = new JLabel(atmImageIcon);
        atmBackgroundLabel.setBounds(0, 0, 1550, 830);
        add(atmBackgroundLabel);

        JLabel maxWithdrawalLabel = new JLabel("MAXIMUM WITHDRAWAL IS RS.10,000");
        maxWithdrawalLabel.setForeground(Color.WHITE);
        maxWithdrawalLabel.setFont(new Font("System", Font.BOLD, 16));
        maxWithdrawalLabel.setBounds(460, 180, 700, 35);
        atmBackgroundLabel.add(maxWithdrawalLabel);

        JLabel enterAmountLabel = new JLabel("PLEASE ENTER YOUR AMOUNT");
        enterAmountLabel.setForeground(Color.WHITE);
        enterAmountLabel.setFont(new Font("System", Font.BOLD, 16));
        enterAmountLabel.setBounds(460, 220, 400, 35);
        atmBackgroundLabel.add(enterAmountLabel);

        amountInputField = new TextField();
        amountInputField.setBackground(new Color(65, 125, 128));
        amountInputField.setForeground(Color.WHITE);
        amountInputField.setBounds(460, 260, 320, 25);
        amountInputField.setFont(new Font("Raleway", Font.BOLD, 22));
        atmBackgroundLabel.add(amountInputField);

        withdrawButton = new JButton("WITHDRAW");
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.setBackground(new Color(65, 125, 128));
        withdrawButton.setBounds(700, 362, 150, 35);
        withdrawButton.addActionListener(this);
        atmBackgroundLabel.add(withdrawButton);

        backButton = new JButton("Back");
        backButton.setBounds(700, 406, 150, 35);
        backButton.setBackground(new Color(65, 125, 128));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        atmBackgroundLabel.add(backButton);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String amountStr = amountInputField.getText();

            if (e.getSource() == withdrawButton) {
                if (amountStr.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to withdraw");
                } else {
                    try {
                        double withdrawAmount = Double.parseDouble(amountStr);
                        ATMService.TransactionResult result = atmService.withdraw(cardNumber, withdrawAmount);
                        switch (result) {
                            case SUCCESS:
                                JOptionPane.showMessageDialog(null, "Rs. " + amountStr + " Debited Successfully");
                                setVisible(false);
                                new ATMMenu(cardNumber);
                                break;
                            case INSUFFICIENT_BALANCE:
                                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                                break;
                            case EXCEEDS_LIMIT:
                                JOptionPane.showMessageDialog(null, "Maximum withdrawal amount is 10,000");
                                break;
                            case INVALID_AMOUNT:
                                JOptionPane.showMessageDialog(null,
                                        "Please enter an amount that is a multiple of 100");
                                break;
                        }
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid numeric amount");
                    }
                }
            } else if (e.getSource() == backButton) {
                setVisible(false);
                new ATMMenu(cardNumber);
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

}
