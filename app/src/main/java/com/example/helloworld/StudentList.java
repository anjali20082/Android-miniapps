package com.example.helloworld;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class StudentList {

    private static StudentList sStudentDetails;

    private List<Student> mStudents;

    public static StudentList get(Context context) {

        if (sStudentDetails == null) {
            sStudentDetails = new StudentList(context);
        }

        return sStudentDetails;
    }

    private StudentList(Context context) {
        mStudents = new ArrayList<>();
        String [] namelist = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l","m","n","o",
                "aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh", "ii", "jj", "kk", "ll","mm","nn","oo"};
        for (int i = 0; i < 30; i++) {
            int c = i+1;
            Student crime = new Student();
            crime.setName("Student " + namelist[i]);
            crime.setRollno("MT"+c);
            if (i<20){
                crime.setDept("CSE");}
            else{
                crime.setDept("ECE");
            }
            crime.setEmailid(c+"@iiitd.ac.in");
            mStudents.add(crime);
        }
    }

    public List<Student> getStudents() {
        return mStudents;
    }

    public Student getStudent( UUID mid) {
        for (Student student : mStudents) {
            if (student.getId().equals(mid)) {
                return student;
            }
        }

        return null;
    }
}
