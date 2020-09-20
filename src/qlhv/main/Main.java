package qlhv.main;

import qlhv.view.LoginJDialog;

/**
 *
 * @author PC
 */
public class Main {

    public static void main(String[] args) {

        LoginJDialog dialog = new LoginJDialog(null, true);
        dialog.setTitle("Đăng nhập");
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }
}
