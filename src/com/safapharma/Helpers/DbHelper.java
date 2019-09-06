/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Helpers;

import com.safapharma.ModelObjects.DbParameters;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Natasha Malik
 */
public class DbHelper {

    private static volatile DbHelper dbHelper;
    private static DbParameters dbParameters;
    private static Connection conn;

    private DbHelper() throws Exception {
        dbParameters = new DbParametersHelper().getDBModel();
        conn = getNewConnection();
    }

    public static DbHelper getInstance() throws Exception {
        if (dbHelper == null) {
            synchronized (DbHelper.class) {
                if (dbHelper == null) {
                    dbHelper = new DbHelper();
                }
            }
        }
        return dbHelper;
    }

    private static Connection getNewConnection() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "" + dbParameters.getUrl() + ":" + dbParameters.getPort() + "/" + dbParameters.getDbName() + "";
            conn = DriverManager.getConnection(url, dbParameters.getUsername(), dbParameters.getPassword());
            return conn;
        } catch (SQLException ex) {
            System.out.print("exception : " + ex.getMessage());
        }
        return null;
    }

    public static Connection getConnection() throws Exception {
        if (conn != null) {
            if (!conn.isClosed()) {
                return conn;
            } else {
                conn.close();
            }
        }
        getInstance();
        return getNewConnection();

    }

}
