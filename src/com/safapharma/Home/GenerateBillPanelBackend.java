/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home;
import com.safapharma.ModelObjects.Customer;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.ModelObjects.Sales;
import com.safapharma.ModelObjects.SaleEntry;
import com.safapharma.ModelObjects.StockEntry;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author shiva
 */
public class GenerateBillPanelBackend {
    
    private Customer customer;
    private Sales sales;
    private SaleEntry salesEntry;
    private StockEntry stockEntry;
    

    public GenerateBillPanelBackend(Customer customer,DefaultTableModel tableModel) {
        this.customer =  customer;
        //System.out.println(this.customer);
    }
    public Customer getCustomer()
    {
        return customer;
    }
    
}
