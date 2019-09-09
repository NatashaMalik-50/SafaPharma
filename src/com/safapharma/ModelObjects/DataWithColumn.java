/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.ModelObjects;

import java.util.Vector;

/**
 *
 * @author Natasha Malik
 */
public class DataWithColumn {

    Vector<Vector<Object>> data;
    Vector<String> columnNames;
    Vector<Object> idData;

    public DataWithColumn() {
        columnNames = new Vector<String>();
        data = new Vector<Vector<Object>>();
    }

    public DataWithColumn(boolean hasId) {
        columnNames = new Vector<String>();
        data = new Vector<Vector<Object>>();
        idData = new Vector<>();
    }

    public Vector<Vector<Object>> getData() {
        return data;
    }

    public void setData(Vector<Vector<Object>> data) {
        this.data = data;
    }

    public Vector<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(Vector<String> columnNames) {
        this.columnNames = columnNames;
    }

    public Vector<Object> getIdData() {
        return idData;
    }

    public void setIdData(Vector<Object> idData) {
        this.idData = idData;
    }

}
