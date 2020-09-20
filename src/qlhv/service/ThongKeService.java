package qlhv.service;

import java.util.List;
import qlhv.bean.KhoaHocBean;
import qlhv.bean.LopHocBean;

/**
 *
 * @author PC
 */
public interface ThongKeService {

    public List<LopHocBean> getListByClass();

    public List<KhoaHocBean> getListByCourse();
}
