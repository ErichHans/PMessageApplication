import java.net.*;
import java.io.*;
import java.util.*;
public class GroupChatConsole
{
    private static final String TERMINATE = "Exit";
    static String name;
    static volatile boolean finished = false;
    public static void main(String[] args)
    {
            try
            {
                InetAddress group = InetAddress.getByName("224.0.2.15");
                int port = Integer.parseInt("1234");
                BufferedReader namer = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter your name: ");
                name = namer.readLine();
                MulticastSocket socket = new MulticastSocket(port);
              
                // Since we are deploying
                socket.setTimeToLive(0);
                //this on localhost only (For a subnet set it as 1)
                  
                socket.joinGroup(group);
                Thread t = new Thread(new ReadThread(socket,group,port));
              
                // Spawn a thread for reading messages
                t.start();
                  
                // sent to the current group
                while(true)
                {
                    BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
                    String message;
                    message = sc.readLine();
                    if(message.equalsIgnoreCase(GroupChatConsole.TERMINATE))
                    {
                        finished = true;
                        socket.leaveGroup(group);
                        socket.close();
                        break;
                    }
                    message = name + ": " + message;
                    byte[] buffer = message.getBytes();
                    DatagramPacket datagram = new DatagramPacket(buffer,buffer.length,group,port);
                    socket.send(datagram);
                }
            }
            catch(SocketException se)
            {
                System.out.println("Da co loi say ra khi ketnoi den may chu...");
                se.printStackTrace();
            }
            catch(IOException ie)
            {
                System.out.println("Da co loi say ra...");
                ie.printStackTrace();
            }
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
            }
            catch(IOException e)
            {
                System.out.println("May chu da dong cua!");
          
            }
        }
    }
}