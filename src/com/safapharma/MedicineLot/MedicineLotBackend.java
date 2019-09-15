/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.MedicineLot;

import com.safapharma.DataAccessObjects.MedicineLotDAO;
import com.safapharma.Helpers.Constants;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.ModelObjects.Medicine;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.safapharma.ModelObjects.MedicineLot;
import com.safapharma.ModelObjects.Supplier;
import java.util.Vector;

/**
 *
 * @author Karan
 */
public class MedicineLotBackend {

    private MedicineLotDAO medicineLotDAO;

    public MedicineLotBackend() {
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

    public Vector<Medicine> getIdNameFromMedicine() throws Exception {
        return medicineLotDAO.getIdNameFromMedicine();
    }

    public DataWithColumn getIdNameFromMedicineName(String name) throws Exception {
        DataWithColumn dataWithColumn = medicineLotDAO.getIdNameFromMedicineName(name);
        return dataWithColumn;
    }

    public Vector<MedicineLot> getMedicineLotDetailsId(int id) throws Exception {
       return medicineLotDAO.getMedicineLotDetailsId(id);
    }

    public DataWithColumn getAllSupplierFromBillDetails() throws Exception {

        DataWithColumn dataWithColumn = medicineLotDAO.getAllSupplierFromBillDetails();
        return dataWithColumn;
    }

    public Vector<Supplier> getAllSuppliers() throws Exception {

        return medicineLotDAO.getAllSuppliers();
    }

    public boolean insertMedicineLotDetails(MedicineLot medLot) throws Exception {
        return (medicineLotDAO.insertMedicineLotDetails(medLot) != Constants.INVALID) ? true : false;
    }
}
