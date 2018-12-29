/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import entities.Inventory;
import entities.Phone;
import java.util.List;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PHUC
 */
public class DataModelDashBoard extends AbstractTableModel {

    private String[] header = new String[]{"MÃ SẢN PHẨM", "HÃNG SẢN XUẤT", "TÊN", "GIÁ"};
    private List<Phone> data;

    public DataModelDashBoard(List<Phone> data) {
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
        Phone phone = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return phone.getId();
            case 1:
                return phone.getCategory().getManufacturer();
            case 2:
                return phone.getName();
            case 3:
                return phone.getPrice() + "Đ";
            default:
                return "";
        }
    }

}
