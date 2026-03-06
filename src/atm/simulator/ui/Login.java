package atm.simulator.ui;

import atm.simulator.data.TransactionDAO;
import atm.simulator.data.UserDAO;
import atm.simulator.domain.ATMService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    JLabel welcomeLabel, cardNumberLabel, pinLabel;
    JTextField cardNumberTextField;
    JPasswordField pinPasswordField;
    ATMService atmService;

    JButton signInButton, clearButton;

    public Login() {
        this.atmService = new ATMService(new UserDAO(), new TransactionDAO());
        super("Bank Management System");
        ImageIcon bankIcon = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image scaledBankImage = bankIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon bankImageIcon = new ImageIcon(scaledBankImage);
        JLabel bankImageLabel = new JLabel(bankImageIcon);
        bankImageLabel.setBounds(350, 10, 100, 100);
        add(bankImageLabel);

        ImageIcon cardIcon = new ImageIcon(ClassLoader.getSystemResource("icon/card.png"));
        Image scaledCardImage = cardIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon cardImageIcon = new ImageIcon(scaledCardImage);
        JLabel cardImageLabel = new JLabel(cardImageIcon);
        cardImageLabel.setBounds(630, 350, 100, 100);
        add(cardImageLabel);

        welcomeLabel = new JLabel("WELCOME TO ATM");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("AvantGarde", Font.BOLD, 38));
        welcomeLabel.setBounds(230, 125, 450, 40);
        add(welcomeLabel);

        cardNumberLabel = new JLabel("Card No:");
        cardNumberLabel.setFont(new Font("Ralway", Font.BOLD, 28));
        cardNumberLabel.setForeground(Color.WHITE);
        cardNumberLabel.setBounds(150, 190, 375, 30);
        add(cardNumberLabel);

        cardNumberTextField = new JTextField(15);
        cardNumberTextField.setBounds(325, 190, 230, 30);
        cardNumberTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardNumberTextField);

        pinLabel = new JLabel("PIN: ");
        pinLabel.setFont(new Font("Ralway", Font.BOLD, 28));
        pinLabel.setForeground(Color.WHITE);
        pinLabel.setBounds(150, 250, 375, 30);
        add(pinLabel);

        pinPasswordField = new JPasswordField(15);
        pinPasswordField.setBounds(325, 250, 230, 30);
        pinPasswordField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinPasswordField);

        signInButton = new JButton("SIGN IN");
        signInButton.setFont(new Font("Arial", Font.BOLD, 14));
        signInButton.setForeground(Color.WHITE);
        signInButton.setBackground(Color.BLACK);
        signInButton.setBounds(300, 300, 100, 30);
        signInButton.addActionListener(this);
        add(signInButton);

        clearButton = new JButton("CLEAR");
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setForeground(Color.WHITE);
        clearButton.setBackground(Color.BLACK);
        clearButton.setBounds(430, 300, 100, 30);
        clearButton.addActionListener(this);
        add(clearButton);

        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icon/backbg.png"));
        Image scaledBackgroundImage = backgroundIcon.getImage().getScaledInstance(850, 480, Image.SCALE_DEFAULT);
        ImageIcon backgroundImageIcon = new ImageIcon(scaledBackgroundImage);
        JLabel backgroundImageLabel = new JLabel(backgroundImageIcon);
        backgroundImageLabel.setBounds(0, 0, 850, 480);
        add(backgroundImageLabel);

        setLayout(null);
        setSize(850, 480);
        setLocation(450, 200);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == signInButton) {
                String cardno = cardNumberTextField.getText();
                String pin = new String(pinPasswordField.getPassword());

                if (atmService.login(cardno, pin)) {
                    // Note: In the original code, Aid was fetched.
                    // Since we are migrating, we might need a way to get Aid from the user object.
                    // For now, I'll pass the card number as Aid to maintain compatibility.
                    setVisible(false);
                    new ATMMenu(cardno);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } else if (e.getSource() == clearButton) {
                cardNumberTextField.setText("");
                pinPasswordField.setText("");
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

}