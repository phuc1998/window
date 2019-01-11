/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import common.CommonFunction;
import entities.Bill;
import entities.Discount;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PHUC
 */
public class DataModelDiscount extends AbstractTableModel {

    private String[] header = new String[]{"MÃ ƯU ĐÃI", "PHẦN TRĂM", "NGÀY BẮT ĐẦU", "NGÀY KẾT THÚC"};
    private List<Discount> data;

    public DataModelDiscount(List<Discount> data) {
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
        Discount discount = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return discount.getId();
            case 1:
                return discount.getPercentDiscount();
            case 2:
                return CommonFunction.formatDate(discount.getTimeBegin(), "dd-MM-yyyy");
            case 3:
                return CommonFunction.formatDate(discount.getTimeEnd(), "dd-MM-yyyy");
            default:
                return "";
        }
    }

}

