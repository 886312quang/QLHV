package qlhv.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import qlhv.model.HocVien;

/**
 *
 * @author PC
 */
public class HocVienDAOImpl implements HocVienDAO {

    @Override
    public List<HocVien> getList() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT * FROM student";
            List<HocVien> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HocVien hv = new HocVien();
                hv.setStudentCode(rs.getInt("studentCode"));
                hv.setName(rs.getString("name"));
                hv.setBirth(rs.getDate("birth"));
                hv.setAddress(rs.getString("address"));
                hv.setPhone(rs.getString("phone"));
                hv.setSex(rs.getBoolean("sex"));
                hv.setActive(rs.getBoolean("active"));

                list.add(hv);
            }
            ps.close();
            rs.close();
            cons.close();
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        HocVienDAO hocVienDAO = new HocVienDAOImpl();
        System.out.println(hocVienDAO.getList());
    }

    @Override
    public int createOrUpdate(HocVien hocVien) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "INSERT INTO student(studentCode, name, birth, sex, phone, address, active) VALUES(?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE name = VALUES(name), birth = VALUES(birth), sex = VALUES(sex), phone = VALUES(phone), address = VALUES(address), active = VALUES(active);";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, hocVien.getStudentCode());
            ps.setString(2, hocVien.getName());
            ps.setDate(3, new Date(hocVien.getBirth().getTime()));
            ps.setBoolean(4, hocVien.isSex());
            ps.setString(5, hocVien.getPhone());
            ps.setString(6, hocVien.getAddress());
            ps.setBoolean(7, hocVien.isActive());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            System.out.println(generatedKey);

            ps.close();
            cons.close();
            return generatedKey;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

}
