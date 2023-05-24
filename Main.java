import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame implements ActionListener {
    private JTextField messageField;
    private JButton sendButton;
    private JTextArea messageArea;

    public Main() {
        // tạo ra giao diện cửa sổ
        setTitle("PMessage");
        setSize(1280, 720);
        setLayout(new BorderLayout());

        // tạo ra nơi để nhập tin nhắn
        messageField = new JTextField();
        add(messageField, BorderLayout.SOUTH);

        // Tạo ra nút Send
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        add(sendButton, BorderLayout.EAST);

        // Khu vực hiển thị tin nhắn
        messageArea = new JTextArea();
        add(messageArea, BorderLayout.CENTER);

        // Cửa sổ hiện ra và có thể tắt khi bấm thoát
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
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