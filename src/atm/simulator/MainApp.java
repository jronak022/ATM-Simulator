package atm.simulator;

import atm.simulator.ui.Login;

import javax.swing.*;

/**
 * The entry point for the ATM Simulator application.
 * This class handles initialization of the UI on the Event Dispatch Thread
 * (EDT)
 * and applies the cross-platform look and feel for a consistent user
 * experience.
 */
public class MainApp {

    /**
     * The main entry point for the application.
     * Sets the UI look and feel and launches the Login screen on the Swing Event
     * Dispatch Thread.
     * 
     * @param args command line arguments (not used)
     * @see atm.simulator.ui.Login
     */
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
