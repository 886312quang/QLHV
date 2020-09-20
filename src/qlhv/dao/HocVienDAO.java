package qlhv.dao;

import java.util.List;
import qlhv.model.HocVien;

/**
 *
 * @author PC
 */
public interface HocVienDAO {

    public List<HocVien> getList();

    public int createOrUpdate(HocVien hocVien);

}
