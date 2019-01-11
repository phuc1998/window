/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import charts.GenerateDataSet;
import common.CommonFunction;
import dao.DatabaseAPI;
import java.awt.Panel;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author PHUC
 */
public class ReportForm extends javax.swing.JFrame {

    private List<Date> listDate;
    private int type;
    private int sum;
    private Map<Object, Object> maps;
    private PieDataset dataSet;
    private int data;
    private String name;
    private String label;
    private DefaultCategoryDataset dataset;

    /**
     * Creates new form ReportForm
     */
    public ReportForm(List<Date> listDates, int type) {
        initComponents();
        this.listDate = listDates;
        this.type = type;
        init();
    }

    private void init() {
        sum = DatabaseAPI.getSumCountSell(-1, listDate, type);
        long topSum = 0;//DatabaseAPI.getSumCountSell(5, listDate, type);
        maps = DatabaseAPI.getBestSell(listDate, type);
        List name = (List<String>) maps.get("name");
        List data = (List<Long>) maps.get("data");
        int size = data.size();
        for (int i = 0; i < size; i++) {
            topSum += (long) data.get(i);
        }
        name.add("Sản phẩm khác");
        data.add(sum - topSum);
        dataSet = GenerateDataSet.generateDatasetPieChart(name, data);
        JFreeChart freeChart = ChartFactory.createPieChart("Thống kê sản phẩm bán chạy", dataSet, true, true, false);
        ChartPanel panel = new ChartPanel(freeChart);

        this.name = "DOANH THU";
        switch (type) {
            case 0:
                this.label = CommonFunction.formatDate(this.listDate.get(0), "dd-MM-yyyy");
                break;
            case 1:
                this.label = CommonFunction.formatDate(this.listDate.get(0), "MM-yyyy");
                break;
            case 2:
                this.label = CommonFunction.formatDate(this.listDate.get(0), "yyyy");
                break;
            case 3:
                this.label = "TỪ " + CommonFunction.formatDate(this.listDate.get(0), "dd-MM-yyyy")
                        + " ĐẾN " + CommonFunction.formatDate(this.listDate.get(1), "dd-MM-yyyy");
                break;
            default:
                this.label = "";
        }
        this.txtTitle.setText("BÁO CÁO " + this.label);
        this.data = DatabaseAPI.getRevenue(listDate, type);
        this.dataset = GenerateDataSet.generateDatasetBarChart(this.data, this.name, label);
        JFreeChart freeChartB = ChartFactory.createBarChart("Thống kê doanh thu", "Thời gian", "Đơn vị(VNĐ)", dataset, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot categoryPlot = freeChartB.getCategoryPlot();
        BarRenderer br = (BarRenderer) categoryPlot.getRenderer();
        br.setMaxBarWidth(0.25);
        ChartPanel panelB = new ChartPanel(freeChartB);
        
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                        .addComponent(panelB, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel, 300, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelB, 300, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, 600, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
        SwingUtilities.updateComponentTreeUI(this.jPanel3);
        SwingUtilities.updateComponentTreeUI(this);

    }
    
    
    public JPanel getContentPanel(){
        return this.jPanel3;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        txtTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        txtTitle.setBackground(new java.awt.Color(255, 255, 255));
        txtTitle.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTitle.setText("BÁO CÁO");
        txtTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtTitle)
                .addContainerGap(501, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel txtTitle;
    // End of variables declaration//GEN-END:variables
}
