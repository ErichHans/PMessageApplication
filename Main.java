import java.io.*;
import java.net.*;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {
    private JTextField textField;
    private JTextArea textArea;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Main(String serverAddress) throws IOException {
        super("PMessage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
  

        textField = new JTextField();
        textField.addActionListener(this);
        add(textField, BorderLayout.SOUTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        socket = new Socket( serverAddress, 11087);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        String line = in.readLine();
                        if (line == null) {
                            break;
                        }
                        textArea.append(line + "\n");
                    }
                } catch (IOException e) {
                    textArea.append("Error: " + e + "\n");
                }
            }
        }).start();

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String text = textField.getText();
        out.println(text);
        textField.setText("");
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java SimpleChat <server>");
            return;
        }
        new Main(args[0]);
    }
}
