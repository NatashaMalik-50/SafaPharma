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
import com.safapharma.ModelObjects.Sales;
import com.safapharma.ModelObjects.SaleEntry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author akshit
 */
public class SalesDAO {
    
    public double getSaleAmount(int saleId) throws Exception {
        String sql = "SELECT final_amount FROM " + TABLE_SALES + " where id= ?";
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, saleId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                return resultSet.getDouble(1);
            else
               return Constants.INVALID;
        }
    }
    
    // Retrieve all sales data and shows in Total sales statistics for the application
    public void getTotalSales(){
        String sql = "SELECT id, Customer.name, Customer.id, final_amount FROM " + TABLE_SALES + " INNER JOIN " + TABLE_CUSTOMER + " ON " + TABLE_SALES + ".customer_id =" + TABLE_CUSTOMER +".id ";
       
       
    }
}
