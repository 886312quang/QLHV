package qlhv.controller;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import qlhv.bean.KhoaHocBean;
import qlhv.bean.LopHocBean;
import qlhv.service.ThongKeService;
import qlhv.service.ThongKeServiceImpl;

/**
 *
 * @author PC
 */
public class QuanLyThongKe {

    private ThongKeService thongKeService = null;

    public QuanLyThongKe() {
        thongKeService = new ThongKeServiceImpl();
    }

    public void setDateToChart1(JPanel jpnItem) {
        List<LopHocBean> listItem = thongKeService.getListByClass();

        if (listItem != null) {
            DefaultCategoryDataset dateSet = new DefaultCategoryDataset();
            for (LopHocBean item : listItem) {
                dateSet.addValue(item.getCountStudent(), "Student", item.getDateStart());
            }
            JFreeChart chart = ChartFactory.createBarChart("THỐNG KÊ SỐ LƯỢNG HỌC VIÊN ĐĂNG KÝ",
                    "THỜI GIAN", "SỐ LƯỢNG", dateSet);
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 300));

            jpnItem.removeAll();
            jpnItem.setLayout(new CardLayout());
            jpnItem.add(chartPanel);
            jpnItem.validate();
            jpnItem.repaint();
        }
    }

    public void setDateToChart2(JPanel jpnItem) {
        List<KhoaHocBean> listItem = thongKeService.getListByCourse();

        if (listItem != null) {
            TaskSeriesCollection ts = new TaskSeriesCollection();

            TaskSeries taskSeries;
            Task task;

            for (KhoaHocBean item : listItem) {
                taskSeries = new TaskSeries(item.getSource());
                task = new Task(item.getSource(), item.getDateStart(), item.getDateEnd());
                taskSeries.add(task);
                ts.add(taskSeries);
            }

            JFreeChart chart = ChartFactory.createGanttChart("THỐNG KÊ TÌNH TRẠNG KHÓA HỌC", "Khóa học", "Thời gian", ts);

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 300));

            jpnItem.removeAll();
            jpnItem.setLayout(new CardLayout());
            jpnItem.add(chartPanel);
            jpnItem.validate();
            jpnItem.repaint();

        }
    }
}
