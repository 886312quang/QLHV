/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlhv.service;

import java.util.List;
import qlhv.bean.KhoaHocBean;
import qlhv.bean.LopHocBean;
import qlhv.dao.ThongKeDAO;
import qlhv.dao.ThongKeDAOImpl;

/**
 *
 * @author PC
 */
public class ThongKeServiceImpl implements ThongKeService {

    private ThongKeDAO thongKeDAO = null;

    public ThongKeServiceImpl() {
        thongKeDAO = new ThongKeDAOImpl();
    }

    @Override
    public List<LopHocBean> getListByClass() {
        return thongKeDAO.getListByClass();
    }

    @Override
    public List<KhoaHocBean> getListByCourse() {
        return thongKeDAO.getListBySource();
    }

}
