package mail;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.mail.*;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    private ObservableList<Mail> mailMessages;
    private MessageRendererService messageRendererService;
    private Session session;
    private Store mailStore;
    private Folder inbox;
    private String mail;
    private String password;


    @FXML
    private WebView webView;


    @FXML
    private TableView<Mail> mailTableView;

    @FXML
    private TableColumn<Mail, String> senderColumn;

    @FXML
    private TableColumn<Mail, String> subjectColumn;

    @FXML
    private TableColumn<Mail, Date> dateColumn;

    @FXML
    private Button refreshButton;

    @FXML
    private Button replyButton;

    @FXML
    private Button newButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextArea displayText;

    public void setMailandPassword(String mail, String password) {
        this.password = password;
        this.mail = mail;
    }

    public Stage setNewMail(ActionEvent event) throws IOException {
        final Stage dialog = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sendMail.fxml"));
        Parent sendmailParent = loader.load();
        sendMailController newMailController = loader.getController();
        newMailController.setEmailaddress(this.mail);
        newMailController.setPassword(this.password);
        newMailController.setSession(this.session);
        if (event.getSource() == replyButton)
            newMailController.setMsg(mailTableView.getSelectionModel().getSelectedItem());
        Scene sendmailScene = new Scene(sendmailParent);
        dialog.setScene(sendmailScene);
        return dialog;
    }

    @FXML
    void newMail(ActionEvent event) throws IOException {
        Stage dialog = setNewMail(event);
        dialog.show();
    }

    @FXML
    void replymail(ActionEvent event) throws IOException {
        Stage dialog = setNewMail(event);
        dialog.show();
    }

    @FXML
    void refreshAction(ActionEvent event) {
        System.out.println("refresh");
        getMails();
    }

    public void initData(Session session, Store mailStore) {
        this.session = session;
        this.mailStore = mailStore;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            setmailTableViewRows();
            messageRendererService = new MessageRendererService(webView.getEngine());
        });


    }

    public void getMails() {
        mailMessages = FXCollections.observableArrayList();
        try {
            Folder inbox = mailStore.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            for (int i = 0; i < inbox.getMessageCount(); i++) {
                Message a = inbox.getMessage(inbox.getMessageCount() - i);
                mailMessages.add(fetchMessage(a));
            }
            mailTableView.setItems(mailMessages);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Mail fetchMessage(Message message) throws MessagingException {
        Mail mailMessage = new Mail(message.getSubject(), message.getFrom()[0].toString(), message.getSentDate(), message);
        return mailMessage;
    }

    private void setmailTableViewRows() {
        senderColumn.setCellValueFactory((new PropertyValueFactory<Mail, String>("sender")));
        subjectColumn.setCellValueFactory((new PropertyValueFactory<Mail, String>("subject")));
        dateColumn.setCellValueFactory((new PropertyValueFactory<Mail, Date>("when")));
    }

    @FXML
    void showMail(MouseEvent event) {
        Mail emailMessage = mailTableView.getSelectionModel().getSelectedItem();
        if (emailMessage != null) {
            messageRendererService.setEmailMessage(emailMessage);
            messageRendererService.restart();
        }
    }


}




