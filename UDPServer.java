import java.net.*;
import java.io.*;
import java.util.*; 
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;


public class UDPServer{
	public static Map<String, Integer> inventory = new ConcurrentHashMap<String, Integer>();
	public static Map<Integer, Order> userOrders = new ConcurrentHashMap<Integer, Order>();
	public static AtomicInteger currentOrder = new AtomicInteger(1);
	public static void main(String[] args) throws java.io.IOException {
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
		//TODO: only creates one thread currently
		DatagramPacket dataPacket, returnPacket;
		int port = udpPort;
		int len = 1024;
		System.out.println("We at least are in main");
		try {
			DatagramSocket dataSocket = new DatagramSocket(udpPort);
			byte[] buf = new byte[len];
			System.out.println("Running server");
			while (true) {
				//receive data packet
				System.out.println("Waiting to receive packet");
				dataPacket = new DatagramPacket(buf, buf.length);
				dataSocket.receive(dataPacket);
				System.out.println("Packet received");

				//spawn thread to handle that
				new Thread (new UDPServerThread(dataSocket, dataPacket)).start();
				System.out.println("Thread created");
			}
		} catch (SocketException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	    
	}


}
