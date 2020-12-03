package site.liuye.studentinfomanager.entity;

import java.io.Serializable;

/**
 * @author LiuYe
 * @version 1.0
 * @date 2020/11/27 10:58
 */
public class ClassInfo implements Serializable {
    private int classid;
    private String classname;

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "classid=" + classid +
                ", classname='" + classname + '\'' +
                '}';
    }
}
