import java.util.Scanner;

public class Client {
  public static void main (String[] args) {
    String hostAddress;
    int tcpPort;
    int udpPort;
    String communicationMode = "TCP";

    if (args.length != 3) {
      System.out.println("ERROR: Provide 3 arguments");
      System.out.println("\t(1) <hostAddress>: the address of the server");
      System.out.println("\t(2) <tcpPort>: the port number for TCP connection");
      System.out.println("\t(3) <udpPort>: the port number for UDP connection");
      System.exit(-1);
    }

    hostAddress = args[0];
    tcpPort = Integer.parseInt(args[1]);
    udpPort = Integer.parseInt(args[2]);

    try (
      //Default: Connect to TCP Socket
      Socket socket = new Socket(hostAddress, tcpPort);
      PrintWriter out =
        new PrintWriter(socket.getOutputStream(), true);
      BufferedReader in =
          new BufferedReader(
              new InputStreamReader(socket.getInputStream()));
      //TODO:Go ahead and connec to the UDP Socket as well

    ) {

      Scanner sc = new Scanner(System.in);
      while(sc.hasNextLine()) {
        String cmd = sc.nextLine();
        String[] tokens = cmd.split(" ");

        // setmode T|U – sets the protocol for communication with the server. The protocol is specified
        // by the letter U ot T where U stands for UDP and T stands for TCP. The default mode of
        // communication is TCP.
        if (tokens[0].equals("setmode")) {
          // TODO: set the mode of communication for sending commands to the server 
          // and display the name of the protocol that will be used in future
          if(tokens[1].equals("T")){
            communicationMode = "TCP";
          }
          else{
            communicationMode = "UDP";
          }
        }
        else if (tokens[0].equals("purchase")) {
          //TCP IMPLEMENTATION
          out.println("purchase " + tokens[1] + " " + tokens[2] + " " + tokens[3]);
          //UDP IMPLEMENTATION

          // TODO: send appropriate command to the server and display the
          // appropriate responses form the server
        } else if (tokens[0].equals("cancel")) {
          //TCP IMPLEMENATION
          out.println("cancel " + tokens[1]);
          //UDP IMPLEMENTATION

          // TODO: send appropriate command to the server and display the
          // appropriate responses form the server
        } else if (tokens[0].equals("search")) {
          //TCP IMPLEMENTATION
          out.println("search " + tokens[1]);
          //UDP IMPLMENTATION

          // TODO: send appropriate command to the server and display the
          // appropriate responses form the server
        } else if (tokens[0].equals("list")) {
          //TCP IMPLEMENTATION
          out.println("list");

          //UDP IMPLEMENATION

          // TODO: send appropriate command to the server and display the
          // appropriate responses form the server
        } else {
          System.out.println("ERROR: No such command");
        }
    }

    }
    
    
    


    
  }
}
