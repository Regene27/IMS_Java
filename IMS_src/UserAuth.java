package InventoryManagementSystem.IMS_src;

import javax.swing.*;
import javax.swing.text.FieldView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.FieldPosition;
import java.text.Format.Field;
import java.util.HashMap;
import java.util.Map;

public class UserAuth extends JFrame implements ActionListener {
    private Map<String, String> authorizedUsers;
    private JLabel usernameLabel, passwordLabel, messageLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public UserAuth() {
        authorizedUsers = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("authorized_users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                authorizedUsers.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTitle("Inventory Management System");
        setSize(720, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        Font labelFont = new Font("Arial", Font.PLAIN, 30);

        panel.setLayout(new GridLayout(3, 2));

        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        messageLabel = new JLabel("");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        usernameLabel.setFont(labelFont);
        usernameField.setFont(labelFont);

        passwordLabel.setFont(labelFont);
        passwordField.setFont(labelFont);

        messageLabel.setFont(labelFont);
        loginButton.setFont(labelFont);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(messageLabel);
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    public boolean isAuthorized(String username, String password) {
        String authorizedPassword = authorizedUsers.get(username);
        if (authorizedPassword != null && authorizedPassword.equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (isAuthorized(username, password)) {
            messageLabel.setText("Login successful!");
        } else {
            messageLabel.setText("Invalid username or password. Please try again.");
        }
    }

    public static void main(String[] args) {
        new UserAuth();
    }
}
