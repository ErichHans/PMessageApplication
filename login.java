import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.io.*;

public class login {
   public static void main(String [] args) {
        JFrame jf = new JFrame();
        JPasswordField JP = new JPasswordField();
        jf.setTitle("Login Window");
        jf.setSize(250, 350);
        jf.setVisible(true);
        jf.setLayout(null);
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
        jf.setResizable(false);
        JP.setBounds((250/2-50),(350/2-30),100, 30);
        jf.add(JP,BorderLayout.CENTER);
   }
}
