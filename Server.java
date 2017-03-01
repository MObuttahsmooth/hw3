import java.net.*;
import java.io.*;
import java.util.*; 
import java.util.concurrent.*;

public class Server {

  public static Map<String, Integer> inventory = new ConcurrentHashMap<String, Integer>();
  public static Map<String, String> userOrders = new ConcurrentHashMap<String, String>();
  public static void main (String[] args) {
    int tcpPort;
    int udpPort;
    if (args.length != 3) {
      System.out.println("ERROR: Provide 3 arguments");
      System.out.println("\t(1) <tcpPort>: the port number for TCP connection");
      System.out.println("\t(2) <udpPort>: the port number for UDP connection");
      System.out.println("\t(3) <file>: the file of inventory");

      System.exit(-1);
    }
    tcpPort = Integer.parseInt(args[0]);
    udpPort = Integer.parseInt(args[1]);
    String fileName = args[2];

    // parse the inventory file
    File file = new File(fileName);
    Scanner fileScanner = null;
    try{
      fileScanner = new Scanner(file);
    }catch(Exception e){
      System.out.println("ERROR: File Not Found");
      System.exit(-1);
    }

    while(fileScanner.hasNext()){
      String input = fileScanner.nextLine();
      String[] splitInput = input.split(" ");
      inventory.put(splitInput[0], Integer.parseInt(splitInput[1]));
    }

    // TODO: handle request from clients
    try (ServerSocket serverSocket = new ServerSocket(4444)){
      while(true){
        Thread thread = new Thread(new ServerThread(serverSocket.accept()));
        thread.start();
      }
    } catch(IOException e){
        e.printStackTrace();
    }

    
  }

}
