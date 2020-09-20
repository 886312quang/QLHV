package qlhv.utility;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import qlhv.model.HocVien;

/**
 *
 * @author PC
 */
public class ClassTableModel {

    public DefaultTableModel setTableHocVien(List<HocVien> listItem, String[] listColumn) {

        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 7 ? Boolean.class : String.class;
            }

        };
        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;
        int rows = listItem.size();
        if (rows > 0) {
            for (int i = 0; i < rows; i++) {
                HocVien hocVien = listItem.get(i);

                obj = new Object[columns];
                obj[0] = hocVien.getStudentCode();
                obj[1] = (i + 1);
                obj[2] = hocVien.getName();
                obj[3] = hocVien.getBirth();
                obj[4] = hocVien.isSex() == true ? "Nam" : "Nữ";
                obj[5] = hocVien.getPhone();
                obj[6] = hocVien.getAddress();
                obj[7] = hocVien.isActive();

                dtm.addRow(obj);
            }
        }

        return dtm;
    }

}
