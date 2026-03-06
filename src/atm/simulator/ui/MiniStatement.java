package atm.simulator.ui;

import atm.simulator.model.Transaction;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import atm.simulator.data.TransactionDAO;
import atm.simulator.data.UserDAO;
import atm.simulator.domain.ATMService;

public class MiniStatement extends JFrame implements ActionListener {
    String cardNumber;
    JButton backButton;
    ATMService atmService;

    public MiniStatement(String cardNumber) {
        this.cardNumber = cardNumber;
        this.atmService = new ATMService(new UserDAO(), new TransactionDAO());
        getContentPane().setBackground(new Color(255, 204, 204));
        setSize(400, 600);
        setLocation(20, 20);
        setLayout(null);

        JLabel cardLabel = new JLabel();
        cardLabel.setBounds(20, 80, 400, 400);
        add(cardLabel);

        JLabel titleLabel = new JLabel("MINI STATEMENT");
        titleLabel.setFont(new Font("System", Font.BOLD, 15));
        titleLabel.setBounds(150, 20, 200, 20);
        add(titleLabel);

        JLabel cardNumberLabel = new JLabel();
        cardNumberLabel.setBounds(20, 80, 300, 20);
        add(cardNumberLabel);

        JLabel balanceDisplayLabel = new JLabel();
        balanceDisplayLabel.setBounds(20, 450, 300, 20);
        add(balanceDisplayLabel);

        if (cardNumber != null && cardNumber.length() >= 16) {
            cardNumberLabel
                    .setText("Card Number:  " + cardNumber.substring(0, 4) + "XXXXXXXX" + cardNumber.substring(12));
        } else {
            cardNumberLabel.setText("Card Number:  " + (cardNumber != null ? cardNumber : "N/A"));
        }

        try {
            StringBuilder transactionsContent = new StringBuilder("<html>");
            List<Transaction> transactionList = atmService.getMiniStatement(cardNumber);
            double currentBalance = atmService.getBalance(cardNumber);

            for (Transaction t : transactionList) {
                String type = t.getType();
                double amt = t.getAmount();
                transactionsContent.append(t.getDate()).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                        .append(type).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                        .append(amt).append("<br><br>");
            }
            transactionsContent.append("</html>");

            cardLabel.setText(transactionsContent.toString());
            balanceDisplayLabel.setText("Your Total Balance is Rs " + currentBalance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        backButton = new JButton("Back");
        backButton.setBounds(20, 500, 100, 25);
        backButton.addActionListener(this);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        add(backButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            setVisible(false);
            new ATMMenu(cardNumber).setVisible(true);
        }
    }

}
