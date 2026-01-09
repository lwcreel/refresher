import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class TCPServer {

  private static final int PORT = 8080;
  private static CopyOnWriteArrayList<ClientHandler> clients =
      new CopyOnWriteArrayList<>();

  public static void main(String[] args) throws Exception {

    try {

      ServerSocket serverSocket = new ServerSocket(PORT);
      System.out.println("Server is Waiting for Connections...");

      // Thread to Handle Server Admin Input
      new Thread(() -> {
        Scanner scanner = new Scanner(System.in);
        while (true) {
          String serverMessage = scanner.nextLine();
          broadcast("[Server]: " + serverMessage, null);
        }
      }).start();

      // Accept Incoming Connections
      while (true) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("New Client Connected: " + clientSocket);

        // Create New Client Handler for Connected Client
        ClientHandler clientHandler = new ClientHandler(clientSocket);
        clients.add(clientHandler);
        new Thread(clientHandler).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void broadcast(String message, ClientHandler sender) {
    for (ClientHandler client : clients) {
      if (client != sender) {
        client.sendMessage(message);
      }
    }
  }

  private static class ClientHandler implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String username;

    public ClientHandler(Socket socket) {
      this.clientSocket = socket;

      try {
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    @Override
    public void run() {
      try {
        out.println("Enter your username: ");
        username = in.readLine();
        System.out.println("User " + username + " connected.");
        out.println("Welcome to the chat, " + username + "!");
        out.println("Type Your Message");

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
          broadcast("[" + username + "]: " + inputLine, this);
        }

        clients.remove(this);
        System.out.println("User " + username + " disconnected.");

      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          in.close();
          out.close();
          clientSocket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    public void sendMessage(String message) { out.println(message); }
  }
}
