import java.io.*;
import java.net.*;

public class MyServer {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MyServer <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            System.out.println("Waiting for client...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            BufferedReader fileReader = new BufferedReader(
                    new FileReader("server_file.txt"));

            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(clientSocket.getOutputStream())));

            String line;
            while ((line = fileReader.readLine()) != null) {
                out.println(line);
            }

            out.flush();
            fileReader.close();
            clientSocket.close();

            System.out.println("File sent. Connection closed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
