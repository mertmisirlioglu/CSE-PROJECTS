import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.*;
import java.util.*;


public class populate_db {
    public static ArrayList<Graduate> graduateList = new ArrayList<Graduate>();
    public static Set<Course> courseList = new HashSet<Course>();
    public static ArrayList<Taken> takenList = new ArrayList<Taken>();
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    static EntityManager em = emf.createEntityManager();
    static EntityTransaction tx = em.getTransaction();
    public static void main(String[] args) throws IOException {
        File folder = new File("students");
        File[] listOfFiles = folder.listFiles();
        BufferedReader reader;
        String[] student_info = new String[5];
        String[] course_info = new String[3];
        String[] taken_info = new String[7];
        String[] course_elective = new String[3];
        int semester = 0;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                String[] splited;
                while (line != null) {
                    splited = line.split(" ");
                    if(splited[0].equals("majorleavingdate")){
                    student_info[0] = splited[1];
                    student_info[1] = splited[2];
                    student_info[2] = splited[3];
                    }
                    else if(splited[0].equals("studentnumber")){
                        student_info[3] = splited[1];
                    }
                    else if(splited[0].equals("minor")){
                        student_info[4] = splited[1];
                        createStudent(student_info);
                    }
                    else if(splited[0].equals("semester")){
                        semester = Integer.parseInt(splited[1]);
                    }
                    else if(splited.length == 3){
                        course_info[0] = splited[0];
                        course_info[1] = splited[1];
                        course_info[2] = splited[2];
                        createCourse(course_info);
                    }
                    else if(splited.length == 4){
                        taken_info[0] = splited[0];
                        taken_info[1] = splited[1];
                        taken_info[2] = splited[2];
                        taken_info[3] = splited[3];

                        taken_info[4] = String.valueOf(semester);
                        taken_info[5] = student_info[3];
                        taken_info[6] = course_info[0];
                        if(!course_info[0].equals(taken_info[3])) {
                            course_elective = new String[]{taken_info[3], "unknown", course_info[2]};
                            createCourse(course_elective);
                        }
                        createTaken(taken_info);
                    }
                    line = reader.readLine();
                }
                System.out.println(file.getName());
            }
        }

        persistGraduates();
        persistCourses();
        persistTaken();

    }


    private static void createTaken(String[] taken_info) {
        String[] taken = taken_info.clone();
        Graduate g = null;
        Course c = null;
        for (int i = 0; i < graduateList.size(); i++){
            if(graduateList.get(i).getStudent_id().equals(taken[5])){
                g = graduateList.get(i);
                break;
            }
        }
        for (Course course : courseList) {
            if(course.getCourse_code().equals(taken[3])){
                c = course;
                break;
            }
        }
        Taken t = new Taken(g,c,Integer.parseInt(taken[0]),taken[1],taken[2],Integer.parseInt(taken[4]),taken_info[6]);
        takenList.add(t);
    }


    private static void createCourse(String[] course_info) {
        Course course = new Course(course_info[0],course_info[1],Integer.parseInt(course_info[2]));
        courseList.add(course);
    }

    private static void createStudent(String[] student_info) {
        Date date = new GregorianCalendar(Integer.parseInt(student_info[2]), Integer.parseInt(student_info[1]) - 1, Integer.parseInt(student_info[0])).getTime();
        String student_number = student_info[3];
        boolean minor = false;
        if(student_info[4].equals("true")){
            minor = true;
        }
        Graduate g = new Graduate(student_number,date,minor);
        graduateList.add(g);

    }

    private static void persistGraduates(){
        tx.begin();
        for (int i = 0; i < graduateList.size(); i++){
            em.persist(graduateList.get(i));
        }

    }
    private static void persistCourses(){
        for (Course course : courseList) {
            em.persist(course);
        }

    }
    private static void persistTaken(){

        for (Taken taken : takenList) {
            em.persist(taken);
        }
        tx.commit();
        em.close();
    }

}
