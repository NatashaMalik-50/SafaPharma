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
    
    public DataWithColumn getAllSupplierFromBillDetails() throws Exception{
        
        DataWithColumn dataWithColumn = medicineLotDAO.getAllSupplierFromBillDetails();        
        return dataWithColumn;        
    }
    
    public boolean insertMedicineLotDetails(MedicineLot medLot) throws Exception {
        return (medicineLotDAO.insertMedicineLotDetails(medLot) != Constants.INVALID) ? true : false;
    }
}
