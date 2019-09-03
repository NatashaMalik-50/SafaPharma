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

    public void fetchUserDetail(User user) {
        final String SQL_QUERY = "select * from " + UserTable + " where username = ? ";
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
            } else {
                return;
            }
        } catch (Exception e) {
            return;
        }

    }

}
