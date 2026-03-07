package atm.simulator.ui;

import atm.simulator.AppContext;
import atm.simulator.domain.ATMService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FastCash extends JFrame implements ActionListener {

    JButton rs100Button, rs500Button, rs1000Button, rs2000Button, rs5000Button, rs10000Button, backButton;
    String cardNumber;
    ATMService atmService;

    public FastCash(String cardNumber) {
        this.cardNumber = cardNumber;
        this.atmService = AppContext.getInstance().getATMService();

        ImageIcon rawAtmIcon = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image scaledAtmImage = rawAtmIcon.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon atmImageIcon = new ImageIcon(scaledAtmImage);
        JLabel atmBackgroundLabel = new JLabel(atmImageIcon);
        atmBackgroundLabel.setBounds(0, 0, 1550, 830);
        add(atmBackgroundLabel);

        JLabel selectAmountLabel = new JLabel("SELECT WITHDRAWAL AMOUNT");
        selectAmountLabel.setBounds(445, 180, 700, 35);
        selectAmountLabel.setForeground(Color.WHITE);
        selectAmountLabel.setFont(new Font("System", Font.BOLD, 23));
        atmBackgroundLabel.add(selectAmountLabel);

        rs100Button = new JButton("Rs. 100");
        rs100Button.setForeground(Color.WHITE);
        rs100Button.setBackground(new Color(65, 125, 128));
        rs100Button.setBounds(410, 274, 150, 35);
        rs100Button.addActionListener(this);
        atmBackgroundLabel.add(rs100Button);

        rs500Button = new JButton("Rs. 500");
        rs500Button.setForeground(Color.WHITE);
        rs500Button.setBackground(new Color(65, 125, 128));
        rs500Button.setBounds(700, 274, 150, 35);
        rs500Button.addActionListener(this);
        atmBackgroundLabel.add(rs500Button);

        rs1000Button = new JButton("Rs. 1000");
        rs1000Button.setForeground(Color.WHITE);
        rs1000Button.setBackground(new Color(65, 125, 128));
        rs1000Button.setBounds(410, 318, 150, 35);
        rs1000Button.addActionListener(this);
        atmBackgroundLabel.add(rs1000Button);

        rs2000Button = new JButton("Rs. 2000");
        rs2000Button.setForeground(Color.WHITE);
        rs2000Button.setBackground(new Color(65, 125, 128));
        rs2000Button.setBounds(700, 318, 150, 35);
        rs2000Button.addActionListener(this);
        atmBackgroundLabel.add(rs2000Button);

        rs5000Button = new JButton("Rs. 5000");
        rs5000Button.setForeground(Color.WHITE);
        rs5000Button.setBackground(new Color(65, 125, 128));
        rs5000Button.setBounds(410, 362, 150, 35);
        rs5000Button.addActionListener(this);
        atmBackgroundLabel.add(rs5000Button);

        rs10000Button = new JButton("Rs. 10000");
        rs10000Button.setForeground(Color.WHITE);
        rs10000Button.setBackground(new Color(65, 125, 128));
        rs10000Button.setBounds(700, 362, 150, 35);
        rs10000Button.addActionListener(this);
        atmBackgroundLabel.add(rs10000Button);

        backButton = new JButton("BACK");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(65, 125, 128));
        backButton.setBounds(700, 406, 150, 35);
        backButton.addActionListener(this);
        atmBackgroundLabel.add(backButton);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            setVisible(false);
            new ATMMenu(cardNumber);
        } else {
            String amountStr = ((JButton) e.getSource()).getText().substring(4);
            try {
                double amount = Double.parseDouble(amountStr);
                ATMService.TransactionResult result = atmService.withdraw(cardNumber, amount);
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
                        JOptionPane.showMessageDialog(null, "Invalid amount or not a multiple of 100");
                        break;
                }
            } catch (Exception E) {
                E.printStackTrace();
            }
        }
    }

}
