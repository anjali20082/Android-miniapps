package com.example.helloworld;

import java.util.UUID;

public class Student {

    private String name;
    private UUID id;
    private String dept;
    private String rollno;
    private String emailid;

    public Student(){
        id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String sname) {
        name = sname;
    }

    public UUID getId() {
        return id;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String srollno) {
        rollno = srollno;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String sdept) {
        dept = sdept;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String smail) {
        emailid = smail;
    }


}
