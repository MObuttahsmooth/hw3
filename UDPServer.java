import java.net.*;
import java.io.*;
import java.util.*; 
import java.util.concurrent.*;


public class UDPServer{
	public static void main(String[] args) throws java.io.IOException {
		//TODO: only creates one thread currently
	    new UDPServerThread().start();
	}
}
