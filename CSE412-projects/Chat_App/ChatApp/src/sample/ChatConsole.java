package sample;

import java.io.*;

public class ChatConsole {
    private static DataInputStream fromServer;
    private static DataOutputStream toServer;

    public static void main(String[] args) {
        System.out.println(AlternateServer.ar.size());
    }
}
