import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompositeKey implements Serializable {
    private String student_id;
    private String course_code;
    private int year_taken;
    private String term_taken;

    public CompositeKey() {
    }


    public CompositeKey(String student_id, String course_code, int year_taken, String term_taken) {
        this.student_id = student_id;
        this.course_code = course_code;
        this.year_taken = year_taken;
        this.term_taken = term_taken;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public int getYear_taken() {
        return year_taken;
    }

    public void setYear_taken(int year_taken) {
        this.year_taken = year_taken;
    }

    public String getTerm_taken() {
        return term_taken;
    }

    public void setTerm_taken(String term_taken) {
        this.term_taken = term_taken;
    }
}