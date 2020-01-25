import javax.persistence.*;
import java.util.Objects;

@Entity
public class Taken {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "student_id")
    @ManyToOne
    private Graduate graduate;

    @JoinColumn(name = "course_code")
    @ManyToOne
    private Course course;

    private int year_taken;
    private String slot_name;
    private String term_taken;
    private String grade;
    private int semester;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlot_name() {
        return slot_name;
    }

    public void setSlot_name(String slot_name) {
        this.slot_name = slot_name;
    }

    public Taken() {
    }

    public Taken(Graduate graduate, Course course, int year_taken, String term_taken, String grade, int semester,String slot_name) {
        this.graduate = graduate;
        this.course = course;
        this.year_taken = year_taken;
        this.term_taken = term_taken;
        this.grade = grade;
        this.semester = semester;
        this.slot_name = slot_name;
    }


    public Graduate getGraduate() {
        return graduate;
    }

    public void setGraduate(Graduate graduate) {
        this.graduate = graduate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Taken)) return false;
        Taken taken = (Taken) o;
        return year_taken == taken.year_taken &&
                semester == taken.semester &&
                Objects.equals(graduate, taken.graduate) &&
                Objects.equals(course, taken.course) &&
                Objects.equals(term_taken, taken.term_taken) &&
                Objects.equals(grade, taken.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(graduate, course, year_taken, term_taken, grade, semester);
    }
}