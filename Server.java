import java.net.*;
import java.io.*;
import java.util.*; 
import java.util.concurrent.*;

public class Server {

  public static Map<String, Integer> inventory = new ConcurrentHashMap<String, Integer>();
  public static Map<Integer, Order> userOrders = new ConcurrentHashMap<Integer, Order>();
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

    // TODO: handle TCP request from clients
    try (ServerSocket serverSocket = new ServerSocket(4444)){
      while(true){
        Thread thread = new Thread(new ServerThread(serverSocket.accept()));
        thread.start();
      }
    } catch(IOException e){
        e.printStackTrace();
    }
  }

  // purchase <user-name> <product-name> <quantity> – inputs the name of a customer, the
  // name of the product, and the number of quantity that the user wants to purchase. The client
  // sends this command to the server using the current mode of the appropriate protocol. If the store
  // does not have enough items, the server responds with message: ‘Not Available - Not enough
  // items’. If the store does not have the product, the server responds with message: ‘Not Available
  // - We do not sell this product’. Otherwise, an order is placed and the server replies a message:
  // 1
  // ‘You order has been placed, <order-id> <user-name> <product-name> <quantity>’. Note
  // that, the order-id is unique and automatically generated by the server. You can assume that
  // the order-id starts with 1. The server should also update the inventory.
  public static synchronized String purchase(String[] st){

    return null;
  }
  // cancel <order-id> – cancels the order with the <order-id>. If there is no existing order
  // with the id, the response is: ‘<order-id> not found, no such order’. Otherwise, the server
  // replies: ‘Order <order-id> is canceled’ and updates the inventory    
  public static synchronized String cancel(String[] st){

    return null;
  }
  // search <user-name> – returns all orders for the user. If no order is found for the user, the
  // system responds with a message: ‘No order found for <user-name>’. Otherwise, list all orders
  // of the users as <order-id>, <product-name>, <quantity>. Note that, you should print one
  // line per order.
  public static synchronized String search(String[] st){
    
    return null;
  }
  // list – lists all available products with quantities of the store. For each product, you should
  // show ‘<product-name> <quantity>’. Note that, even if the product is sold out, you should
  // also print the product with quantity 0. In addition, you should print one line per product.
  public static synchronized void list(){

  }
}
