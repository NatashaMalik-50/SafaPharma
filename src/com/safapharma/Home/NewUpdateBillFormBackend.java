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
public class NewUpdateBillFormBackend {
        private StockDAO stockDAO;
     private int myid;
     private int currQuantity;
     private int maxQuantity;

    public NewUpdateBillFormBackend(int id,int currQuantity,int maxQuatity) {
        stockDAO = new StockDAO();
        myid=id;
        this.currQuantity=currQuantity;
        this.maxQuantity=maxQuatity;
    }
    public int getMyId()
    {
        return this.myid;
    }
     public int getCurrQuantity()
    {
        return this.currQuantity;
    }
      public int getMaxQuantity()
    {
        return this.maxQuantity;
    }
}
