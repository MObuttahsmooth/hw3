import java.net.*;
import java.io.*;
import java.util.*; 
import java.util.concurrent.*;

public class TestUDP{
	public static void main(String args[]){
		System.out.println("Begin test");
		String hostname;
        int port = 4445;
        int len = 1024;
        byte[] rbuffer = new byte[len];
        DatagramPacket sPacket, rPacket = null;
        try {
            InetAddress ia = InetAddress.getByName("localhost");
            DatagramSocket datasocket = new DatagramSocket();
            Scanner sc = new Scanner(System.in);
            System.out.println("Entering while loop to send packet");
            while (true) {
            	String echoline = sc.nextLine();
            	if (echoline.equals("done")) break;
            	byte[] buffer = new byte[echoline.length()];
            	buffer = echoline.getBytes();
            	sPacket = new DatagramPacket(buffer, buffer.length, ia, port);
            	datasocket.send(sPacket);            	
            	rPacket = new DatagramPacket(rbuffer, rbuffer.length);
            	datasocket.receive(rPacket);
            	String retstring = new String(rPacket.getData(), 0,
            			rPacket.getLength());
            	System.out.println("Received from Server:" + retstring);
            	//System.out.println("Hello we are trying");
            }
            
        } catch (Exception e) {
        	System.err.println(e);
        }
    }
}
