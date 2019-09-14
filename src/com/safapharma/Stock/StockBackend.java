/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Stock;

import com.safapharma.Home.*;
import com.safapharma.DataAccessObjects.StockDAO;
import com.safapharma.ModelObjects.DataWithColumn;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.safapharma.ModelObjects.StockEntry;
import java.util.Vector;

/**
 *
 * @author Natasha Malik
 */
public class StockBackend {

    private StockDAO stockDAO;

    public StockBackend() {
        stockDAO = new StockDAO();
    }

    public DataWithColumn setStockInfoIntoTable(JTable table, DefaultTableModel tableModel) throws Exception {
        DataWithColumn dataWithColumn = stockDAO.getAllStockDetails();
        return dataWithColumn;
    }
    
    public void insertStockDetails(Vector<Vector> allStock){
        for(Vector object : allStock){
//            System.out.println(object);
            int quantity =(int) object.get(2);
            double discountPercentage = (double)object.get(3);
            double gstPercentage = (double)object.get(4);
            double amount = (double) object.get(5);
            System.out.println(quantity+"\t"+discountPercentage+"\t"+gstPercentage+"\t"+amount);
        }
        
    }

}
