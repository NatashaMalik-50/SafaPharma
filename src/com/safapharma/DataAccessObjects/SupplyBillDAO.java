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
 */
public class SupplyBillDAO {
     public DataWithColumn getAllSupplyBillDetails() throws Exception {
        final String SQL_QUERY = "select stock_entry_id, company_name, medicine_name,quantity,rate,batch_no,expiry,supplier_name from " + Constants.VIEW_STOCK_VIEW;
        return DAOHelper.getDetailsForTableWithId(SQL_QUERY);
    }
    

}
