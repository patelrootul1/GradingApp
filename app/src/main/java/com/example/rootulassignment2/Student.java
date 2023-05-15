package com.example.rootulassignment2;

//Class created for entering records in the database School.db
public class Student {
    int student_ID;

    String studentName;
    String Program;
    int course1;
    int course2;
    int course3;
    int course4;

    public void setStudent_ID(int student_ID) {
        this.student_ID = student_ID;
    }

    public int getStudent_ID() {
        return student_ID;
    }


    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setProgram(String program) {
        Program = program;
    }

    public String getProgram() {
        return Program;
    }

    public void setCourse1(int course1) {
        this.course1 = course1;
    }

    public int getCourse1() {
        return course1;
    }

    public void setCourse2(int course2) {
        this.course2 = course2;
    }

    public int getCourse2() {
        return course2;
    }

    public void setCourse3(int course3) {
        this.course3 = course3;
    }

    public int getCourse3() {
        return course3;
    }

    public void setCourse4(int course4) {
        this.course4 = course4;
    }

    public int getCourse4() {
        return course4;
    }

    public int getTotalMarks(){
       return course1 + course2 + course3 + course4;
    }

}
