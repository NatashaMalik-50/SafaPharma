/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.ModelObjects;

import com.safapharma.Helpers.Constants;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

/**
 *
 * @author Natasha Malik
 */
public class DataWithColumn {

    Vector<Vector<Object>> data;
    Vector<String> columnNames;
    Vector<Object> idData;
    HashMap<Integer,Integer> idSnoHashmap;

    public DataWithColumn() {
        columnNames = new Vector<String>();
        data = new Vector<Vector<Object>>();
    }

    public DataWithColumn(boolean hasId) {
        columnNames = new Vector<String>();
        data = new Vector<Vector<Object>>();
        idData = new Vector<>();
        idSnoHashmap = new HashMap<>();
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

    public Vector<Object> getDataOf(int index) {
        return data.get(index);
    }

    public int getIdOf(int index) {
        return (Integer) idData.get(index);
    }

    public void addToIdSNoHashMap(int sNo,int id){
        idSnoHashmap.put(sNo, id);
    }
    
    public int getIdBySerialNo(int sNo){
        if(idSnoHashmap.containsKey(sNo))
                return idSnoHashmap.get(sNo);
        else
            return Constants.INVALID;
    }
    
}
