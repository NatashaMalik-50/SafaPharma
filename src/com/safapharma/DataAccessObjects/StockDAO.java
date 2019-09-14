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
import com.safapharma.Helpers.DbHelper;
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
    public String setQuantity(int qty,int Stockid) throws Exception {
        final String SQL_QUERY = "update stock_entry set quantity= ? where id= ?";
        try (Connection con = DbHelper.getConnection();) {
           
            
          //  SQL_QUERY.executeUpdate();
              return SQL_QUERY;
        
    }
    
}
    
    public DataWithColumn getAllExpiredMedicinesDetails() throws Exception{
        final String SQL_QUERY = "select id,company_name,medicine_name,quantity,amount,batch_no,expiry,supplier_name from " + Constants.VIEW_EXPIRED_MEDICINES;
        return DAOHelper.getDetailsForTableWithId(SQL_QUERY);
    }
}
