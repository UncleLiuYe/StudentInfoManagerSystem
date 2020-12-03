package site.liuye.studentinfomanager.entity;

import java.io.Serializable;

/**
 * @author LiuYe
 * @version 1.0
 * @date 2020/11/27 10:56
 */
public class Student implements Serializable {
    private int studentid;
    private String studentname;
    private ClassInfo studentclassid;
    private String studentsex;
    private String studentxuehao;

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public ClassInfo getStudentclassid() {
        return studentclassid;
    }

    public void setStudentclassid(ClassInfo studentclassid) {
        this.studentclassid = studentclassid;
    }

    public String getStudentsex() {
        return studentsex;
    }

    public void setStudentsex(String studentsex) {
        this.studentsex = studentsex;
    }

    public String getStudentxuehao() {
        return studentxuehao;
    }

    public void setStudentxuehao(String studentxuehao) {
        this.studentxuehao = studentxuehao;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentid=" + studentid +
                ", studentname='" + studentname + '\'' +
                ", studentclassid=" + studentclassid.toString() +
                ", studentsex='" + studentsex + '\'' +
                ", studentxuehao='" + studentxuehao + '\'' +
                '}';
    }
}
