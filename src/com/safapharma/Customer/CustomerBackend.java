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

    public DataWithColumn setCustomerInfoIntoTable(JTable table, DefaultTableModel tableModel) throws Exception {
        DataWithColumn dataWithColumn = customerDAO.getAllCustomerDetails();
        return dataWithColumn;
    }
    
    public DataWithColumn setSelectedInfoIntoTable(int id) throws Exception {
        DataWithColumn dataWithColumn = customerDAO.getSelectedCustomerDetails(id);
        return dataWithColumn;
    }

    boolean addCustomer(Customer customer) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        String sql="INSERT INTO customer (name, contact_no, address, email) VALUES (?, ?, ?, ?)";
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,customer.getName());
            statement.setString(2,customer.getContactNo());
            statement.setString(3,customer.getAddress());
            statement.setString(4,customer.getEmail());
            ResultSet resultSet = statement.executeQuery();
            int i=statement.executeUpdate();
            if(i!=0)
                return true;
            else
               return false;
        }
        catch(Exception e)
        {
            System.out.println("Exception Occured");
        }
return true;
    }
    
    
  }
