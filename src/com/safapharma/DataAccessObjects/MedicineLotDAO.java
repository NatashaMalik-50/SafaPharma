/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.DataAccessObjects;

import com.safapharma.Helpers.Constants;
import com.safapharma.Helpers.DbHelper;
import com.safapharma.ModelObjects.DataWithColumn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.safapharma.ModelObjects.MedicineLot;

/**
 *
 * @author shiva
 * updated by karan
 */
public class MedicineLotDAO {
    
    
     public DataWithColumn getAllMedicineLotDetails() throws Exception {
         System.out.println("Running View medicine lot query");
        final String SQL_QUERY = "select lot_id, company_name,medicine_name,expiry,batch_no,rate from " + Constants.VIEW_MEDICINE_LOT_VIEW;
        
        return DAOHelper.getDetailsForTableWithId(SQL_QUERY);
    }
     
     public DataWithColumn getAllSupplierFromBillDetails() throws Exception {
        final String SQL_QUERY = "select supplier_id, supplier_name,bill_id from " + Constants.VIEW_SUPPLIER_FROM_BILL_VIEW;
        
        return DAOHelper.getDetailsForTableWithId(SQL_QUERY);
    }
     
     public int insertMedicineLotDetails(MedicineLot medLot) throws Exception {
        //INSERT INTO medicine_lot(medicine_id,batch_no,expiry,rate) VALUES (m_id,ml_batch_no,ml_expiry,ml_rate);                
        String sql = "INSERT INTO " + Constants.TABLE_MEDICINE_LOT + " (medicine_id,batch_no,expiry,rate) VALUES (?,?,?,?);";
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, medLot.getMedicine_id());
            statement.setString(2, medLot.getBatchNo());
            statement.setString(3, medLot.getExpiry());
            statement.setDouble(4, medLot.getRate());
            int rowsAdded = statement.executeUpdate();
            return (rowsAdded > 0) ? rowsAdded : Constants.INVALID;
        }
    }

}
