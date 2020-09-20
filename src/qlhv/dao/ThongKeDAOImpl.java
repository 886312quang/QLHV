/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlhv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import qlhv.bean.KhoaHocBean;
import qlhv.bean.LopHocBean;

/**
 *
 * @author PC
 */
public class ThongKeDAOImpl implements ThongKeDAO {
    
    @Override
    public List<LopHocBean> getListByClass() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT dateStart, COUNT(*) AS countStudent FROM class GROUP BY dateStart";
            List<LopHocBean> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LopHocBean lopHocBean = new LopHocBean();
                lopHocBean.setDateStart(rs.getString("dateStart"));
                lopHocBean.setCountStudent(rs.getInt("countStudent"));
                
                list.add(lopHocBean);
            }
            rs.close();
            ps.close();
            cons.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<KhoaHocBean> getListBySource() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT course,dateStart, dateEnd FROM course WHERE active = TRUE";
            List<KhoaHocBean> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhoaHocBean khoaHocBean = new KhoaHocBean();
                khoaHocBean.setSource(rs.getString("course"));
                khoaHocBean.setDateStart(rs.getDate("dateStart"));
                khoaHocBean.setDateEnd(rs.getDate("dateEnd"));
                
                list.add(khoaHocBean);
            }
            rs.close();
            ps.close();
            cons.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
