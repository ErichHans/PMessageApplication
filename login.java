import javax.swing.*;

import java.awt.Frame;
import java.io.*;

public class login {
   public static void main(String [] args) {
        JFrame jf = new JFrame();
        JPasswordField JP = new JPasswordField();
        jf.setTitle("Login Window");
        jf.setSize(250, 350);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
        JP.add(jf, JP, 0);
   }
}
