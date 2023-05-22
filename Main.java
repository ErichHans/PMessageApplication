import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener {
    private JTextArea messageArea; // Vùng hiển thị tin nhắn
    private JTextField inputField; // Trường nhập liệu tin nhắn

    public Main() {
        super("PMessage Application"); // Tiêu đề cửa sổ

        // Khởi tạo các thành phần giao diện người dùng
        messageArea = new JTextArea(10, 30);
        messageArea.setEditable(false);
        inputField = new JTextField(30);
        JButton sendButton = new JButton("Send");

        // Đăng ký lắng nghe sự kiện cho nút Send và trường nhập liệu
        sendButton.addActionListener(this);
        inputField.addActionListener(this);

        // Thêm các thành phần vào cửa sổ chính
        add(new JScrollPane(messageArea), BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);
        add(sendButton, BorderLayout.EAST);

        // Thiết lập cửa sổ chính
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String message = inputField.getText(); // Lấy nội dung tin nhắn từ trường nhập liệu
        messageArea.append(message + "\n"); // Thêm tin nhắn vào vùng hiển thị
        inputField.setText(""); // Xóa trường nhập liệu
    }

    public static void main(String[] args) {
        new Main();
    }
}