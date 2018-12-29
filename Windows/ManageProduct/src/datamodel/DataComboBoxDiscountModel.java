/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import entities.Category;
import entities.Discount;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author PHUC
 */
public class DataComboBoxDiscountModel extends DefaultComboBoxModel{
    private List<Discount> list;

    public DataComboBoxDiscountModel(List<Discount> list) {
        this.list = list;
    }
    
    

    @Override
    public int getSize() {
        return list.size();
    }
    
    @Override
    public Object getElementAt(int index) {
        return list.get(index).getPercentDiscount();
    }
}
