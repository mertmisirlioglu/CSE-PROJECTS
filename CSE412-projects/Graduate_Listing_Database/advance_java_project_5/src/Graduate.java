import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Graduate {
    @Id
    private String student_id;

    @Temporal(TemporalType.DATE)
    private Date major_leaving_date;

    private boolean minor;

    public Graduate() {

    }

    public Graduate(String student_id, Date major_leaving_date, boolean minor){
        this.student_id = student_id;
        this.major_leaving_date = major_leaving_date;
        this.minor = minor;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public Date getMajor_leaving_date() {
        return major_leaving_date;
    }

    public void setMajor_leaving_date(Date major_leaving_date) {
        this.major_leaving_date = major_leaving_date;
    }

    public boolean isMinor() {
        return minor;
    }

    public void setMinor(boolean minor) {
        this.minor = minor;
    }

    @Override
    public String toString() {
        return this.student_id;
    }
}
