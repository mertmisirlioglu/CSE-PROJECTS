import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    EntityManager em = emf.createEntityManager();
    List<Graduate> listOfGraduates;
    List<Taken> listOfTaken;
    List<Course> listOfCourses;


    @FXML
    private ComboBox<Integer> yearComboBox;

    @FXML
    private ComboBox<Graduate> studentComboBox;

    @FXML
    private Button listTakenButton;

    @FXML
    private CheckBox includeYearCheck;

    @FXML
    private ComboBox<String> slotComboBox;

    @FXML
    private Text averageOfCourseText;

    @FXML
    private Text averageOfSlotText;

    @FXML
    private Menu coursesButton;

    @FXML
    private TableView<Taken> tableView;

    @FXML
    private ListView<Graduate> GraduateListView;

    @FXML
    private ListView<Course> courseListView;

    @FXML
    private Text averageofGraduate;


    @FXML
    void listCourseWithSlot(ActionEvent event) {
        if (includeYearCheck.isSelected() && yearComboBox.getSelectionModel().isEmpty()) {
            averageOfCourseText.setText("Please select year in your right !");
        }
        if (!slotComboBox.getSelectionModel().isEmpty()) {
            String slot_name = slotComboBox.getSelectionModel().getSelectedItem();
            List<String> grades = em.createQuery("select  T.grade  from Taken T where T.slot_name='" + slot_name + "' and T.grade != 'F' and T.grade !='p' and T.grade != 'w'").getResultList();
            averageOfSlotText.setText(slot_name + " slot average: " + calculateAverage(grades));
        }
        if (includeYearCheck.isSelected() && !yearComboBox.getSelectionModel().isEmpty()) {

            int year = yearComboBox.getSelectionModel().getSelectedItem();
            String slot_name = slotComboBox.getSelectionModel().getSelectedItem();

            List<Course> listOfCourses_year_slot = em.createQuery("select DISTINCT  T.course  from Taken T where T.year_taken=" + year + " and T.slot_name ='" + slot_name + "'").getResultList();
            ObservableList<Course> data = FXCollections.observableArrayList(listOfCourses_year_slot);
            courseListView.setItems(data);

        } else {
            String slot_name = slotComboBox.getSelectionModel().getSelectedItem();
            List<Course> listOfCourses_slot = em.createQuery("select DISTINCT T.course from Taken T where  T.slot_name ='" + slot_name + "'").getResultList();
            ObservableList<Course> data = FXCollections.observableArrayList(listOfCourses_slot);
            courseListView.setItems(data);
        }
    }

    @FXML
    void listGraduatesWithYear(ActionEvent event) {
        int year = yearComboBox.getSelectionModel().getSelectedItem();
        List<Graduate> listOfGraduates_year = em.createQuery("select G from Graduate G where (EXTRACT(year from G.major_leaving_date)) " +
                "=" + year + "").getResultList();
        ObservableList<Graduate> data = FXCollections.observableArrayList(listOfGraduates_year);
        GraduateListView.setItems(data);


        List<Course> year_courses = em.createQuery("select  DISTINCT T.course  from Taken T where T.year_taken=" + year).getResultList();
        ObservableList<Course> data2 = FXCollections.observableArrayList(year_courses);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                courseListView.setItems(data2);
            }
        });


        List<String> grades = em.createQuery("select  T.grade  from Taken T where T.year_taken=" + year + " and T.grade != 'F' and T.grade !='p' and T.grade != 'w'").getResultList();
        averageOfCourseText.setText(year + " given courses average: " + calculateAverage(grades));

    }


    @FXML
    void listTakenWithGraduate(ActionEvent event) {
        List<Taken> listOfTaken_grad = em.createQuery("select T from Taken T where T.graduate.student_id ='" + studentComboBox.getSelectionModel().getSelectedItem() + "'").getResultList();
        ObservableList<Taken> data = FXCollections.observableArrayList(listOfTaken_grad);
        tableView.setItems(data);

        List<String> student_grades = em.createQuery("select T.grade from Taken T where T.graduate.student_id ='" + studentComboBox.getSelectionModel().getSelectedItem() + "' and T.grade != 'F' and T.grade !='p' and T.grade != 'w'").getResultList();
        averageofGraduate.setText("GPA : " + calculateAverage(student_grades));
    }


    private void getData() {
        listOfGraduates = em.createQuery("select G from Graduate G").getResultList();
        listOfTaken = em.createQuery("select T from Taken T").getResultList();
        listOfCourses = em.createQuery("select C from Course C").getResultList();
    }

    @FXML
    void showGraduates(ActionEvent event) {
        ObservableList<Graduate> data = FXCollections.observableArrayList(listOfGraduates);
        GraduateListView.setItems(data);


    }

    @FXML
    void showTaken(ActionEvent event) {
        ObservableList<Taken> data = FXCollections.observableArrayList(listOfTaken);
        tableView.setItems(data);
    }

    @FXML
    void showCourses(ActionEvent event) {
        ObservableList<Course> data = FXCollections.observableArrayList(listOfCourses);
        courseListView.setItems(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData();
        setCombos();
        prepareTable();
        averageOfCourseText.setText("");
        averageOfSlotText.setText("");
        averageofGraduate.setText("");


        courseListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            Course c = courseListView.getSelectionModel().getSelectedItem();
            List<String> student_grades = em.createQuery("select T.grade from Taken T where T.course.course_code ='" + c.getCourse_code() + "' and T.grade != 'F' and T.grade !='p' and T.grade != 'w'").getResultList();
            averageOfCourseText.setText("Average of selected course : " + calculateAverage(student_grades));
        });
    }

    private void prepareTable() {
        TableColumn<Taken, String> tc1 = new TableColumn("SLOT_NAME");
        tc1.setCellValueFactory(new PropertyValueFactory<Taken, String>("slot_name"));
        tc1.setMinWidth(100);
        TableColumn<Taken, String> tc2 = new TableColumn("YEAR_TAKEN");
        tc2.setCellValueFactory(new PropertyValueFactory<Taken, String>("year_taken"));
        tc2.setMinWidth(100);
        TableColumn<Taken, String> tc3 = new TableColumn("TERM_TAKEN");
        tc3.setCellValueFactory(new PropertyValueFactory<Taken, String>("term_taken"));
        tc3.setMinWidth(110);
        TableColumn<Taken, String> tc4 = new TableColumn("COURSE_CODE");
        tc4.setCellValueFactory(new PropertyValueFactory<Taken, String>("course"));
        tc4.setMinWidth(120);
        TableColumn<Taken, String> tc5 = new TableColumn("STUDENT_ID");
        tc5.setCellValueFactory(new PropertyValueFactory<Taken, String>("graduate"));
        tc5.setMinWidth(130);
        TableColumn<Taken, String> tc6 = new TableColumn("SEMESTER");
        tc6.setCellValueFactory(new PropertyValueFactory<Taken, String>("semester"));
        tc6.setMinWidth(100);
        TableColumn<Taken, String> tc7 = new TableColumn("GRADE");
        tc7.setCellValueFactory(new PropertyValueFactory<Taken, String>("grade"));
        tableView.getColumns().clear();
        tableView.getColumns().addAll(tc1, tc2, tc3, tc4, tc5, tc6, tc7);
    }

    private void setCombos() {
        ArrayList<Integer> years = new ArrayList<>();
        for (int i = 1996; i < 2016; i++) {
            years.add(i);
        }
        ObservableList<Integer> data = FXCollections.observableArrayList(years);
        yearComboBox.setItems(data);

        List<Graduate> listOfGraduates_id = em.createQuery("select G.student_id from Graduate G").getResultList();
        ObservableList<Graduate> data2 = FXCollections.observableArrayList(listOfGraduates_id);
        studentComboBox.setItems(data2);

        List<String> listOfTaken_slot = em.createQuery("select T.slot_name from Taken T").getResultList();
        ObservableList<String> data3 = FXCollections.observableArrayList(listOfTaken_slot);
        slotComboBox.setItems(data3);

    }

    private String calculateAverage(List<String> grades) {
        double sum = 0.0;
        for (String grade : grades) {
            if (grade.equals("aa"))
                sum += 4.0;
            else if (grade.equals("ba"))
                sum += 3.5;
            else if (grade.equals("bb"))
                sum += 3.0;
            else if (grade.equals("cb"))
                sum += 2.5;
            else if (grade.equals("cc"))
                sum += 2;
            else if (grade.equals("dc"))
                sum += 1.5;
            else if (grade.equals("dd"))
                sum += 1.0;
        }

        double avg = sum / grades.size();
        if (avg == 4.00) {
            return "aa";
        } else if (avg >= 3.5) {
            return "ba";
        } else if (avg >= 3.0) {
            return "bb";
        } else if (avg >= 2.5) {
            return "cb";
        } else if (avg >= 2.0) {
            return "cc";
        } else if (avg >= 1.5) {
            return "dc";
        } else if (avg >= 1.0) {
            return "dd";
        }
        return "None";
    }


}
