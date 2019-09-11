/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home.Sales;

import com.safapharma.DataAccessObjects.SalesDAO;
import com.safapharma.ModelObjects.DataWithColumn;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author akshit
 */
public class SalesBackend {
    
    private SalesDAO salesDAO;

    public SalesBackend() {
        salesDAO = new SalesDAO();
    }

    public DataWithColumn setSaleInfoIntoTable(JTable table, DefaultTableModel tableModel) throws Exception {
        DataWithColumn dataWithColumn = salesDAO.getAllSaleDetails();
        return dataWithColumn;
    }

    public DataWithColumn setSaleItemsInfoIntoTable(JTable table, DefaultTableModel tableModel, int id) throws Exception {
        DataWithColumn dataWithColumn = salesDAO.getSaleDetails(id);
        return dataWithColumn;
    }
}
