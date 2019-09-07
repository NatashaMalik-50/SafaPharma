/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.DataAccessObjects;

import com.safapharma.Helpers.Constants;
import static com.safapharma.Helpers.Constants.TABLE_CUSTOMER;
import static com.safapharma.Helpers.Constants.TABLE_SALES;
import static com.safapharma.Helpers.Constants.TABLE_SALE_ENTRY;
import com.safapharma.Helpers.DbHelper;
import com.safapharma.ModelObjects.Customer;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.ModelObjects.Sales;
import com.safapharma.ModelObjects.SaleEntry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author akshit
 */
public class CustomerDAO {
        public int getCustomerId(String cName) throws Exception {
        String sql = "SELECT id FROM " + TABLE_CUSTOMER + " where name= ?";
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cName);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt("id");
            else
               return Constants.INVALID;
        }
    }
        
    public void getCustomer(Customer customer) throws Exception{
        final String SQL_QUERY = "select * from " + TABLE_CUSTOMER + " where username = ? ";
        try (Connection con = DbHelper.getConnection();) {
            PreparedStatement pst = con.prepareStatement(SQL_QUERY);
            pst.setString(1, customer.getName());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setContactNo(rs.getString("contact_no"));
                customer.setAddress(rs.getString("address"));
                customer.setEmail(rs.getString("email"));
            }
        }
    }
    public int createCustomer(Customer customer) throws Exception{
        final String sql = "Insert into " + TABLE_CUSTOMER + "values(?,?,?,?,?)";
        try (Connection con = DbHelper.getConnection();) {
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, customer.getName());
            pst.setString(2, customer.getContactNo());
            pst.setString(3, customer.getAddress());
            pst.setString(4, customer.getEmail());
            
            int recordUpdatedCount = pst.executeUpdate();
               return recordUpdatedCount;
        }
    }
    public int UpdateCustomer(Customer customer) throws Exception{
        final String sql = "Update table " + TABLE_CUSTOMER + "set name = ?, contact_no = ?, address = ?, email= ? where id = /";
        try (Connection con = DbHelper.getConnection();) {
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, customer.getName());
            pst.setString(2, customer.getContactNo());
            pst.setString(3, customer.getAddress());
            pst.setString(4, customer.getEmail());
            pst.setInt(5, customer.getId());
            
            int recordUpdatedCount = pst.executeUpdate();
            return recordUpdatedCount;
        }
    }
    // No DELETE Function
    
    public void getCustomerAllSales(String customerId) throws Exception{
      final String sql= "Select * from" + TABLE_SALES +" where customer_id = ?";
      try(Connection con = DbHelper.getConnection();){
          PreparedStatement pst = con.prepareStatement(sql);
          pst.setString(1, customerId);
          ResultSet rs = pst.executeQuery();
          List <Sales> sales = new ArrayList<>();
          while(rs.next()){
               // return list of sales opject.
          }  
      }
    }
    public void getMedicineBought(String customerId) throws Exception{
       final String sql = "Select * from " + TABLE_SALE_ENTRY + " INNER JOIN " + TABLE_SALES + " ON " + TABLE_SALE_ENTRY +".sale_id = " + TABLE_SALES + ".id where Sales.customer_id = ?";
       try (Connection con = DbHelper.getConnection();){
       PreparedStatement pst = con.prepareStatement(sql);
       pst.setString(1, customerId);
       ResultSet rs = pst.executeQuery();
       }   
    }
    
     public DataWithColumn getAllCustomerDetails() throws Exception {
        final String SQL_QUERY = "select company_name, medicine_name,quantity,rate,batch_no,expiry,supplier_name from " + Constants.VIEW_STOCK_VIEW;
        return DAOHelper.getDetailsForTable(SQL_QUERY);
    }
}
