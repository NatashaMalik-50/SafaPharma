/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.MedicineLot;

import com.safapharma.DataAccessObjects.MedicineLotDAO;
import com.safapharma.Helpers.Constants;
import com.safapharma.ModelObjects.DataWithColumn;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.safapharma.ModelObjects.MedicineLot;

/**
 *
 * @author Karan
 */
public class MedicineLotBackend {
    
    private MedicineLotDAO medicineLotDAO;
    
    public MedicineLotBackend(){
        medicineLotDAO = new MedicineLotDAO();
    }
    
    public DataWithColumn setMedicineIntoTable(JTable table, DefaultTableModel tableModel) throws Exception {
        DataWithColumn dataWithColumn = medicineLotDAO.getAllMedicineLotDetails();        
        return dataWithColumn;
    }
    
    public DataWithColumn getAllMedicineLotDetails() throws Exception {
        DataWithColumn dataWithColumn = medicineLotDAO.getAllMedicineLotDetails();        
        return dataWithColumn;
    }
    
    public DataWithColumn getAllMedicineLotDetailsBatch(String batchNum) throws Exception {
        DataWithColumn dataWithColumn = medicineLotDAO.getAllMedicineLotDetailsBatch(batchNum);        
        return dataWithColumn;
    }
    
    public DataWithColumn getIdNameFromMedicine () throws Exception {
        DataWithColumn dataWithColumn = medicineLotDAO.getIdNameFromMedicine();        
        return dataWithColumn;
    }
    
    
    public DataWithColumn getIdNameFromMedicineName(String name) throws Exception {
        DataWithColumn dataWithColumn = medicineLotDAO.getIdNameFromMedicineName(name);        
        return dataWithColumn;
    }
    
    public DataWithColumn getMedicineLotDetailsId(String id) throws Exception {
        DataWithColumn dataWithColumn = medicineLotDAO.getMedicineLotDetailsId(id);        
        return dataWithColumn;
    }
    
    
    public DataWithColumn getAllSupplierFromBillDetails() throws Exception{
        
        DataWithColumn dataWithColumn = medicineLotDAO.getAllSupplierFromBillDetails();        
        return dataWithColumn;        
    }
    
    public boolean insertMedicineLotDetails(MedicineLot medLot) throws Exception {
        return (medicineLotDAO.insertMedicineLotDetails(medLot) != Constants.INVALID) ? true : false;
    }
}
