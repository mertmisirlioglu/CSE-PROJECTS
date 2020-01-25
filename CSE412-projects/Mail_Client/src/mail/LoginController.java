package mail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    private Properties props;
    private Session session;
    private Store mailStore;

    @FXML
    private Button loginButton;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void loginAction(ActionEvent event) {
        props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        Authenticator a = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailField.getText(), passwordField.getText());
            }
        };
        session = Session.getDefaultInstance(props, a);
        try {
            mailStore = session.getStore("imaps");
            mailStore.connect("imap.gmail.com", emailField.getText(), passwordField.getText());


            switchToMain(event);
        } catch (Exception CanNotConnect) {
            JOptionPane.showMessageDialog(null, "Informations not valid or this email is not gmail.");
        }

    }

    public void switchToMain(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent mainParent = loader.load();
        Controller mainController = loader.getController();
        mainController.initData(session, mailStore);
        mainController.setMailandPassword(this.emailField.getText(), this.passwordField.getText());
        Scene mainScene = new Scene(mainParent);
        final WorkIndicatorDialog[] wd = {null};

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        wd[0] = new WorkIndicatorDialog(window, "Fetching mails...");
        window.setScene(mainScene);
        wd[0].addTaskEndNotification(result -> {
            wd[0] = null;
        });

        wd[0].exec("123", inputParam -> {
            mainController.getMails();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 1;
        });
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emailField.setText("cse482atisik@gmail.com");
        passwordField.setText("EmineEkin");
    }
}
