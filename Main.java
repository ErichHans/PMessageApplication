import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.*;
import java.io.*;
import java.util.*;
public class Main extends JFrame implements ActionListener {
    private JTextField messageField;
    private JButton sendButton;
    public static JTextArea messageArea;
    private static final String TERMINATE = "Exit";
    static String name;
    static volatile boolean finished = false;
    InetAddress group = InetAddress.getByName("224.0.1.255");
    int port = Integer.parseInt("1234");
    MulticastSocket socket = new MulticastSocket(port);
    
    public Main() throws IOException {
      
        // tạo ra giao diện cửa sổ
        setTitle("PMessage");
        setSize(1280, 720);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // tạo ra nơi để nhập tin nhắn
        messageField = new JTextField();
        add(messageField, BorderLayout.SOUTH);
        
        // Tạo ra nút Send
        sendButton = new JButton("gửi tin nhắn");
        sendButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            sendMessage();
          }
        });
        setPreferredSize(new Dimension(80, 30));
        add(sendButton, BorderLayout.EAST);

        // Khu vực hiển thị tin nhắn
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        add(messageArea, BorderLayout.CENTER);

        // Cửa sổ hiện ra và có thể tắt khi bấm thoát
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        socket.setTimeToLive(0);
       
          
        socket.joinGroup(group);
        Thread t = new Thread(new
        ReadThread(socket,group,port));
        
        
        t.start();
        
    }
    public void sendMessage(String message) throws IOException{
    message = name + ": " + message+"\n";
    messageArea.append(message);
    byte[] buffer = message.getBytes();
    DatagramPacket datagram = new DatagramPacket(buffer,buffer.length,group,port);
    socket.send(datagram);
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == sendButton) {
            String mess = messageField.getText();
            messageField.setText("");
            try {
                sendMessage(mess);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
      }
    });
        try {
        System.out.print("Enter Name:");
        Scanner sc = new Scanner(System.in);
        name = sc.nextLine();
        sc.close();
          input = new BufferedReader(new InputStreamReader(server.getInputStream()));
          output = new PrintWriter(server.getOutputStream(), true);
          output.println(name);
          read = new Read();
          read.start();
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(jfr, ex.getMessage());
        }
  }
  public class TextListener implements DocumentListener{
    JTextField jtf1;
    JTextField jtf2;
    JTextField jtf3;
    JButton jcbtn;

    public TextListener(JTextField jtf1, JTextField jtf2, JTextField jtf3, JButton jcbtn){
      this.jtf1 = jtf1;
      this.jtf2 = jtf2;
      this.jtf3 = jtf3;
      this.jcbtn = jcbtn;
    }

    public void changedUpdate(DocumentEvent e) {}

    public void removeUpdate(DocumentEvent e) {
      if(jtf1.getText().trim().equals("") ||
          jtf2.getText().trim().equals("") ||
          jtf3.getText().trim().equals("")
          ){
        jcbtn.setEnabled(false);
      }else{
        jcbtn.setEnabled(true);
      }
    }
    public void insertUpdate(DocumentEvent e) {
      if(jtf1.getText().trim().equals("") ||
          jtf2.getText().trim().equals("") ||
          jtf3.getText().trim().equals("")
          ){
        jcbtn.setEnabled(false);
      }else{
        jcbtn.setEnabled(true);
      }
    }

  }
  public void sendMessage() {
    if(name!=null){
    try {
      String message = messageField.getText();
      if (message.equals("")) {
        return;
      }
      output.println(message);
      messageField.requestFocus();
      messageField.setText(null);
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());
      System.exit(0);
    }
  }else{
    messageArea.append("Vui Lòng Nhập Tên Của Bạn...");
    messageField.requestFocus();
    messageField.setText(null);
  }
  }

  public static void main(String[] args) throws Exception {
    Main client = new Main();
  }
  class Read extends Thread {
    public void run() {
      String message;
      while(!Thread.currentThread().isInterrupted()){
        try {
          message = input.readLine();
          if(message != null){
            messageArea.append(message+"\n");
        }}
        catch (IOException ex) {
          System.err.println("Failed to parse incoming message");
        }
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
  }}