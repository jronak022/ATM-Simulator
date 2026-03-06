package atm.simulator.ui;

import atm.simulator.AppContext;
import atm.simulator.domain.ATMService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Deposit extends JFrame implements ActionListener {
    JButton depositButton, backButton;
    TextField amountInputField;
    String cardNumber;
    ATMService atmService;

    public Deposit(String cardNumber) {
        this.cardNumber = cardNumber;
        this.atmService = AppContext.getInstance().getATMService();

        ImageIcon rawAtmIcon = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image scaledAtmImage = rawAtmIcon.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon atmImageIcon = new ImageIcon(scaledAtmImage);
        JLabel atmBackgroundLabel = new JLabel(atmImageIcon);
        atmBackgroundLabel.setBounds(0, 0, 1550, 830);
        add(atmBackgroundLabel);

        JLabel depositHeaderLabel = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        depositHeaderLabel.setForeground(Color.WHITE);
        depositHeaderLabel.setFont(new Font("System", Font.BOLD, 16));
        depositHeaderLabel.setBounds(430, 180, 700, 35);
        atmBackgroundLabel.add(depositHeaderLabel);

        amountInputField = new TextField();
        amountInputField.setBackground(new Color(65, 125, 128));
        amountInputField.setForeground(Color.WHITE);
        amountInputField.setBounds(430, 230, 320, 25);
        amountInputField.setFont(new Font("Raleway", Font.BOLD, 22));
        atmBackgroundLabel.add(amountInputField);

        depositButton = new JButton("DEPOSIT");
        depositButton.setForeground(Color.WHITE);
        depositButton.setBackground(new Color(65, 125, 128));
        depositButton.setBounds(700, 362, 150, 35);
        depositButton.addActionListener(this);
        atmBackgroundLabel.add(depositButton);

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

            if (e.getSource() == depositButton) {
                if (amountStr.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Deposit");
                } else {
                    try {
                        double depositAmount = Double.parseDouble(amountStr);
                        if (depositAmount % 100 != 0) {
                            JOptionPane.showMessageDialog(null, "Please enter an amount that is a multiple of 100");
                        } else if (depositAmount > 10000) {
                            JOptionPane.showMessageDialog(null, "Maximum deposit amount is 10,000");
                        } else {
                            atmService.deposit(cardNumber, depositAmount);
                            JOptionPane.showMessageDialog(null, "Rs. " + amountStr + " Deposited Successfully");
                            setVisible(false);
                            new ATMMenu(cardNumber);
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
