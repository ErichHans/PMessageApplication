import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class RegisterWindow extends JFrame implements ActionListener {
    private JTextField usernameField; // Trường nhập liệu tên đăng nhập
    private JPasswordField passwordField; // Trường nhập liệu mật khẩu
    private JTextField emailField; // Trường nhập liệu email

    public RegisterWindow() {
        super("Register"); // Tiêu đề cửa sổ

        // Khởi tạo các thành phần giao diện người dùng
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        emailField = new JTextField(10);
        JButton registerButton = new JButton("Register");

        // Đăng ký lắng nghe sự kiện cho nút Register
        registerButton.addActionListener(this);

        // Tạo một bảng để chứa các thành phần giao diện người dùng
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(registerButton);

        // Thêm bảng vào cửa sổ chính
        add(panel);

        // Thiết lập cửa sổ chính
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText(); // Lấy tên đăng nhập từ trường nhập liệu
        String password = new String(passwordField.getPassword()); // Lấy mật khẩu từ trường nhập liệu
        String email = emailField.getText(); // Lấy email từ trường nhập liệu

        // Lưu thông tin đăng ký vào cơ sở dữ liệu (ví dụ này chỉ giả lập việc lưu thông tin)
        System.out.println("Registered new user: " + username + ", " + password + ", " + email);

        // Mở cửa sổ đăng nhập sau khi đăng ký thành công
        new LoginWindows();
        setVisible(false); // Ẩn cửa sổ đăng ký
    }

    public static void main(String[] args) {
        new RegisterWindow();
    }
}