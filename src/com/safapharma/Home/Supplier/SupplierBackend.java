/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home.Supplier;

import com.safapharma.DataAccessObjects.SupplierDAO;
import com.safapharma.Helpers.Constants;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.ModelObjects.Supplier;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Natasha Malik
 */
public class SupplierBackend {

    private SupplierDAO supplierDAO;

    public SupplierBackend() {
        supplierDAO = new SupplierDAO();
    }

    public boolean addSupplier(Supplier supplier) throws Exception {
        return (supplierDAO.saveSuuplier(supplier) != Constants.INVALID) ? true : false;
    }

    public DataWithColumn setSupplierInfoIntoTable(JTable table, DefaultTableModel tableModel) throws Exception {
        DataWithColumn dataWithColumn = supplierDAO.getAllSupplierDetails();
        return dataWithColumn;
    }

}
