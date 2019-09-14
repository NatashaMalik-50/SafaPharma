/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Customer;

import com.safapharma.DataAccessObjects.CustomerDAO;
import com.safapharma.Helpers.Constants;
import com.safapharma.Helpers.DbHelper;
import com.safapharma.ModelObjects.Customer;
import com.safapharma.ModelObjects.DataWithColumn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static javax.swing.DropMode.INSERT;
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

    public DataWithColumn setCustomerInfoIntoTable() throws Exception {
        DataWithColumn dataWithColumn = customerDAO.getAllCustomerDetails();
        return dataWithColumn;
    }

    public DataWithColumn setSelectedInfoIntoTable(int id) throws Exception {
        DataWithColumn dataWithColumn = customerDAO.getSelectedCustomerDetails(id);
        return dataWithColumn;
    }

    public boolean addCustomer(Customer customer) throws Exception {
        return (customerDAO.createCustomer(customer) != Constants.INVALID) ? true : false;
    }
    public boolean updateCustomer(Customer customer) throws Exception{
        return (customerDAO.UpdateCustomer(customer) != Constants.INVALID) ? true : false;
    }
}
