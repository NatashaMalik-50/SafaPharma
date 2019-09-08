/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.DataAccessObjects;

import com.safapharma.Helpers.Constants;
import static com.safapharma.Helpers.Constants.TABLE_USERS;
import com.safapharma.Helpers.DbHelper;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.ModelObjects.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

/**
 *
 * @author Natasha Malik
 */
public class UserDAO {

    public int getUserId(String userName) throws Exception {
        String sql = "SELECT id FROM " + TABLE_USERS + " where username= ?";
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return Constants.INVALID;
            }
        }
    }

    public String getPassword(String userName) throws Exception {
        String password = "";
        String sql = "SELECT password FROM " + TABLE_USERS + " where username= ?";
        try (Connection connection = DbHelper.getConnection();) {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                password = resultSet.getString(1);
            }
        }
        return password;
    }

    public void getUser(User user) throws Exception {
        final String SQL_QUERY = "select * from " + TABLE_USERS + " where username = ? ";
        try (Connection con = DbHelper.getConnection();) {
            PreparedStatement pst = con.prepareStatement(SQL_QUERY);
            pst.setString(1, user.getUsername());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setContactNo(rs.getString("contact_no"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                user.setDateOfBirth(rs.getString("date_of_birth"));
            }
        }
    }

    public DataWithColumn getUsers() throws Exception {
        final String SQL_QUERY = "select * from " + TABLE_USERS;
        try (Connection con = DbHelper.getConnection();) {
            PreparedStatement pst = con.prepareStatement(SQL_QUERY);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            DataWithColumn dataWithColumn = new DataWithColumn();
            // Names of columns
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                dataWithColumn.getColumnNames().add(metaData.getColumnName(i));
            }

            // Data of the table
            while (rs.next()) {
                Vector<Object> row = new Vector<Object>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                dataWithColumn.getData().add(row);
            }
            return dataWithColumn;
        }
    }
}
