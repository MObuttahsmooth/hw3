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
      String inputLine, outputLine;
      while ((inputLine = in.readLine()) != null) {
        String[] splitIn = inputLine.split(" ");

        if (splitIn[0].equals("purchase")) {
          Server.purchase(splitIn);
        }

        else if (splitIn[0].equals("cancel")) {
          Server.cancel(splitIn);
        } 

        else if (splitIn[0].equals("search")) {
          Server.search(splitIn);
        } 

        else if (splitIn[0].equals("list")) {
          Server.list();
        } 
        else {
          System.out.println("ERROR: No such command");
        }
      }
    } catch (IOException e) {
          e.printStackTrace();
    }
  }
}