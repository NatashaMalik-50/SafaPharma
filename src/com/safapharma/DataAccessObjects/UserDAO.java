/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.DataAccessObjects;

import com.safapharma.Helpers.DbHelper;
import com.safapharma.ModelObjects.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Natasha Malik
 */
public class UserDAO {

    final String UserTable = "user";

    public boolean isUserNameEnteredValid(String userName) throws Exception {
        String sql = "SELECT * FROM " + UserTable + " where username= ?";
        try (Connection connection = DbHelper.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    public String fetchPassword(String userName) throws Exception {
        String password = "";
        String sql = "SELECT password FROM " + UserTable + " where username= ?";
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
    
    public User fetchUserDetail(User u) {
        final String SQL_QUERY = "select * from " + UserTable + " where username = ? ";
        try (Connection con = DbHelper.getConnection();) {
            
                PreparedStatement pst = con.prepareStatement(SQL_QUERY);
            pst.setString(1, u.getUsername());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("contact_no"), rs.getString("address"), rs.getString("email"), rs.getString("date_of_birth"));
                return user;
            } else {
                return new User();
            }
        } catch (Exception e) {
            return new User();
        }

    }

}
