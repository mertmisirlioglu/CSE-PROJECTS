package mail;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.web.WebEngine;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.swing.*;
import java.io.IOException;

public class MessageRendererService extends Service {
    private Mail mailMessage;
    private WebEngine webEngine;
    private StringBuffer stringBuffer;

    public MessageRendererService(WebEngine webEngine) {
        this.webEngine = webEngine;
        this.stringBuffer = new StringBuffer();
        this.setOnSucceeded(event -> {
            displayMessage();
        });
    }

    public void setEmailMessage(Mail mailMessage) {
        this.mailMessage = mailMessage;
    }

    private void displayMessage() {
        webEngine.loadContent(stringBuffer.toString());
    }

    @Override
    protected Task createTask() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                try {
                    loadMessage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

    private void loadMessage() throws MessagingException, IOException {
        stringBuffer.setLength(0); //clears the SB
        Message message = mailMessage.getMessage();
        String contentType = message.getContentType();
        if (isSimpleType(contentType)) {
            stringBuffer.append(message.getContent().toString());
        } else if (isMultipartType(contentType)) {
            Multipart multipart = (Multipart) message.getContent();
            for (int i = multipart.getCount() - 1; i >= 0; i--) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                String bodyPartContentType = bodyPart.getContentType();
                if (isSimpleType(bodyPartContentType)) {
                    stringBuffer.append(bodyPart.getContent().toString());
                }
                MimeBodyPart bp = (MimeBodyPart) bodyPart; //get the body part
                if (Part.ATTACHMENT.equalsIgnoreCase(bp.getDisposition())) {
                    attachmentOperations(bp);
                }
            }
        }
    }

    public void attachmentOperations(MimeBodyPart bp) throws MessagingException, IOException {
        int input = JOptionPane.showConfirmDialog(null, "This mail contains attachments , do you want to download?");
        // 0=yes, 1=no, 2=cancel
        if (input == 0) {
            bp.saveFile(bp.getFileName());
            System.out.println("dosyayi da sakladim");
            JOptionPane.showConfirmDialog(null,
                    "Attachments are saved to this directory : " + System.getProperty("user.dir") + "/" + bp.getFileName(), "Download Succesfull!", JOptionPane.DEFAULT_OPTION);
        }

    }

    private boolean isSimpleType(String contentType) {
        return contentType.contains("TEXT/HTML") ||
                contentType.contains("mixed") ||
                contentType.contains("text");
    }

    private boolean isMultipartType(String contentType) {
        return contentType.contains("multipart");
    }
}
