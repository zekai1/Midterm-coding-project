package com.cisc181.core;

import java.util.UUID;

public class Section {

    private UUID CourseID;
    private UUID SemesterID;
    private UUID SectionID;
    private int RoomID;


    public Section(UUID courseID, UUID semesterID, UUID sectionID, int roomID) {
        this.CourseID = courseID;
        this.SemesterID = semesterID;
        this.SectionID = sectionID;
        this.RoomID = roomID;
    }

    public UUID getCourseID() {
        return CourseID;
    }

    public UUID getSemesterID() {
        return SemesterID;
    }

    public UUID getSectionID() {
        return SectionID;
    }

    public int getRoomID() {
        return RoomID;
    }

    public void setCourseID(UUID courseID) {
        this.CourseID = courseID;
    }

    public void setSemesterID(UUID semesterID) {
        this.SemesterID = semesterID;
    }

    public void setSectionID(UUID sectionID) {
        this.SectionID = sectionID;
    }

    public void setRoomID(int roomID) {
        this.RoomID = roomID;
    }
}
