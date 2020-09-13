package qlhv.model;

import java.util.Date;

/**
 *
 * @author PC
 */
public class LopHoc {

    private int classCode;
    private KhoaHoc course;
    private HocVien student;
    private Date dateRegistation;
    private boolean active;

    public int getClassCode() {
        return classCode;
    }

    public void setClassCode(int classCode) {
        this.classCode = classCode;
    }

    public KhoaHoc getCourse() {
        return course;
    }

    public void setCourse(KhoaHoc course) {
        this.course = course;
    }

    public HocVien getStudent() {
        return student;
    }

    public void setStudent(HocVien student) {
        this.student = student;
    }

    public Date getDateRegistation() {
        return dateRegistation;
    }

    public void setDateRegistation(Date dateRegistation) {
        this.dateRegistation = dateRegistation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
