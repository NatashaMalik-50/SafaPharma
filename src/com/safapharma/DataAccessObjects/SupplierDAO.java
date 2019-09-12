/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.DataAccessObjects;

import com.safapharma.Helpers.Constants;
import com.safapharma.Helpers.DbHelper;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.ModelObjects.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author shiva
 */
public class SupplierDAO {

    public int getStockId(String MedicineName) throws Exception {
        /*select se.id from stock_entry as se join medicine_lot as ml on se.medicine_lot_id = ml.id join medicine as m on ml.medicine_id = m.id
        where m.name like 'a%';*/
        String sql = "";
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

    /*
    INSERT INTO `safa_pharma`.`supplier` (`name`, `address`, `contact_no`, `email`) VALUES ('S1', 'B240', '9899638588', 'supplier@gmail.com');
    INSERT INTO supplier (name, address, contact_no, email) VALUES ('S1', 'B240', '9899638588', 'supplier@gmail.com');
     */
    public int saveSupplier(Supplier supplier) throws Exception {
        String sql = "INSERT INTO " + Constants.TABLE_SUPPLIER + " (name, address, contact_no, email) VALUES (?,?,?,?);";
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getAddress());
            statement.setString(3, supplier.getContactNo());
            statement.setString(4, supplier.getEmail());
            int rowsAdded = statement.executeUpdate();
            return (rowsAdded > 0) ? rowsAdded : Constants.INVALID;
        }
    }

    public int updateSupplier(Supplier supplier) throws Exception {
        String sql = "UPDATE supplier SET name=? , address=? , contact_no=? , email=?  where id=?";
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println("Supplier "+supplier);
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getAddress());
            statement.setString(3, supplier.getContactNo());
            statement.setString(4, supplier.getEmail());
            statement.setInt(5, supplier.getId());
            int rowsAdded = statement.executeUpdate();
            return (rowsAdded > 0) ? rowsAdded : Constants.INVALID;
        }
    }

    public DataWithColumn getAllSupplierDetails() throws Exception {
        final String SQL_QUERY = "SELECT id, name,address,contact_no,email FROM " + Constants.TABLE_SUPPLIER;
        return DAOHelper.getDetailsForTableWithId(SQL_QUERY);
    }
}
