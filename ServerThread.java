import java.net.*;
import java.io.*;
import java.util.*; 
import java.util.concurrent.*;

public class ServerThread implements Runnable {
  Socket socket = null;

  public ServerThread(Socket socket){
    this.socket = socket;
  }

  public void run(){
    try(
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
          BufferedReader in = new BufferedReader(
              new InputStreamReader(
                  socket.getInputStream()));
    ) {
      //do client/server things here

    } catch (IOException e) {
          e.printStackTrace();
    }
  }
}