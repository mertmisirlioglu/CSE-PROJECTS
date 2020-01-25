package mail;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

public class sendMailController implements Initializable {
    String emailaddress;
    String password;
    Session session;
    ArrayList<BodyPart> attachList = new ArrayList<>();
    Mail msg;

    @FXML
    private Button fileChooserButton;

    @FXML
    void fileChooseOperation(ActionEvent event) throws MessagingException {
        JFileChooser chooser = new JFileChooser();

        int returnVal = chooser.showOpenDialog(null);
        BodyPart partForAtt = new MimeBodyPart();
        String filename = chooser.getSelectedFile().getPath();
        DataSource source = new FileDataSource(filename);
        partForAtt.setDataHandler(new DataHandler(source));
        partForAtt.setFileName(filename);
        attachList.add(partForAtt);


    }


    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void setMsg(Mail msg) {
        this.msg = msg;
    }

    @FXML
    private TextField subjectField;

    @FXML
    private TextField toField;

    @FXML
    private TextArea sendMailTextArea;

    @FXML
    private Button sendMailButton;

    @FXML
    void sendMail(ActionEvent event) {
        Properties props = session.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.from", this.emailaddress);
        props.put("mail.smtp.auth", "true");

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom();
            msg.setRecipients(Message.RecipientType.TO, toField.getText());
            msg.setSubject(subjectField.getText());
            msg.setSentDate(new Date());
            Multipart mp = new MimeMultipart();
            BodyPart bp1 = new MimeBodyPart();
            bp1.setText(sendMailTextArea.getText());
            mp.addBodyPart(bp1);
            for (int i = 0; i < attachList.size(); i++) {
                mp.addBodyPart(attachList.get(i));
            }

            msg.setContent(mp);
            Transport.send(msg);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Send succesfull");
            alert.setHeaderText("eMail sending is succesfull");
            alert.showAndWait();
        } catch (MessagingException mex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Send unsuccesfull");
            alert.setHeaderText("eMail sending is unsuccesfull");
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            if (msg != null) {
                String sendermail = msg.getSender();
                String[] a1 = sendermail.split("<");
                String[] a2 = a1[1].split(">");
                toField.setText(a2[0]);
                subjectField.setText(msg.getSubject());

            }
        });
    }
}
