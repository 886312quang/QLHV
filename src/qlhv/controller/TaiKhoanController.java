package qlhv.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import qlhv.model.TaiKhoan;
import qlhv.service.TaiKhoanService;
import qlhv.service.TaiKhoanServiceImpl;
import qlhv.view.MainJFrame;

/**
 *
 * @author PC
 */
public class TaiKhoanController {
    
    private JDialog dialog;
    private JButton btnSubmit;
    private JTextField jtfNameAccount;
    private JTextField jtfPassword;
    private JLabel jblMsg;
    
    private TaiKhoanService taiKhoanService = null;
    
    public TaiKhoanController(JDialog dialog, JButton btnSubmit, JTextField jtfNameAccount, JTextField jtfPassword, JLabel jblMsg) {
        this.dialog = dialog;
        this.btnSubmit = btnSubmit;
        this.jtfNameAccount = jtfNameAccount;
        this.jtfPassword = jtfPassword;
        this.jblMsg = jblMsg;
        
        this.taiKhoanService = new TaiKhoanServiceImpl();
    }
    
    public TaiKhoanController() {
    }
    
    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jtfNameAccount.getText().trim().length() == 0 || jtfPassword.getText().length() == 0) {
                    jblMsg.setText("Vui lòng nhập đầy đủ thông tin!!!");
                } else {
                    TaiKhoan taiKhoan = taiKhoanService.login(jtfNameAccount.getText(), jtfPassword.getText());
                    if (taiKhoan == null) {
                        jblMsg.setText("Sai thông tin đăng nhập!!!");
                    } else {
                        if (!taiKhoan.isActive()) {
                                jblMsg.setText("Tài khoản hiện đang bị tạm khóa!");
                        } else {
                            dialog.dispose();
                            MainJFrame frame = new MainJFrame();
                            frame.setTitle("Quản lý học viên");
                            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                            frame.setVisible(true);
                        }
                    }
                }
            }
        });
    }
    
}
