/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home;

import com.safapharma.DataAccessObjects.StockDAO;
import com.safapharma.ModelObjects.DataWithColumn;

/**
 *
 * @author shiva
 */
public class NewBillMedicineEntryViewFormBackend {
     private StockDAO stockDAO;
     private int myid;

    public NewBillMedicineEntryViewFormBackend(int id) {
        stockDAO = new StockDAO();
        myid=id;
    }
    public int getMyId()
    {
        return this.myid;
    }
   
    public DataWithColumn  setBillInfoIntoDailog(int id) throws Exception {
        DataWithColumn dao  = stockDAO.getBillInfo(id);
        //System.out.println(dao.getDataOf(0).get(0)); 
        //System.out.println(dao.getDataOf(0).get(1).toString());
        //System.out.println(dao.getDataOf(0).get(2));
        return dao;
    }

}
