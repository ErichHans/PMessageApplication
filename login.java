import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class login extends JFrame implements ActionListener {
    private JLabel userLabel;
    private JTextField userText;
    private JLabel passwordLabel;
    private JPasswordField passwordText;
    private JButton loginButton;

    public login() {
        super("Login Window");

        setLayout(new FlowLayout());

        userLabel = new JLabel("Username: ");
        add(userLabel);

        userText = new JTextField(10);
        add(userText);

        passwordLabel = new JLabel("Password: ");
        add(passwordLabel);

        passwordText = new JPasswordField(10);
        add(passwordText);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());

            // Check if the username and password are correct
            if (username.equals("admin") && password.equals("conmemayhung")) {
                // Login successful
                JOptionPane.showMessageDialog(this, "Login successful!");
            } else {
                // Login failed
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        }
    }

    public static void main(String[] args) {
        login login = new login();
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setSize(250, 300);
        login.setVisible(true);
    }
}