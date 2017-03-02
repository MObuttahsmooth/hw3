import java.net.*;
import java.io.*;
import java.util.*; 
import java.util.concurrent.*;

public class UDPServerThread extends Thread{
	public DatagramSocket dataSocket = null;
	public DatagramPacket dataPacket, returnPacket = null;
	public BufferedReader in = null;

	public UDPServerThread(DatagramSocket dSocket, DatagramPacket dataPacket){
		this.dataSocket = dSocket;
		this.dataPacket = dataPacket;
	}

	//Handle the UDP command
	public void run(){
		//System.out.println("Thread is running");
		String command = new String(dataPacket.getData());
		//System.out.println("String received was:" + command);
		//System.out.println("Received command: " + command);
		String[] commandArgs = command.split(" ");
		// System.out.println(commandArgs[0]);
		// System.out.println(commandArgs[0].equals("purchase"));
        if (commandArgs[0].trim().equals("purchase")) {
          //System.out.println("received purchase");
          //System.out.println(command);
          commandArgs[3] = commandArgs[3].trim();
          Integer number = Integer.parseInt(commandArgs[3]);
          String returnString = Server.purchase(commandArgs);
          //System.out.println(returnString);
          sendResponse(returnString);

        }
        else if (commandArgs[0].trim().equals("cancel")) {
          //System.out.println("received cancel");
          //System.out.println(command);
          commandArgs[1] = commandArgs[1].trim();
          String returnString = Server.cancel(commandArgs);
          //System.out.println(returnString);
          sendResponse(returnString);
        } 

        else if (commandArgs[0].trim().equals("search")) {
          //System.out.println("received search");
          //System.out.println(command);
          commandArgs[1] = commandArgs[1].trim();
          String returnString = Server.search(commandArgs);
          //System.out.println(returnString);
          sendResponse(returnString);
        } 
        else if (commandArgs[0].trim().equals("list")) {
          //System.out.println("received list");
          //System.out.println(command);
          String returnString = Server.list(commandArgs);
          //System.out.println(returnString);
          sendResponse(returnString);
        } 
        else {
			//System.out.println(commandArgs.length);
			System.out.println("ERROR: No such command");
        }

		// try{
		// returnPacket = new DatagramPacket(
		// 			dataPacket.getData(),
		// 			dataPacket.getLength(),
		// 			dataPacket.getAddress(),
		// 			dataPacket.getPort());
		// dataSocket.send(returnPacket);
		// System.out.println("return packet sent");
		// } catch (Exception e){
		// 	System.err.println(e);
		// }
		
	}

	public void sendResponse(String returnString){
		try{
			byte[] buf = returnString.getBytes();
		returnPacket = new DatagramPacket(
					buf,
					buf.length,
					dataPacket.getAddress(),
					dataPacket.getPort());
		dataSocket.send(returnPacket);
		//System.out.println("return packet sent");
		} catch (Exception e){
			System.err.println(e);
		}
	}
}