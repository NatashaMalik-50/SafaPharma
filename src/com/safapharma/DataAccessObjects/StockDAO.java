/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.DataAccessObjects;

import com.safapharma.Helpers.Constants;
import static com.safapharma.Helpers.Constants.TABLE_STOCK_ENTRY;
import static com.safapharma.Helpers.Constants.TABLE_SUPPLIER;
import static com.safapharma.Helpers.Constants.TABLE_MEDICINE_LOT;
import static com.safapharma.Helpers.Constants.TABLE_MEDICINE;
import com.safapharma.Helpers.DbHelper;
import com.safapharma.ModelObjects.StockEntry;
import com.safapharma.ModelObjects.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author shiva
 */
public class StockDAO {
    public int getStockId(String MedicineName) throws Exception {
    /*select se.id from stock_entry as se join medicine_lot as ml on se.medicine_lot_id = ml.id join medicine as m on ml.medicine_id = m.id
        where m.name like 'a%';*/
        String sql = "select tse.id from" + TABLE_STOCK_ENTRY + "as tse join "+ TABLE_MEDICINE_LOT + "as ml on se.medicine_lot_id = ml.id join " + TABLE_MEDICINE + " as m on ml.medicine_id = m.id where m.name like '"+ MedicineName +"%'";
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, MedicineName);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt(1);
            else
               return Constants.INVALID;
        }
    }
    
    public int getStockDetails(String StockId) throws Exception {
    /*select * from stock_entry as se join medicine_lot as ml on se.medicine_lot_id = ml.id 
        where se.id = id*/
        String sql = "select * from"+ TABLE_STOCK_ENTRY +"as  se join"+ TABLE_MEDICINE_LOT +"as ml on se.medicine_lot_id = ml.id where se.id = ?";
                
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, StockId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt(1);
            else
               return Constants.INVALID;
        }
    }
     public int getStockSupllierWise(String Supplier_Name) throws Exception {
    /*select s.name as supplier_name,m.name as medicine_name from supplier s join supply_bill sb on s.id = sb.supplier_id join stock_entry as se on se.bill_id = sb.id join medicine_lot as ml on ml.id = se.medicine_lot_id join medicine m on m.id = ml.medicine_id
group by s.name; */
        String sql = "select supp.name  from" + TABLE_STOCK_ENTRY + "as tse where tse.id = " + Supplier_Name;
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Supplier_Name);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt(1);
            else
               return Constants.INVALID;
        }
    }
    public int getAllSuppliesLotWise(String StockId) throws Exception {
    /*select s.name,ml.batch_no  from stock_entry as se join medicine_lot as ml on se.medicine_lot_id = ml.id  join supply_bill sb on se.bill_id = sb.id join supplier as s on s.id = sb.supplier_id
group by s.name*/
        String sql = "select supp.name  from" + TABLE_STOCK_ENTRY + "as tse where tse.id = ?";
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, StockId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt(1);
            else
               return Constants.INVALID;
        }
    }
     
   
    
}
