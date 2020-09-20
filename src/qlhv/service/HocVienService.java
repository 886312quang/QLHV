package qlhv.service;

import java.util.List;
import qlhv.model.HocVien;

/**
 *
 * @author PC
 */
public interface HocVienService {

    public List<HocVien> getList();

    public int createOrUpdate(HocVien hocVien);
}
