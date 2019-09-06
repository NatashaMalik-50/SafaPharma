/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.DataAccessObjects;
import com.safapharma.Helpers.Constants;
import com.safapharma.Helpers.DbHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author shiva
 */
public class MedicineDAO {
    public int getMedicineNameBySupplierName(String SupplierName) throws Exception {
    /*select m.name as Medicine_name from medicine as m join medicine_lot as ml on ml.medicine_id = m.id join stock_entry as se on se.medicine_lot_id = ml.id join supply_bill as sb on sb.id = se.bill_id join supplier as s on s.id= sb.supplier_id;
*/
        String sql = "";
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, SupplierName);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt(1);
            else
               return Constants.INVALID;
        }
    }
    public int getMedicineStockInfo(String SupplierName) throws Exception {
    /*	select * from medicine as m join medicine_lot as ml on ml.medicine_id = m.id join stock_entry as se on se.medicine_lot_id = ml.id 
	where m.name = "crocin" and se.quatity>0;
*/
        String sql = "";
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, SupplierName);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt(1);
            else
               return Constants.INVALID;
        }
    }
    public int getMedicineStockInfoOnSupplier(String SupplierName) throws Exception {
    /*	select m.name as Medicine_name from medicine as m join medicine_lot as ml on ml.medicine_id = m.id join stock_entry as se on se.medicine_lot_id = ml.id join supply_bill as sb on sb.id = se.bill_id join supplier as s on s.id= sb.supplier_id
where s.name = "shivam" and se.quantity>0;;
*/
        String sql = "";
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, SupplierName);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt(1);
            else
               return Constants.INVALID;
        }
    }
    
}
