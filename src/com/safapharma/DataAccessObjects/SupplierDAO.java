/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.DataAccessObjects;

import com.safapharma.Helpers.Constants;
import static com.safapharma.Helpers.Constants.TABLE_SUPPLIER;
import com.safapharma.Helpers.DbHelper;
import com.safapharma.ModelObjects.User;
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
            if(resultSet.next())
                return resultSet.getInt(1);
            else
               return Constants.INVALID;
        }
    }
}
