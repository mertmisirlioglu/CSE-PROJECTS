package sample.models;

import java.io.Serializable;

public class Room implements Serializable {
    private int roomID;
    private String roomName;
    private int onlineCount;
    private int msgCount;
    private static int count=325;

    public Room(String roomName){
        this.roomName = roomName;
        this.roomID = count++;
    }

    public int getRoomID() {
        return roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public int getMsgCount() {
        return msgCount;
    }

    @Override
    public String toString() {
        return this.roomName;
    }
}
