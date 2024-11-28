package planasleiman;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main {

    /**
     * Create the GUI and show it. For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        // Create and set up the main window
        JFrame frame = new JFrame("User Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Main content label
        JLabel titleLabel = new JLabel("Welcome to the Registration System");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setPreferredSize(new Dimension(300, 50));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Create a panel for the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Single button for registering users
        JButton registerButton = new JButton("Register New User");
        buttonPanel.add(registerButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Add action listener for the button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegistrationForm();
            }
        });

        // Set up and show the frame
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    /**
     * Open the registration form
     */
    private static void openRegistrationForm() {
        // Create a new frame for the registration form
        JFrame formFrame = new JFrame("User Registration Form");
        formFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formFrame.setLayout(new GridLayout(8, 2, 10, 10));
        formFrame.setSize(400, 350);

        // Create form components
        JLabel userTypeLabel = new JLabel("User Type:");
        String[] userTypes = {"Voluntario", "Persona Necesitada"};
        JComboBox<String> userTypeDropdown = new JComboBox<>(userTypes);

        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();

        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField();

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();

        JButton submitButton = new JButton("Submit");
        JLabel statusLabel = new JLabel("");

        // Add components to the form
        formFrame.add(userTypeLabel);
        formFrame.add(userTypeDropdown);

        formFrame.add(lastNameLabel);
        formFrame.add(lastNameField);

        formFrame.add(firstNameLabel);
        formFrame.add(firstNameField);

        formFrame.add(emailLabel);
        formFrame.add(emailField);

        formFrame.add(phoneLabel);
        formFrame.add(phoneField);

        formFrame.add(addressLabel);
        formFrame.add(addressField);

        formFrame.add(new JLabel()); // Empty space
        formFrame.add(submitButton);

        formFrame.add(new JLabel()); // Empty space
        formFrame.add(statusLabel);

        // Add functionality to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userType = (String) userTypeDropdown.getSelectedItem();
                String lastName = lastNameField.getText().trim();
                String firstName = firstNameField.getText().trim();
                String email = emailField.getText().trim();
                String phone = phoneField.getText().trim();
                String address = addressField.getText().trim();

                if (lastName.isEmpty() || firstName.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                    statusLabel.setText("Please fill in all fields.");
                    statusLabel.setForeground(Color.RED);
                } else {
                    // Simulate saving the data
                    System.out.println("User Type: " + userType);
                    System.out.println("Last Name: " + lastName);
                    System.out.println("First Name: " + firstName);
                    System.out.println("Email: " + email);
                    System.out.println("Phone: " + phone);
                    System.out.println("Address: " + address);

                    statusLabel.setText("Registration successful!");
                    statusLabel.setForeground(Color.GREEN);

                    // Clear fields
                    lastNameField.setText("");
                    firstNameField.setText("");
                    emailField.setText("");
                    phoneField.setText("");
                    addressField.setText("");
                }
            }
        });

        // Show the form
        formFrame.setLocationRelativeTo(null); // Center the form
        formFrame.setVisible(true);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

