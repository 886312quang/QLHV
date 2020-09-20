package qlhv.dao;

import java.util.List;
import qlhv.bean.KhoaHocBean;
import qlhv.bean.LopHocBean;

/**
 *
 * @author PC
 */
public interface ThongKeDAO {

    public List<LopHocBean> getListByClass();
    
    public List<KhoaHocBean> getListBySource();
}
