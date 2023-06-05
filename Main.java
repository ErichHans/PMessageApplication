import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
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
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        add(sendButton, BorderLayout.EAST);
        
        // Khu vực hiển thị tin nhắn
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        add(messageArea, BorderLayout.CENTER);

        // Cửa sổ hiện ra và có thể tắt khi bấm thoát
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Since we are deploying
        socket.setTimeToLive(0);
        //this on localhost only (For a subnet set it as 1)
          
        socket.joinGroup(group);
        Thread t = new Thread(new
        ReadThread(socket,group,port));
        
        // Spawn a thread for reading messages
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
    public static void main(String[] args) throws IOException {
        Main groupchat=new Main();
        System.out.print("Enter Name:");
        Scanner sc = new Scanner(System.in);
        name = sc.nextLine();
        sc.close();
    }
    public void addtoma(String message){
        messageArea.append(message);
    }
}
class ReadThread implements Runnable
{
    private MulticastSocket socket;
    private InetAddress group;
    private int port;
    private static final int MAX_LEN = 1000;
    ReadThread(MulticastSocket socket,InetAddress group,int port)
    {
        this.socket = socket;
        this.group = group;
        this.port = port;
    }
      
    @Override
    public void run()
    {
        while(!Main.finished)
        {
                byte[] buffer = new byte[ReadThread.MAX_LEN];
                DatagramPacket datagram = new
                DatagramPacket(buffer,buffer.length,group,port);
                String message;
            try
            {
                socket.receive(datagram);
                message = new
                String(buffer,0,datagram.getLength(),"UTF-8");
                if(!message.startsWith(Main.name))
                    Main.messageArea.append(message);
            }
            catch(IOException e)
            {
                System.out.println("May chu da dong cua!");
            }
        }
    }
}
