package qlhv.service;

import qlhv.dao.TaiKhoanDAO;
import qlhv.dao.TaiKhoanDAOImpl;
import qlhv.model.TaiKhoan;

/**
 *
 * @author PC
 */
public class TaiKhoanServiceImpl implements TaiKhoanService {

    private TaiKhoanDAO taiKhoanDAO = null;

    public TaiKhoanServiceImpl() {
        taiKhoanDAO = new TaiKhoanDAOImpl();
    }

    @Override
    public TaiKhoan login(String name, String password) {
        return taiKhoanDAO.login(name, password);
    }

}
