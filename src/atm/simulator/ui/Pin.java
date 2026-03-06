package atm.simulator.ui;

import atm.simulator.data.TransactionDAO;
import atm.simulator.data.UserDAO;
import atm.simulator.domain.ATMService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pin extends JFrame implements ActionListener {
    JButton changeButton, backButton;
    JPasswordField pinField1, pinField2;
    String cardNumber;
    ATMService atmService;

    public Pin(String cardNumber) {
        this.cardNumber = cardNumber;
        this.atmService = new ATMService(new UserDAO(), new TransactionDAO());

        ImageIcon rawAtmIcon = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image scaledAtmImage = rawAtmIcon.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon atmImageIcon = new ImageIcon(scaledAtmImage);
        JLabel atmBackgroundLabel = new JLabel(atmImageIcon);
        atmBackgroundLabel.setBounds(0, 0, 1550, 830);
        add(atmBackgroundLabel);

        JLabel pinChangeHeaderLabel = new JLabel("CHANGE YOUR PIN");
        pinChangeHeaderLabel.setForeground(Color.WHITE);
        pinChangeHeaderLabel.setFont(new Font("System", Font.BOLD, 16));
        pinChangeHeaderLabel.setBounds(430, 180, 700, 35);
        atmBackgroundLabel.add(pinChangeHeaderLabel);

        JLabel newPinLabel = new JLabel("New PIN:");
        newPinLabel.setForeground(Color.WHITE);
        newPinLabel.setFont(new Font("System", Font.BOLD, 16));
        newPinLabel.setBounds(430, 220, 150, 35);
        atmBackgroundLabel.add(newPinLabel);

        pinField1 = new JPasswordField();
        pinField1.setBackground(new Color(65, 125, 128));
        pinField1.setForeground(Color.WHITE);
        pinField1.setBounds(600, 220, 180, 25);
        pinField1.setFont(new Font("Raleway", Font.BOLD, 22));
        atmBackgroundLabel.add(pinField1);

        JLabel reEnterPinLabel = new JLabel("Re-Enter New PIN: ");
        reEnterPinLabel.setForeground(Color.WHITE);
        reEnterPinLabel.setFont(new Font("System", Font.BOLD, 16));
        reEnterPinLabel.setBounds(430, 250, 400, 35);
        atmBackgroundLabel.add(reEnterPinLabel);

        pinField2 = new JPasswordField();
        pinField2.setBackground(new Color(65, 125, 128));
        pinField2.setForeground(Color.WHITE);
        pinField2.setBounds(600, 255, 180, 25);
        pinField2.setFont(new Font("Raleway", Font.BOLD, 22));
        atmBackgroundLabel.add(pinField2);

        changeButton = new JButton("CHANGE");
        changeButton.setBounds(700, 362, 150, 35);
        changeButton.setBackground(new Color(65, 125, 128));
        changeButton.setForeground(Color.WHITE);
        changeButton.addActionListener(this);
        atmBackgroundLabel.add(changeButton);

        backButton = new JButton("BACK");
        backButton.setBounds(700, 406, 150, 35);
        backButton.setBackground(new Color(65, 125, 128));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        atmBackgroundLabel.add(backButton);

        setSize(1550, 1080);
        setLayout(null);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String pin1 = new String(pinField1.getPassword());
            String pin2 = new String(pinField2.getPassword());

            if (!pin1.equals(pin2)) {
                JOptionPane.showMessageDialog(null, "Entered PINs do not match");
                return;
            }

            if (e.getSource() == changeButton) {
                if (pin1.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter New PIN");
                    return;
                }
                if (pin2.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Re-Enter New PIN");
                    return;
                }

                if (atmService.changePin(cardNumber, pin1)) {
                    JOptionPane.showMessageDialog(null, "PIN changed successfully");
                    setVisible(false);
                    new ATMMenu(cardNumber);
                } else {
                    JOptionPane.showMessageDialog(null, "Error changing PIN");
                }

            } else if (e.getSource() == backButton) {
                new ATMMenu(cardNumber);
                setVisible(false);
            }

        } catch (Exception E) {
            E.printStackTrace();
        }
    }

}