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
    
     public DataWithColumn getAllSaleDetails() throws Exception {
        final String SQL_QUERY = "SELECT " + TABLE_SALES + ".id, " + TABLE_CUSTOMER +".name, total_quantity, total_amount, discount, final_amount, "+ TABLE_SALES + ".created_at as billed_at from " + TABLE_SALES + " INNER JOIN " + TABLE_CUSTOMER +" On " + TABLE_SALES +".customer_id = "+ TABLE_CUSTOMER + ".id";
        return DAOHelper.getDetailsForTableWithId(SQL_QUERY);
    }
    public DataWithColumn getSaleDetails(int sale_id) throws Exception{
         //final String SQL_QUERY = "SELECT id, rate, quantity, discount, final_amount from " + TABLE_SALE_ENTRY  + " where sale_id = " + sale_id; 
         final String SQL_QUERY = "select sale_entry.id, medicine.name,sale_entry.rate,sale_entry.quantity,sale_entry.discount,sale_entry.final_amount\n" +
"from sale_entry\n" +
"inner join stock_entry\n" +
"on sale_entry.stock_entry_id = stock_entry.id  \n" +
"Inner join medicine_lot\n" +
"on  stock_entry.medicine_lot_id = medicine_lot.id\n" +
"Inner join medicine\n" +
"on medicine_lot.medicine_id = medicine.id\n" +
"where sale_entry.id =" + sale_id;
        return DAOHelper.getDetailsForTableWithId(SQL_QUERY);
    }
}
