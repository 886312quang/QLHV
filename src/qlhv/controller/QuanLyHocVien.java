package qlhv.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import qlhv.model.HocVien;
import qlhv.service.HocVienService;
import qlhv.service.HocVienServiceImpl;
import qlhv.utility.ClassTableModel;
import qlhv.view.HocVienJFrame;

/**
 *
 * @author PC
 */
public class QuanLyHocVien {
    
    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    private JButton btnOFile;
    
    private HocVienService hocVienService = null;
    
    private String[] listColumn = {"Mã học vien", "STT", "Họ và Tên", "Ngày sinh", "Giới tính", "SĐT", "Địa chỉ", "Trạng thái"};
    
    private TableRowSorter<TableModel> rowSorter = null;
    
    public QuanLyHocVien(JPanel jpnView, JButton btnAdd, JTextField jtfSearch, JButton btnOFile) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnOFile = btnOFile;
        
        this.hocVienService = new HocVienServiceImpl();
    }
    
    public void setDateToTable() {
        List<HocVien> listItem = hocVienService.getList();
        
        DefaultTableModel model = new ClassTableModel().setTableHocVien(listItem, listColumn);
        JTable table = new JTable(model);
        
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setPreferredWidth(0);
        
        table.getColumnModel().getColumn(1).setMinWidth(80);
        table.getColumnModel().getColumn(1).setMaxWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();

                    // get Index Row
                    int selectRowIndex = table.getSelectedRow();
                    selectRowIndex = table.convertRowIndexToModel(selectRowIndex);
                    System.out.println(selectRowIndex);
                    
                    HocVien hocVien = new HocVien();
                    hocVien.setStudentCode((int) model.getValueAt(selectRowIndex, 0));
                    hocVien.setName(model.getValueAt(selectRowIndex, 2).toString());
                    hocVien.setSex(model.getValueAt(selectRowIndex, 4).toString().equalsIgnoreCase("Nam"));
                    hocVien.setBirth((Date) model.getValueAt(selectRowIndex, 3));
                    hocVien.setPhone(model.getValueAt(selectRowIndex, 5) != null
                            ? model.getValueAt(selectRowIndex, 5).toString()
                            : ""
                    );
                    hocVien.setAddress(model.getValueAt(selectRowIndex, 6) != null
                            ? model.getValueAt(selectRowIndex, 6).toString() : "");
                    hocVien.setActive((boolean) model.getValueAt(selectRowIndex, 7));
                    
                    HocVienJFrame frame = new HocVienJFrame(hocVien);
                    frame.setTitle("Thông tin học viên");
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            }
        });
        
        table.getTableHeader().setFont(new Font("Arrial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.setPreferredSize(new Dimension(1300, 400));
        
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scrollPane);
        jpnView.validate();
        jpnView.repaint();
    }
    
    public void setEvent() {
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                HocVienJFrame frame = new HocVienJFrame(new HocVien());
                frame.setTitle("Thông tin học viên");
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAdd.setBackground(new Color(0, 200, 83));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                btnAdd.setBackground(new Color(100, 221, 23));
            }
            
        });
        
        btnOFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Học viên");
                
                XSSFRow row = null;
                Cell cell = null;

                // Cell && row
                row = sheet.createRow(3);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("STT");
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Họ và tên");
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Ngày sinh");
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Giới tính");
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Điện thoại");
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("Địa chỉ");
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue("Tình trạng");
                
                List<HocVien> listItem = hocVienService.getList();
                
                if (listItem != null) {
                    FileOutputStream fis = null;
                    
                    try {
                        int size = listItem.size();
                        
                        for (int i = 0; i < size; i++) {
                            HocVien hocVien = listItem.get(i);
                            
                            row = sheet.createRow(4 + i);
                            
                            cell = row.createCell(0, CellType.NUMERIC);
                            cell.setCellValue(i + 1);
                            
                            cell = row.createCell(1, CellType.STRING);
                            cell.setCellValue(hocVien.getName());
                            
                            cell = row.createCell(2, CellType.STRING);
                            cell.setCellValue(hocVien.getBirth().toString());
                            
                            cell = row.createCell(3, CellType.STRING);
                            cell.setCellValue(hocVien.isSex() ? "Nam" : "Nu");
                            
                            cell = row.createCell(4, CellType.STRING);
                            cell.setCellValue(hocVien.getPhone());
                            
                            cell = row.createCell(5, CellType.STRING);
                            cell.setCellValue(hocVien.getAddress());
                            
                            cell = row.createCell(6, CellType.STRING);
                            cell.setCellValue(hocVien.isActive() ? "T" : "F");
                        }

                        // Sava file
                        File file = new File("g://hv.xlsx");
                        fis = new FileOutputStream(file);
                        workbook.write(fis);
                        fis.close();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAdd.setBackground(new Color(0, 200, 83));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                btnAdd.setBackground(new Color(100, 221, 23));
            }
            
        });
    }
}
