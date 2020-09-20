package qlhv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import qlhv.model.TaiKhoan;

/**
 *
 * @author PC
 */
public class TaiKhoanDAOImpl implements TaiKhoanDAO {

    @Override
    public TaiKhoan login(String name, String password) {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT * FROM tai_khoan WHERE ten_dang_nhap LIKE ? AND mat_khau LIKE ?";
        TaiKhoan taiKhoan = null;
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                taiKhoan = new TaiKhoan();
                taiKhoan.setCodeAccount(rs.getInt("ma_tai_khoan"));
                taiKhoan.setNameAccount(rs.getString("ten_dang_nhap"));
                taiKhoan.setPassword(rs.getString("mat_khau"));
                taiKhoan.setActive(rs.getBoolean("tinh_trang"));
            }
            ps.close();
            cons.close();
            return taiKhoan;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taiKhoan;
    }

}
