/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import common.CommonFunction;
import entities.Bill;
import entities.Inventory;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PHUC
 */
public class DataModelStatistic extends AbstractTableModel {

    private String[] header = new String[]{"MÃ HÓA ĐƠN", "NGÀY BÁN", "SÓ LƯỢNG", "TRẠNG THÁI", "TÊN SẢN PHẨM", "GIẢM GIÁ", "THANH TOÁN", "MÔ TẢ"};
    private List<Bill> data;

    public DataModelStatistic(List<Bill> data) {
        this.data = data;
    }

    
    @Override
    public int getRowCount() {
        if(data == null)
            return 0;
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(data == null)
            return "";
        Bill bill = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return bill.getId();
            case 1:
                return CommonFunction.formatDate(bill.getDateSell(), "dd-MM-yyyy");
            case 2:
                return bill.getCount();
            case 3:
                return bill.getStatus();
            case 4:
                return bill.getPhone().getName();
            case 5:
                return bill.getDiscount() != null ? bill.getDiscount().getPercentDiscount() + "%" : "0%";
            case 6:
                return bill.getPayment().getMethod();
            case 7:
                return bill.getDiscription();
            default:
                return "";
        }
    }

}
