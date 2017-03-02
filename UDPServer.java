import java.net.*;
import java.io.*;
import java.util.*; 
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;


public class UDPServer implements Runnable{
	int port;
	public UDPServer(int p){
		this.port = p;
	}
	
	public void run(){
		DatagramPacket dataPacket, returnPacket;
		int len = 1024;
		//System.out.println("We're running a UDPServer now'");
		try {
			DatagramSocket dataSocket = new DatagramSocket(port);
			
			while (true) {
				byte[] buf = new byte[len];
				//receive data packet
				// System.out.println("Waiting to receive packet");
				dataPacket = new DatagramPacket(buf, buf.length);
				dataSocket.receive(dataPacket);
				// System.out.println("Packet received");

				//spawn thread to handle that
				new Thread (new UDPServerThread(dataSocket, dataPacket)).start();
				// System.out.println("Thread created");
			}
		} catch (SocketException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}


}
