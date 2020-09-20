package qlhv.bean;

import java.util.Date;

/**
 *
 * @author PC
 */
public class KhoaHocBean {

    private String source;
    private Date dateStart;
    private Date dateEnd;

    public KhoaHocBean() {
    }

    public KhoaHocBean(String source, Date dateStart, Date dateEnd) {
        this.source = source;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

}
