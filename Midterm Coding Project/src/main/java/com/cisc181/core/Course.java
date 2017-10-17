package com.cisc181.core;

import com.cisc181.eNums.eMajor;

import java.util.UUID;

public class Course {

    private UUID CourseID;
    private String CourseName;
    private int GradePoints;
    private eMajor Major;

    public Course(UUID CourseID, String CourseName, int GradePoints, eMajor Major) {
        this.CourseID = CourseID;
        this.CourseName = CourseName;
        this.GradePoints = GradePoints;
        this.Major = Major;
    }

    public UUID getCourseID() {
        return CourseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public int getGradePoints() {
        return GradePoints;
    }

    public void setCourseID(UUID courseID) {
        this.CourseID = courseID;
    }

    public void setCourseName(String courseName) {
        this.CourseName = courseName;
    }

    public void setGradePoints(int gradePoints) {
        this.GradePoints = gradePoints;
    }
}
