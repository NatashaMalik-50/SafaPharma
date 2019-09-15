/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.DataAccessObjects;

import com.safapharma.Helpers.Constants;
import com.safapharma.Helpers.DbHelper;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.ModelObjects.Medicine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.safapharma.ModelObjects.MedicineLot;
import com.safapharma.ModelObjects.Supplier;
import java.sql.ResultSet;
import java.util.Vector;

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
     
     public DataWithColumn getAllMedicineLotDetailsBatch(String batchNum) throws Exception {
         System.out.println("Running View medicine lot query");
        final String SQL_QUERY = "select lot_id, company_name,medicine_name,expiry,batch_no,rate from " + Constants.VIEW_MEDICINE_LOT_VIEW + "where batch_no="+batchNum;
        
        return DAOHelper.getDetailsForTableWithId(SQL_QUERY);
    }
     
     public DataWithColumn getAllSupplierFromBillDetails() throws Exception {
        final String SQL_QUERY = "select supplier_id, supplier_name,bill_id from " + Constants.VIEW_SUPPLIER_FROM_BILL_VIEW;
        
        return DAOHelper.getDetailsForTableWithId(SQL_QUERY);
    }
     public Vector<Supplier> getAllSuppliers() throws Exception {
        final String SQL_QUERY = "select id, name from " + Constants.TABLE_SUPPLIER;
        Vector<Supplier> suppliers = new Vector<>();
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(SQL_QUERY);
            ResultSet rs= statement.executeQuery();
            while(rs.next()){
                Supplier supplier = new Supplier();
                supplier.setId(rs.getInt("id"));
                supplier.setName(rs.getString("name"));
                suppliers.add(supplier);
            }
        }
        return suppliers;
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
     
     public Vector<Medicine> getIdNameFromMedicine() throws Exception {
        final String SQL_QUERY = "select id, name from " + Constants.TABLE_MEDICINE;
        Vector<Medicine> medicines = new Vector<>();
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(SQL_QUERY);
            ResultSet rs= statement.executeQuery();
            while(rs.next()){
                Medicine medicine = new Medicine();
                medicine.setId(rs.getInt("id"));
                medicine.setName(rs.getString("name"));
                medicines.add(medicine);
            }
        }
        return medicines;
    }
     
     public DataWithColumn getIdNameFromMedicineName(String name) throws Exception {
        final String SQL_QUERY = "select id from " + Constants.TABLE_MEDICINE+ "where name ="+name ;
        return DAOHelper.getDetailsForTableWithId(SQL_QUERY);
    }
     
     public Vector<MedicineLot> getMedicineLotDetailsId(int id) throws Exception {
        final String SQL_QUERY = "select id,batch_no from " + Constants.TABLE_MEDICINE_LOT+ " where medicine_id=?" ;
        Vector<MedicineLot> medicineLots = new Vector<>();
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(SQL_QUERY);
            statement.setInt(1, id);
            ResultSet rs= statement.executeQuery();
            while(rs.next()){
                MedicineLot medicineLot = new MedicineLot();
                medicineLot.setId(rs.getInt("id"));
                medicineLot.setBatchNo(rs.getString("batch_no"));
                medicineLots.add(medicineLot);
            }
        }
        return medicineLots;
    }

}
