package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.Random;
import com.cisc181.eNums.eMajor;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Student_Test {

    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private ArrayList<Section> sections;
    private ArrayList<ArrayList<Enrollment>> enrollments;
    private ArrayList<ArrayList<Double>> data;


    @Before
	public void  setup() {
	    students = new ArrayList<Student>(10);
	    try{
            Student s_1 = new Student("Aaric", "", "Smith", new Date(), eMajor.BUSINESS, "", "3025551111", "s1@udel.edu");
            Student s_2 = new Student("Abbot", "", "Johnson", new Date(), eMajor.BUSINESS, "", "3025551112", "s2@udel.edu");
            Student s_3 = new Student("Aethelstun", "", "Brown", new Date(), eMajor.BUSINESS, "", "3025551113", "s3@udel.edu");
            Student s_4 = new Student("Abbot", "", "Smith", new Date(), eMajor.BUSINESS, "", "3025551114", "s4@udel.edu");
            Student s_5 = new Student("Aaric", "", "Brown", new Date(), eMajor.BUSINESS, "", "3025551115", "s5@udel.edu");
            Student s_6 = new Student("Abbot", "", "Brown", new Date(), eMajor.BUSINESS, "", "3025551116", "s6@udel.edu");
            Student s_7 = new Student("Aaric", "", "Morris", new Date(), eMajor.BUSINESS, "", "3025551117", "s7@udel.edu");
            Student s_8 = new Student("Mabel", "", "James", new Date(), eMajor.BUSINESS, "", "3025551118", "s8@udel.edu");
            Student s_9 = new Student("Maddie", "", "Perry", new Date(), eMajor.BUSINESS, "", "3025551119", "s9@udel.edu");
            Student s_10 = new Student("Madelene", "", "Howard", new Date(), eMajor.BUSINESS, "", "3025551110", "s10@udel.edu");
            students.add(s_1);
            students.add(s_2);
            students.add(s_3);
            students.add(s_4);
            students.add(s_5);
            students.add(s_6);
            students.add(s_7);
            students.add(s_8);
            students.add(s_9);
            students.add(s_10);
        }catch (PersonException e){
	        System.out.println("Bad!!!");
        }



		courses= new ArrayList<Course>(3);
		Course c_1 = new Course(UUID.randomUUID(), "Java", 4, eMajor.COMPSI);
        Course c_2 = new Course(UUID.randomUUID(), "Introduction to Marketing", 4, eMajor.BUSINESS);
        Course c_3 = new Course(UUID.randomUUID(), "Introduction to Computer System", 4, eMajor.COMPSI);
        courses.add(c_1);
        courses.add(c_2);
        courses.add(c_3);

        ArrayList<Semester> semesters = new ArrayList<Semester>(2);
        Semester fall = new Semester(UUID.randomUUID(), new Date(), new Date());
        Semester spring = new Semester(UUID.randomUUID(), new Date(), new Date());
        semesters.add(fall);
        semesters.add(spring);

        sections = new ArrayList<Section>(6);
        Section se_1 = new Section(c_1.getCourseID(), fall.getSemesterID(), UUID.randomUUID(), 1);
        Section se_2 = new Section(c_1.getCourseID(), spring.getSemesterID(), UUID.randomUUID(), 2);
        Section se_3 = new Section(c_2.getCourseID(), fall.getSemesterID(), UUID.randomUUID(), 3);
        Section se_4 = new Section(c_2.getCourseID(), spring.getSemesterID(), UUID.randomUUID(), 4);
        Section se_5 = new Section(c_3.getCourseID(), fall.getSemesterID(), UUID.randomUUID(), 5);
        Section se_6 = new Section(c_3.getCourseID(), spring.getSemesterID(), UUID.randomUUID(), 6);
        sections.add(se_1);
        sections.add(se_2);
        sections.add(se_3);
        sections.add(se_4);
        sections.add(se_5);
        sections.add(se_6);


        enrollments = new ArrayList<ArrayList<Enrollment>>(10);
        data = new ArrayList<ArrayList<Double>>(10);

        //use two for loop to construct 2d arraylist, row is student, column is section
        //use random to generate 0.0-4.0 grade for each enrollement
        //data arraylist to store the real data to test
        for (int i=0; i<students.size(); i++){
            ArrayList<Enrollment> tmp = new ArrayList<Enrollment>(6);
            ArrayList<Double> tmp_scores = randomList(0, 4, 6);
            data.add(tmp_scores);
            for (int j=0; j<sections.size(); j++){
                Enrollment tmp_enroll = new Enrollment(sections.get(j).getSectionID(), students.get(i).getStudentID());
                tmp_enroll.setGrade(tmp_scores.get(j));
                tmp.add(tmp_enroll);
            }
            enrollments.add(tmp);
        }
    }

    //use random to gernerate double random number between min and max
    public static double randDouble(int min, int max) {

        // NOTE: This will (intentionally) not run as written so that folks
        // copy-pasting have to think about how to initialize their
        // Random instance.  Initialization of the Random instance is outside
        // the main scope of the question, but some decent options are to have
        // a field that is initialized once and then re-used as needed or to
        // use ThreadLocalRandom (if using at least Java 1.7).
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        double randomNum = rand.nextDouble() * ((max - min) + 1) + min;

        return randomNum;
    }

    //use randDouble function to generate grades for each section for each student
    public static ArrayList<Double> randomList(int min, int max, int num) {
	    ArrayList<Double> randoms = new ArrayList<Double>(num);
        for (int i=0; i<num; i++){
            randoms.add(randDouble(min, max));
        }
        return randoms;
    }


    @Test
	public void gpa_test() {
        for (int i=0; i<students.size(); i++){
            double sum_expected = 0;
            double sum_true = 0;
            for (int j=0; j<sections.size(); j++){
                sum_expected += enrollments.get(i).get(j).getGrade();
            }
            for (int k=0; k<data.get(i).size(); k++){
                sum_true += data.get(i).get(k);
            }
            //CREDIT IS 3 FOR EACH COURSE
            assertEquals((sum_expected*3)/(3*sections.size()), (sum_true*3)/(3*data.get(i).size()), 0.001);
        }
    }

    public String getCourseName(UUID sectionID) {
	    String courseName = "";
        for (int i = 0; i < sections.size(); i++) {
            if (sections.get(i).getSectionID().equals(sectionID)) {
                for (int j = 0; j < courses.size(); j++) {
                    if (sections.get(i).getCourseID().equals(courses.get(j).getCourseID())) {
                        courseName = courses.get(j).getCourseName();
                    }
                }
            }
        }
        return courseName;
    }

    @Test
    public void average_test(){
	    String courseName;
        double java_grade = 0;
        double market_grade = 0;
        double system_grade = 0;
        double java_grade_true = 0;
        double market_grade_true = 0;
        double system_grade_true = 0;

        for (int i=0; i<students.size(); i++){
	        for (int j=0; j<sections.size(); j++){
	            UUID se_id = enrollments.get(i).get(j).getSectionID();
	            courseName = getCourseName(se_id);
	            if (courseName.equals("Java")){
	                java_grade += enrollments.get(i).get(j).getGrade();
                }
                if (courseName.equals("Introduction to Marketing")){
                    market_grade += enrollments.get(i).get(j).getGrade();
                }
                if (courseName.equals("Introduction to Computer System")){
                    system_grade += enrollments.get(i).get(j).getGrade();
                }
            }
            java_grade_true += enrollments.get(i).get(0).getGrade() + enrollments.get(i).get(1).getGrade();
            market_grade_true += enrollments.get(i).get(2).getGrade() + enrollments.get(i).get(3).getGrade();
            system_grade_true += enrollments.get(i).get(4).getGrade() + enrollments.get(i).get(5).getGrade();
        }

        assertEquals(java_grade/20, java_grade_true/20, 0.001);
        assertEquals(market_grade/20, market_grade_true/20, 0.001);
        assertEquals(system_grade/20, system_grade_true/20, 0.001);
    }
}