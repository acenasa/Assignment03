import java.io.*;
import java.net.*;

public class MyClient {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MyClient <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);

        try {
            System.out.println("Connecting to server...");
            Socket socket = new Socket("localhost", port);
            System.out.println("Connected to server");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            BufferedWriter fileWriter = new BufferedWriter(
                    new FileWriter("client_file.txt"));

            String line;
            while ((line = in.readLine()) != null) {
                fileWriter.write(line);
                fileWriter.newLine();
            }

            fileWriter.close();
            socket.close();

            System.out.println("File received and saved.");
            System.out.println("Connection closed.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
