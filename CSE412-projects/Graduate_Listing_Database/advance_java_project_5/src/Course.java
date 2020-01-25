import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.*;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@Entity
public class Course {
    @Id
    private String course_code;
    private String course_name;
    private int credit;

    public Course() {

    }

    public Course(String course_code, String course_name, int credit) {
        this.course_code = course_code;
        this.course_name = course_name;
        this.credit = credit;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return course_code.equals(course.course_code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course_code);
    }

    @Override
    public String toString() {
        return this.course_code;
    }
}
