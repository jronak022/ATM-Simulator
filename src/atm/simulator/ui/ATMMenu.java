package atm.simulator.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMMenu extends JFrame implements ActionListener {
    JButton depositButton, withdrawalButton, fastCashButton, miniStatementButton, pinChangeButton, balanceEnquiryButton,
            exitButton;
    String cardNumber;

    public ATMMenu(String cardNumber) {
        this.cardNumber = cardNumber;

        ImageIcon rawAtmIcon = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image scaledAtmImage = rawAtmIcon.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon atmImageIcon = new ImageIcon(scaledAtmImage);
        JLabel atmBackgroundLabel = new JLabel(atmImageIcon);
        atmBackgroundLabel.setBounds(0, 0, 1550, 830);
        add(atmBackgroundLabel);

        JLabel selectTransactionLabel = new JLabel("Please Select Your Transaction");
        selectTransactionLabel.setBounds(430, 180, 700, 35);
        selectTransactionLabel.setForeground(Color.WHITE);
        selectTransactionLabel.setFont(new Font("System", Font.BOLD, 28));
        atmBackgroundLabel.add(selectTransactionLabel);

        depositButton = new JButton("DEPOSIT");
        depositButton.setForeground(Color.WHITE);
        depositButton.setBackground(new Color(65, 125, 128));
        depositButton.setBounds(410, 274, 150, 35);
        depositButton.addActionListener(this);
        atmBackgroundLabel.add(depositButton);

        withdrawalButton = new JButton("CASH WITHDRAWL");
        withdrawalButton.setForeground(Color.WHITE);
        withdrawalButton.setBackground(new Color(65, 125, 128));
        withdrawalButton.setBounds(700, 274, 150, 35);
        withdrawalButton.addActionListener(this);
        atmBackgroundLabel.add(withdrawalButton);

        fastCashButton = new JButton("FAST CASH");
        fastCashButton.setForeground(Color.WHITE);
        fastCashButton.setBackground(new Color(65, 125, 128));
        fastCashButton.setBounds(410, 318, 150, 35);
        fastCashButton.addActionListener(this);
        atmBackgroundLabel.add(fastCashButton);

        miniStatementButton = new JButton("MINI STATEMENT");
        miniStatementButton.setForeground(Color.WHITE);
        miniStatementButton.setBackground(new Color(65, 125, 128));
        miniStatementButton.setBounds(700, 318, 150, 35);
        miniStatementButton.addActionListener(this);
        atmBackgroundLabel.add(miniStatementButton);

        pinChangeButton = new JButton("PIN CHANGE");
        pinChangeButton.setForeground(Color.WHITE);
        pinChangeButton.setBackground(new Color(65, 125, 128));
        pinChangeButton.setBounds(410, 362, 150, 35);
        pinChangeButton.addActionListener(this);
        atmBackgroundLabel.add(pinChangeButton);

        balanceEnquiryButton = new JButton("BALANCE ENQUIRY");
        balanceEnquiryButton.setForeground(Color.WHITE);
        balanceEnquiryButton.setBackground(new Color(65, 125, 128));
        balanceEnquiryButton.setBounds(700, 362, 150, 35);
        balanceEnquiryButton.addActionListener(this);
        atmBackgroundLabel.add(balanceEnquiryButton);

        exitButton = new JButton("EXIT");
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(65, 125, 128));
        exitButton.setBounds(700, 406, 150, 35);
        exitButton.addActionListener(this);
        atmBackgroundLabel.add(exitButton);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositButton) {
            new Deposit(cardNumber);
            setVisible(false);
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        } else if (e.getSource() == withdrawalButton) {
            new Withdrawal(cardNumber);
            setVisible(false);
        } else if (e.getSource() == balanceEnquiryButton) {
            new BalanceEnquiry(cardNumber);
            setVisible(false);
        } else if (e.getSource() == fastCashButton) {
            new FastCash(cardNumber);
            setVisible(false);
        } else if (e.getSource() == pinChangeButton) {
            new Pin(cardNumber);
            setVisible(false);
        } else if (e.getSource() == miniStatementButton) {
            new MiniStatement(cardNumber);
            setVisible(false);
        }
    }

}
