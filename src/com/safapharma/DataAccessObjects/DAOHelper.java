/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.DataAccessObjects;

import com.safapharma.Helpers.DbHelper;
import com.safapharma.ModelObjects.DataWithColumn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

/**
 *
 * @author Natasha Malik
 */
public class DAOHelper {

    public static DataWithColumn getDetailsForTable(final String SQL_QUERY) throws Exception {
        try (Connection con = DbHelper.getConnection();) {
            PreparedStatement pst = con.prepareStatement(SQL_QUERY);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            DataWithColumn dataWithColumn = new DataWithColumn();

            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                dataWithColumn.getColumnNames().add(metaData.getColumnName(i));
            }
            //serial no.
            int sNo = 1;
            while (rs.next()) {
                Vector<Object> row = new Vector<Object>();
                row.add(sNo);
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                dataWithColumn.getData().add(row);
                sNo++;
            }
            return dataWithColumn;
        }
    }

    public static DataWithColumn getDetailsForTableWithId(final String SQL_QUERY) throws Exception {
        try (Connection con = DbHelper.getConnection();) {
            PreparedStatement pst = con.prepareStatement(SQL_QUERY);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            DataWithColumn dataWithColumn = new DataWithColumn();

            int columnCount = metaData.getColumnCount();
            dataWithColumn.getColumnNames().add("S.No");
            //leaving first id column
            for (int i = 2; i <= columnCount; i++) {
                dataWithColumn.getColumnNames().add(metaData.getColumnName(i));
            }
            //serial no.
            int sNo = 1;
            Vector<Object> idRow = new Vector<Object>();
            while (rs.next()) {
                Vector<Object> row = new Vector<Object>();
                row.add(sNo);
                for (int i = 1; i <= columnCount; i++) {
                    if (i != 1) {
                        row.add(rs.getObject(i));
                    } else {
                        //first id column
                        idRow.add(rs.getObject(i));
                    }
                }
                dataWithColumn.getData().add(row);
                sNo++;
            }
            dataWithColumn.setIdData(idRow);
            return dataWithColumn;
        }
    }
}
