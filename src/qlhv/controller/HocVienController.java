package qlhv.controller;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import qlhv.model.HocVien;
import qlhv.service.HocVienService;
import qlhv.service.HocVienServiceImpl;
import qlhv.view.HocVienJFrame;

/**
 *
 * @author PC
 */
public class HocVienController {

    private JButton btnSubmit;
    private JTextField jtfCode;
    private JTextField jtfName;
    private JDateChooser jdcBirth;
    private JRadioButton jrdNam;
    private JRadioButton jrdNu;
    private JTextField jtfPhone;
    private JTextArea jtaAddress;
    private JCheckBox jcbActive;
    private JLabel jblMsg;

    private HocVien hocVien = null;
    private HocVienService hocVienService = null;

    public HocVienController(JButton btnSubmit, JTextField jtfCode, JTextField jtfName, JDateChooser jdcBirth,
            JRadioButton jrdNam, JRadioButton jrdNu, JTextField jtfPhone, JTextArea jtaAddress, JCheckBox jcbActive, JLabel jblMsg) {
        this.btnSubmit = btnSubmit;
        this.jtfCode = jtfCode;
        this.jtfName = jtfName;
        this.jdcBirth = jdcBirth;
        this.jrdNam = jrdNam;
        this.jrdNu = jrdNu;
        this.jtfPhone = jtfPhone;
        this.jtaAddress = jtaAddress;
        this.jcbActive = jcbActive;
        this.jblMsg = jblMsg;

        this.hocVienService = new HocVienServiceImpl();
    }

    public void setView(HocVien hocVien) {
        this.hocVien = hocVien;
        jtfCode.setText("#" + hocVien.getStudentCode());
        jtfName.setText(hocVien.getName());
        jdcBirth.setDate(hocVien.getBirth());
        if (hocVien.isSex()) {
            jrdNam.setSelected(true);
            jrdNu.setSelected(false);
        } else {
            jrdNam.setSelected(false);
            jrdNu.setSelected(true);
        }

        jtfPhone.setText(hocVien.getPhone());
        jtaAddress.setText(hocVien.getAddress());
        jcbActive.setSelected(hocVien.isActive());
    }

    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jtfName.getText().length() == 0 || jdcBirth.getDate() == null) {
                    jblMsg.setText("Vui lòng điền đầy đủ họ tên và ngày sinh!");
                } else {
                    hocVien.setName(jtfName.getText());
                    hocVien.setBirth(jdcBirth.getDate());
                    hocVien.setSex(jrdNam.isSelected());
                    hocVien.setPhone(jtfPhone.getText());
                    hocVien.setAddress(jtaAddress.getText());
                    hocVien.setActive(jcbActive.isSelected());

                    int lastId = hocVienService.createOrUpdate(hocVien);

                    System.out.println(lastId);

                    if (lastId != 0) {
                        hocVien.setStudentCode(lastId);
                        jtfCode.setText("#" + lastId);
                        jblMsg.setText("Cập nhật dữ liệu thành công!");
                        HocVienController controller = new HocVienController(btnSubmit, jtfCode, jtfName, jdcBirth, jrdNam, jrdNu, jtfPhone, jtaAddress, jcbActive, jblMsg);
                        controller.setView(hocVien);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSubmit.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSubmit.setBackground(new Color(100, 221, 23));
            }

        });
    }
}
