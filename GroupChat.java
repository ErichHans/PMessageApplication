import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class GroupChat extends JFrame implements ActionListener {
    private JTextField messageField;
    private JButton sendButton;
    private JTextArea messageArea;
    private static final String TERMINATE = "Exit";
    static String name;
    static volatile boolean finished = false;
    InetAddress group = InetAddress.getByName("224.0.0.6");
    int port = Integer.parseInt("1234");
    MulticastSocket socket = new MulticastSocket(port);

    public GroupChat() throws IOException {
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
        messageArea.setEditable(false);
        add(messageArea, BorderLayout.CENTER);

        // Cửa sổ hiện ra và có thể tắt khi bấm thoát
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        name = sc.nextLine();
        
        // Since we are deploying
        socket.setTimeToLive(0);
        //this on localhost only (For a subnet set it as 1)
          
        socket.joinGroup(group);
        Thread t = new Thread(new
        ReadThread(socket,group,port));
        
        // Spawn a thread for reading messages
        t.start();
        String mess = messageField.getText();
        String message = mess;
        message = name + ": " + mess;
        byte[] buffer = message.getBytes();
        DatagramPacket datagram = new DatagramPacket(buffer,buffer.length,group,port);
        socket.send(datagram);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendButton) {
            String mess = messageField.getText();
            messageArea.append("Tin nhắn của bạn: " + mess + "\n");
            messageField.setText("");
        }
        }
    public static void main(String[] args) throws IOException {
        new GroupChat();
    }
    /*public static void add(String[] message){
        JTextArea messagArea = GroupChat
        messageArea.append(message);
    }*/
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
        while(!GroupChatConsole.finished)
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
                if(!message.startsWith(GroupChatConsole.name))
                    System.out.println(message);
                    messageArea.append(message);
            }
            catch(IOException e)
            {
                System.out.println("May chu da dong cua!");
            }
        }
    }
}