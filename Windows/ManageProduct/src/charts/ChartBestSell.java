/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charts;

import dao.DatabaseAPI;
import java.awt.Panel;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author PHUC
 */
public class ChartBestSell extends javax.swing.JDialog {

    private List<Date> listDate;
    private int type;
    private int sum;
    private Map<Object, Object> maps;
    private PieDataset dataSet;

    /**
     * Creates new form ChartBestSell
     */
    public ChartBestSell(java.awt.Frame parent, boolean modal, List<Date> listDates, int type) {
        super(parent, modal);
        this.listDate = listDates;
        this.type = type;
        initComponents();
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
           topSum += (long)data.get(i);
        }
        name.add("Sản phẩm khác");
        data.add(sum - topSum);
        dataSet = GenerateDataSet.generateDatasetPieChart(name, data);
        JFreeChart freeChart = ChartFactory.createPieChart("Thống kê sản phẩm bán chạy", dataSet, true, true, false);
        ChartPanel panel = new ChartPanel(freeChart);
        this.setContentPane(panel);
        SwingUtilities.updateComponentTreeUI(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlChart = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout pnlChartLayout = new javax.swing.GroupLayout(pnlChart);
        pnlChart.setLayout(pnlChartLayout);
        pnlChartLayout.setHorizontalGroup(
            pnlChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );
        pnlChartLayout.setVerticalGroup(
            pnlChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 375, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlChart;
    // End of variables declaration//GEN-END:variables
}