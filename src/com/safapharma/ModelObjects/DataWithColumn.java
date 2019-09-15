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
    int lastKey;

    public DataWithColumn() {
        columnNames = new Vector<String>();
        data = new Vector<Vector<Object>>();
        lastKey=0;
    }

    public DataWithColumn(boolean hasId) {
        columnNames = new Vector<String>();
        data = new Vector<Vector<Object>>();
        idData = new Vector<>();
        idSnoHashmap = new HashMap<>();
        lastKey=0;
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
        return getIdBySerialNo(index);
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
    
    public void addData(Vector<Object> dataObj){
        dataObj.add(dataObj);
    }
    public void updateData(int idx,Vector<Object> dataObj){
        data.set(idx, dataObj);
    }
    public void addIdData(int id){
        idSnoHashmap.put(lastKey+1,id);
        lastKey++;
        idData.add(id);
    }
    public HashMap<Integer, Integer> getIdSnoHashmap() {
        return idSnoHashmap;
    }

    public void setIdSnoHashmap(HashMap<Integer, Integer> idSnoHashmap) {
        this.idSnoHashmap = idSnoHashmap;
    }

    public int getLastKey() {
        return lastKey;
    }

    public void setLastKey(int lastKey) {
        this.lastKey = lastKey;
    }
    
    
}
