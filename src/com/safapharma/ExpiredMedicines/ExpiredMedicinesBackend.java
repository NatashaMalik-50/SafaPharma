/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.ExpiredMedicines;

import com.safapharma.DataAccessObjects.StockDAO;
import com.safapharma.ModelObjects.DataWithColumn;

/**
 *
 * @author Natasha Malik
 */
public class ExpiredMedicinesBackend {
    StockDAO stockDAO;
    
    public ExpiredMedicinesBackend(){
        this.stockDAO = new StockDAO();
    }
    public DataWithColumn getAllExpiredMedicinesDetails() throws Exception{
        return stockDAO.getAllExpiredMedicinesDetails();
    }
    
}
