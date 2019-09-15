/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.DataAccessObjects;

import com.safapharma.Helpers.Constants;
import static com.safapharma.Helpers.Constants.TABLE_MEDICINE;
import static com.safapharma.Helpers.Constants.TABLE_MEDICINE_LOT;
import static com.safapharma.Helpers.Constants.TABLE_STOCK_ENTRY;
import static com.safapharma.Helpers.Constants.TABLE_SALE_ENTRY;
import static com.safapharma.Helpers.Constants.TABLE_SALES;
import com.safapharma.Helpers.DbHelper;
import com.safapharma.ModelObjects.SaleEntry;
import com.safapharma.ModelObjects.Sales;
import com.safapharma.ModelObjects.DataWithColumn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

/**
 *
 * @author shiva
 */
public class StockDAO {

    public int getStockId(String MedicineName) throws Exception {
        /*select se.id from stock_entry as se join medicine_lot as ml on se.medicine_lot_id = ml.id join medicine as m on ml.medicine_id = m.id
        where m.name like 'a%';*/
        String sql = "select tse.id from" + TABLE_STOCK_ENTRY + "as tse join " + TABLE_MEDICINE_LOT + "as ml on se.medicine_lot_id = ml.id join " + TABLE_MEDICINE + " as m on ml.medicine_id = m.id where m.name like '" + MedicineName + "%'";
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, MedicineName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return Constants.INVALID;
            }
        }
    }

    public int getAmtOfStockById(int StockId) throws Exception {
        /*select se.id from stock_entry as se join medicine_lot as ml on se.medicine_lot_id = ml.id join medicine as m on ml.medicine_id = m.id
        where m.name like 'a%';*/
        String sql = "select tse.amount from" + TABLE_STOCK_ENTRY + "as tse where tse.id = " + StockId;
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, StockId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return Constants.INVALID;
            }
        }
    }
//     public int getStockSupllierWise(String Supplier_Name) throws Exception {
//    /*select s.name as supplier_name,m.name as medicine_name from supplier s join supply_bill sb on s.id = sb.supplier_id join stock_entry as se on se.bill_id = sb.id join medicine_lot as ml on ml.id = se.medicine_lot_id join medicine m on m.id = ml.medicine_id
//group by s.name; */
//        String sql = "select supp.name  from" + TABLE_STOCK_ENTRY + "as tse where tse.id = " + StockId;
//        try (Connection connection = DbHelper.getConnection();) {
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, Supplier_Name);
//            ResultSet resultSet = statement.executeQuery();
//            if(resultSet.next())
//                return resultSet.getInt(1);
//            else
//               return Constants.INVALID;
//        }
//    }
//    public int getAllSuppliesLotWise(String Supplier_Name) throws Exception {
//    /*select s.name,ml.batch_no  from stock_entry as se join medicine_lot as ml on se.medicine_lot_id = ml.id  join supply_bill sb on se.bill_id = sb.id join supplier as s on s.id = sb.supplier_id
//group by s.name*/
//        String sql = "select supp.name  from" + TABLE_STOCK_ENTRY + "as tse where tse.id = " + StockId;
//        try (Connection connection = DbHelper.getConnection();) {
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, Supplier_Name);
//            ResultSet resultSet = statement.executeQuery();
//            if(resultSet.next())
//                return resultSet.getInt(1);
//            else
//               return Constants.INVALID;
//        }
//    }

    public DataWithColumn getAllStockDetails() throws Exception {
        final String SQL_QUERY = "select stock_entry_id, company_name, medicine_name,quantity,rate,batch_no,expiry,supplier_name from " + Constants.VIEW_STOCK_VIEW; //see query
        return DAOHelper.getDetailsForTableWithId(SQL_QUERY);
    }
    public DataWithColumn getBillInfo(int Stockid) throws Exception {
        final String SQL_QUERY = "select s.id, s.name, s.email,s.contact_no,ml.batch_no,ml.expiry,ml.rate,c.name,m_c.name from (select * from stock_entry where stock_entry.id="+ Stockid +") se " +
                                    "inner join supply_bill sb on se.bill_id=sb.id " +
                                    "inner join supplier s on sb.supplier_id=s.id " +
                                    "inner join medicine_lot ml on se.medicine_lot_id=ml.id " +
                                    "inner join medicine m on ml.medicine_id=m.id " +
                                    "inner join company c on m.company_id=c.id " +
                                    "inner join medicine_category m_c on m.category_id=m_c.id; ";
        //System.out.println("in dao");
        return  DAOHelper.getDetailsForTableWithId(SQL_QUERY);
    }
    public DataWithColumn getQuantity(int Stockid) throws Exception {
        final String SQL_QUERY = "select id,quantity from stock_entry where id="+Stockid ;
        
        return  DAOHelper.getDetailsForTableWithId(SQL_QUERY);
    }
    
     public int BillSalesTableInsert(Sales sales) throws Exception{
        final String sql1="Insert into "+ TABLE_SALES + "values(?,?,?,?,?,?)";
        try (Connection con = DbHelper.getConnection();) {
            PreparedStatement pst1 = con.prepareStatement(sql1);
            
            pst1.setInt(1, sales.getCustomerId());
            pst1.setInt(2, sales.getTotalQuantity());
            pst1.setDouble(3, sales.getTotalAmount());
            pst1.setDouble(4, sales.getDiscount());
            pst1.setDouble(5, sales.getFinalAmount());
            pst1.setString(6, sales.getDoctorPrescriptionUrl());
            
            int recordInsertedCount = pst1.executeUpdate();
               return recordInsertedCount;
        }
     }
     public int BillSalesEntryTableInsert(SaleEntry saleEntry) throws Exception{
        final String sql = "Insert into "+ TABLE_SALE_ENTRY + "values(?,?,?,?,?,?,?)";
        try (Connection con = DbHelper.getConnection();) {
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, saleEntry.getSaleId());
            pst.setInt(2, saleEntry.getStockEntryId());
            pst.setDouble(3, saleEntry.getRate());
            pst.setInt(4, saleEntry.getQuantity());
            pst.setDouble(5, saleEntry.getDiscount());
            pst.setDouble(6, saleEntry.getFinalAmount());
            pst.setBoolean(7, true);
            
            int recordUpdatedCount = pst.executeUpdate();
            return recordUpdatedCount;
        }
    }
     public int BillStockUpdate(int quan,int id) throws Exception{
         //final String sql1 = "select quantity from "+TABLE_STOCK_ENTRY +"where id=?";
            //Object ob = DAOHelper.getDetailsForTableWithId(sql1);
         //int calquan=
         final String sql = "Update table"+ TABLE_STOCK_ENTRY +"set quantity ="+ quan +"where id ="+ id; 
        try (Connection con = DbHelper.getConnection();) {
           // PreparedStatement pst = con.prepareStatement(sql);
           
            
            //int recordUpdatedCount = sql.executeUpdate();
            return 1;
        }
     }
     public DataWithColumn getAllExpiredMedicinesDetails() throws Exception{
        final String SQL_QUERY = "select id,company_name,medicine_name,quantity,amount,batch_no,expiry,supplier_name from " + Constants.VIEW_EXPIRED_MEDICINES;
        return DAOHelper.getDetailsForTableWithId(SQL_QUERY);
    }
    
}

