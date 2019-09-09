/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home;

import com.safapharma.DataAccessObjects.StockDAO;
import com.safapharma.ModelObjects.DataWithColumn;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author shiva
 */
public class NewStockViewFormBackend {
    private StockDAO stockDAO;
    public NewStockViewFormBackend() {
        stockDAO = new StockDAO();
    }

    public DataWithColumn setStockInfoIntoTable(JTable table, DefaultTableModel tableModel) throws Exception {
        DataWithColumn dataWithColumn = stockDAO.getAllStockDetails();
        return dataWithColumn;
        //return null;
    }
}
