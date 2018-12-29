/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import entities.Inventory;
import java.util.List;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PHUC
 */
public class DataModelRepository extends AbstractTableModel {

    private String[] header = new String[]{"Mã kho", "MÃ SẢN PHẨM", "TÊN", "MÀN HÌNH", "CPU", "RAM", "MEMORY", "PIN", "CAMERA TRƯỚC", "CAMERA SAU", "GIÁ", "SỐ LƯỢNG", "NGÀY NHẬP"};
    private List<Inventory> data;

    public DataModelRepository(List<Inventory> data) {
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
        Inventory inventory = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return inventory.getStt();
            case 1:
                return inventory.getPhone().getId();
            case 2:
                return inventory.getPhone().getName();
            case 3:
                return inventory.getPhone().getScreenSize();
            case 4:
                return inventory.getPhone().getCpu();
            case 5:
                return inventory.getPhone().getRam() + "GB";
            case 6:
                return inventory.getPhone().getMemory() + "GB";
            case 7:
                return inventory.getPhone().getPin() + "mAh";
            case 8:
                return inventory.getPhone().getFrontCam() + "MP";
            case 9:
                return inventory.getPhone().getBackCam() + "MP";
            case 10:
                return inventory.getPhone().getPrice() + "Đ";
            case 11:
                return inventory.getCount();
            case 12:
                return inventory.getDateImport();
            default:
                return "";
        }
    }

}
