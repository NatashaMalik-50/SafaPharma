/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.DataAccessObjects;

import com.safapharma.Helpers.Constants;
import com.safapharma.ModelObjects.DataWithColumn;

/**
 *
 * @author shiva
 * updated by karan
 */
public class MedicineLotDAO {
    
    
     public DataWithColumn getAllMedicineLotDetails() throws Exception {
         System.out.println("Running View medicine lot query");
        final String SQL_QUERY = "select lot_id, company_name,medicine_name,expiry,batch_no,rate from " + Constants.VIEW_MEDICINE_LOT_VIEW;
        
        return DAOHelper.getDetailsForTableWithId(SQL_QUERY);
    }

}
