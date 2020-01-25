package mail;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.mail.Message;
import java.util.Date;

public class Mail {
    private SimpleStringProperty subject;
    private SimpleStringProperty sender;
    private SimpleObjectProperty<Date> when;
    private Message message;

    public Mail(String subject, String sender, Date when, Message message) {
        this.subject = new SimpleStringProperty(subject);
        this.sender = new SimpleStringProperty(sender);
        this.when = new SimpleObjectProperty<Date>(when);
        this.message = message;

    }

    public String getSubject() {
        return subject.get();
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public String getSender() {
        return sender.get();
    }

    public SimpleStringProperty senderProperty() {
        return sender;
    }

    public Date getWhen() {
        return when.get();
    }

    public SimpleObjectProperty<Date> whenProperty() {
        return when;
    }

    public Message getMessage() {
        return message;
    }


}
