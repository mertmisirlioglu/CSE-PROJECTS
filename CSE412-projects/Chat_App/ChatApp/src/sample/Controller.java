package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;


import sample.models.ObjectIO;
import sample.models.Room;


import java.io.*;
import java.net.*;
import java.util.*;


public class Controller implements Initializable {
    private String username;
    private Socket socket;
    private InetAddress serverAddress;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ObservableList chatRoomListed;
    private ObservableList chatPaneListed;
    @FXML
    private ListView<Room> chatRoomListView;

    @FXML
    private ListView<String> onlineUsersListView;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label usernameField;

    @FXML
    private ListView<String> chatPane;

    @FXML
    private TextArea messageBox;

    @FXML
    private Button attachmentButton;

    @FXML
    private Button buttonSend;


    @FXML
    void sendButtonAction(ActionEvent event) {
        sendMessage();
    }

    public void sendMessage() {
        String msg = messageBox.getText();
        int roomID = chatRoomListView.getSelectionModel().getSelectedItems().get(0).getRoomID();
        messageBox.clear();

        try {
            // write on the output stream
            dos.writeUTF(roomID + "#" + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendToChat(String msg) {
        chatPane.setEditable(true);
        chatPane.getItems().add(msg);
        chatPane.setEditable(false);
    }

    public void readMessages() {

        Thread readMessage = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        // read the message sent to this client
                        if (dis.available() != 0) {
                            String msg = dis.readUTF();

                            msg = prepareMessage(msg);
                            final String msg2 = msg;
                            if (msg != null) {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        chatPaneListed.add(msg2);
                                        chatPane.setItems(chatPaneListed);
                                        System.out.println(msg2);
                                    }
                                });

                            }


                        }
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }
        });
        readMessage.start();
    }


    public String prepareMessage(String msg) {
        int selectedroomID = chatRoomListView.getSelectionModel().getSelectedItems().get(0).getRoomID();
        StringTokenizer st = new StringTokenizer(msg, "#");
        String MsgToSend = st.nextToken();
        int roomID = Integer.parseInt(st.nextToken());
        if (roomID == selectedroomID) {
            return MsgToSend;
        } else {
            return null;
        }

    }

    @FXML
    void filterByRoom(MouseEvent event) {
        chatPaneListed.clear();
        chatPane.setItems(chatPaneListed);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        socketConnect();
        fillRooms();

        chatPaneListed = FXCollections.observableArrayList();
        chatPane.setItems(chatPaneListed);

        messageBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendMessage();
            }
        });


    }

    private void fillRooms() {
        ObjectIO<ArrayList<Room>> objectIO = new ObjectIO();
        ArrayList<Room> rooms = objectIO.ReadObjectFromFile("roomList.obj");
        chatRoomListed = FXCollections.observableArrayList(rooms);
        chatRoomListView.setItems(chatRoomListed);
        chatRoomListView.getSelectionModel().selectFirst();
    }

    private void socketConnect() {
        try {
            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection
            Socket s = new Socket(ip, 1234);

            // obtaining input and out streams
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            readMessages();


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
