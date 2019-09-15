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
     private int srno;
    public NewUpdateBillFormBackend(int id,int currQuantity,int maxQuatity,int srno) {
        stockDAO = new StockDAO();
        myid=id;
        this.currQuantity=currQuantity;
        this.maxQuantity=maxQuatity;
        this.srno=srno;
    }
    public int getMyId()
    {
        return this.myid;
    }
    public int getSrno()
    {
        return this.srno;
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
