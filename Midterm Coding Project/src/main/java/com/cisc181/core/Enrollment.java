package com.cisc181.core;

import java.util.UUID;

public class Enrollment {

    private UUID SectionID;
    private UUID StudentID;
    private UUID EnrollmentID;
    private double Grade;

    public Enrollment() {
    }


    public Enrollment(UUID sectionID, UUID studentID) {
        this.SectionID = sectionID;
        this.StudentID = studentID;
        this.EnrollmentID = UUID.randomUUID();
    }

    public void setGrade(double grade) {
        this.Grade = grade;
    }

    public UUID getSectionID() {
        return SectionID;
    }

    public UUID getStudentID() {
        return StudentID;
    }

    public UUID getEnrollmentID() {
        return EnrollmentID;
    }

    public double getGrade() {
        return Grade;
    }
}
