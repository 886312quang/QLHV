package qlhv.bean;

/**
 *
 * @author PC
 */
public class LopHocBean {

    private String dateStart;
    private int countStudent;

    public LopHocBean() {

    }

    public LopHocBean(String dateStart, int countStudent) {
        this.dateStart = dateStart;
        this.countStudent = countStudent;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public int getCountStudent() {
        return countStudent;
    }

    public void setCountStudent(int countStudent) {
        this.countStudent = countStudent;
    }

}
