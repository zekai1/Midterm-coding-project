package com.cisc181.core;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.cisc181.eNums.eTitle;
import org.junit.BeforeClass;
import org.junit.Test;

public class Staff_Test {

    @BeforeClass
    public static void setup() {
    }

    @Test
    public void average() throws Exception {
        ArrayList<Staff> staffs = new ArrayList<Staff>();
        Staff one = new Staff("Gonzalo", "", "Arce", new Date(), "", "3021111111", "one@udel.edu", "MWF", 5, 60000, new Date(), eTitle.MR);
        Staff two = new Staff("Mohsen", "", "Badiey", new Date(), "", "3021111112", "two@udel.edu", "MWF", 4, 70000, new Date(), eTitle.MR);
        Staff three = new Staff("Kenneth", "", "Barner", new Date(), "", "3021111113", "three@udel.edu", "MWF", 3, 80000, new Date(), eTitle.MR);
        Staff four = new Staff("Stephan", "", "Bohacek", new Date(), "", "3021111114", "four@udel.edu", "MWF", 2, 90000, new Date(), eTitle.MR);
        Staff five = new Staff("Charles", "", "Boncelet", new Date(), "", "3021111115", "five@udel.edu", "MWF", 1, 100000, new Date(), eTitle.MR);
        staffs.add(one);
        staffs.add(two);
        staffs.add(three);
        staffs.add(four);
        staffs.add(five);

        float sum = 0;
        for (int i = 0; i < staffs.size(); i++) {
            Staff tmp = (Staff) staffs.get(i);
            sum += tmp.getSalary();
        }
        float average = sum / staffs.size();
        assertEquals(average, 80000, 0.001);

    }

    @Test
    public void test_PhoneNumber() {

        try{
            Staff one = new Staff("Gonzalo", "", "Arce", new Date(), "", "asdsa", "one@udel.edu", "MWF", 5, 60000, new Date(), eTitle.MR);
            fail("Expected an PersonException to be thrown.");
        }catch (PersonException e){
            assertThat(e.getMessage(), is("Bad Phone Number."));

        }
    }

    @Test
    public void test_DOB() {
        Date t = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String input = "1818-11-11";
        try{
            t = ft.parse(input);
            Staff two = new Staff("Gonzalo", "", "Arce", t, "", "3021111112", "one@udel.edu", "MWF", 5, 60000, new Date(), eTitle.MR);
            fail("Expected an PersonException to be thrown.");
        }catch (ParseException e){
            System.out.println("Unparseable using " + ft);
        }catch (PersonException e){
            assertThat(e.getMessage(), is("Bad DOB."));
        }
    }

}
