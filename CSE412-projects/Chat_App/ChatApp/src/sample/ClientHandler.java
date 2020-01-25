package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ClientHandler implements Runnable {
    Scanner scn = new Scanner(System.in);
    private String name;
    final DataInputStream dis;
    final DataOutputStream dos;
    Socket s;
    boolean isloggedin;

    // constructor
    public ClientHandler(Socket s, String name,
                         DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.s = s;
        this.isloggedin = true;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {

        String received;
        while (true) {
            try {
                // receive the string
                if (dis.available() != 0) {
                    received = dis.readUTF();

                    System.out.println(received);

                    if (received.equals("logout")) {
                        this.isloggedin = false;
                        this.s.close();
                        break;
                    }

                    // break the string into message and recipient part
                    StringTokenizer st = new StringTokenizer(received, "#");
                    String roomID = st.nextToken();
                    String MsgToSend = st.nextToken();

                    // search for the recipient in the connected devices list.
                    // ar is the vector storing client of active users
                    for (ClientHandler mc : AlternateServer.ar) {
                        // if the recipient is found, write on its
                        // output stream
                        String msg = this.name + " : " + MsgToSend + "#" + roomID;

                        try {
                            mc.dos.writeUTF(msg);
                        } catch (SocketException e) {
                            System.out.println("socketkapalı");
                        }

                    }
                }
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        try {
            // closing resources
            this.dis.close();
            this.dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
