/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Customer;

import com.safapharma.DataAccessObjects.CustomerDAO;
import com.safapharma.ModelObjects.Customer;
import com.safapharma.ModelObjects.DataWithColumn;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sony
 */
public class CustomerBackend {
    private final CustomerDAO customerDAO;

    public CustomerBackend() {
    customerDAO = new CustomerDAO();
    }

    public DataWithColumn setCustomerInfoIntoTable(JTable table, DefaultTableModel tableModel) throws Exception {
        DataWithColumn dataWithColumn = customerDAO.getAllCustomerDetails();
        return dataWithColumn;
    }

    boolean addCustomer(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  }
