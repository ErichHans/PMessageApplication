import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame implements ActionListener {
    private JTextField messageField;
    private JButton sendButton;
    private JTextArea messageArea;

    public Main() {
        // Set up the frame
        setTitle("PMessage");
        setSize(1280, 720);
        setLayout(new BorderLayout());

        // Create the message field
        messageField = new JTextField();
        add(messageField, BorderLayout.SOUTH);

        // Create the send button
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        add(sendButton, BorderLayout.EAST);

        // Create the message area
        messageArea = new JTextArea();
        add(messageArea, BorderLayout.CENTER);

        // Set up the frame to be visible and close when the window is closed
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendButton) {
            String message = messageField.getText();
            messageArea.append("Tin nhắn của bạn: " + message + "\n");
            messageField.setText("");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}