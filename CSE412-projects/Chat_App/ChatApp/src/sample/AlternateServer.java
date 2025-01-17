package sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.nio.charset.StandardCharsets;
import java.util.Vector;

public class AlternateServer {

    // Vector to store active clients
    static Vector<ClientHandler> ar = new Vector<>();

    public static Vector<ClientHandler> getAr() {
        return ar;
    }

    // counter for clients
    static int i = 0;


    public static void main(String[] args) throws IOException {
        // server is listening on port 1234
        ServerSocket ss = new ServerSocket(1234);

        Socket s;

        // running infinite loop for getting
        // client request
        while (true) {
            // Accept the incoming request
            s = ss.accept();

            System.out.println("New client request received : " + s);

            // obtain input and output streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            System.out.println("Creating a new handler for this client...");

            // Create a new handler object for handling this request.
            ClientHandler mtch = new ClientHandler(s, "client " + i, dis, dos);

            // Create a new Thread with this object.
            Thread t = new Thread(mtch);

            System.out.println("Adding this client to active client list");

            // add this client to active clients list
            ar.add(mtch);
            WriteUsersToFile();
            // start the thread.
            t.start();

            // increment i for new client.
            // i is used for naming only, and can be replaced
            // by any naming scheme
            i++;

        }
    }

    public static void WriteUsersToFile() {

        try {

            PrintWriter writer = new PrintWriter("user-list.txt", StandardCharsets.UTF_8);
            for (ClientHandler mc : AlternateServer.ar) {
                writer.println(mc.getName());
            }
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

