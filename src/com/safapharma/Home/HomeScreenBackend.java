/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home;

import com.safapharma.DataAccessObjects.UserDAO;
import com.safapharma.ModelObjects.DataWithColumn;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Natasha Malik
 */
public class HomeScreenBackend {
    
    private UserDAO userDAO;
    
    //DAO objects

    public HomeScreenBackend() {
        userDAO = new UserDAO();
    }
    
    
    
    public DataWithColumn setStockInfoIntoTable(JTable table, DefaultTableModel tableModel) throws Exception{
        DataWithColumn dataWithColumn = userDAO.getUsers(); //get from dao
         return dataWithColumn;        
    }
    
}
