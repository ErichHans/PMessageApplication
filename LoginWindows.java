import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindows extends JFrame implements ActionListener {
    private JTextField usernameField; // Trường nhập liệu tên đăng nhập
    private JPasswordField passwordField; // Trường nhập liệu mật khẩu

    public LoginWindows() {
        super("Login"); // Tiêu đề cửa sổ

        // Khởi tạo các thành phần giao diện người dùng
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        JButton loginButton = new JButton("Login");

        // Đăng ký lắng nghe sự kiện cho nút Login
        loginButton.addActionListener(this);

        // Tạo một bảng để chứa các thành phần giao diện người dùng
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);

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

        // Kiểm tra thông tin đăng nhập (ví dụ này chỉ kiểm tra đơn giản)
        if (username.equals("admin") && password.equals("abc")) {
            // Nếu thông tin đăng nhập hợp lệ, mở cửa sổ chính của ứng dụng
            new Main();
            setVisible(false); // Ẩn cửa sổ đăng nhập
        } else {
            // Nếu thông tin đăng nhập không hợp lệ, hiển thị thông báo lỗi
            System.err.println("Invalid login");
        }
    }

    public static void main(String[] args) {
        new LoginWindows();
    }
}