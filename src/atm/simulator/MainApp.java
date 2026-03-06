package atm.simulator;

import atm.simulator.ui.Login;

import javax.swing.*;

/**
 * MainApp serves as the central entry point for the ATM Simulator application.
 * Like an 'index' file, it initializes the core UI components to start the
 * program.
 */
public class MainApp {

    public static void main(String[] args) {
        // Set cross-platform look and feel for consistent aesthetics
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Run the Login screen on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new Login();
        });
    }
}
