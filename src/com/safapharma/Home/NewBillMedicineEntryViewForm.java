/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safapharma.Home;

import com.safapharma.Main.MainWindow;
import com.safapharma.Home.HomeScreenPanel;
import com.safapharma.ModelObjects.DataWithColumn;
import com.safapharma.Templates.DialogForm;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 *
 * @author shiva
 */
public class NewBillMedicineEntryViewForm extends DialogForm
{
    private HomeScreenPanel homeScreenPanel;
    private MainWindow manager;
    private final NewBillMedicineEntryViewFormBackend newBillInfoViewFormBackend;
    private DataWithColumn BillData;
    private JLabel supplier_name,supplier_email,supplier_cno,batch_no,exp,rate,company_name,category_name;
    private JLabel supplier_name1,supplier_email1,supplier_cno1,batch_no1,exp1,rate1,company_name1,category_name1;
    
    public NewBillMedicineEntryViewForm(MainWindow manager, HomeScreenPanel homeScreenPanel, int id) {
        this.manager = manager;
        this.homeScreenPanel = homeScreenPanel;
        newBillInfoViewFormBackend = new NewBillMedicineEntryViewFormBackend(id);
        initUI();        
        //System.out.println(id);
        //addListeners();
    }
    @Override
    protected void deleteScreen() {
        manager.deleteNewStockViewForm();
    }

    private void initUI() {
        getFormLabel().setText("Medicine Details");
        try{
            loadBillData();
        }
        catch(Exception e)
        {
            System.out.println(e);
            //System.out.println("HEllo");
        } 
        getFormPanel().setLayout(new GridLayout(0,2));
        supplier_name = new JLabel();
        supplier_name1 = new JLabel();
        supplier_name.setText("Supplier Name :");
        supplier_name1.setText((String)BillData.getDataOf(0).get(1));
        
        
        supplier_email= new JLabel();
        supplier_email1= new JLabel();
        supplier_email.setText("Supplier Email :");
        supplier_email1.setText((String)BillData.getDataOf(0).get(2));
        
        supplier_cno = new JLabel();
        supplier_cno1 = new JLabel();
        supplier_cno.setText("Supplier Contact Number :");
        supplier_cno1.setText(BillData.getDataOf(0).get(3).toString());
        
        
        batch_no = new JLabel();  
        batch_no1 = new JLabel();        
        batch_no.setText("Medicine Batch Number :");
        batch_no1.setText((String)BillData.getDataOf(0).get(4));
        
        exp = new JLabel();        
        exp1 = new JLabel();
        exp.setText("Expiry Date :");
        exp1.setText(BillData.getDataOf(0).get(5).toString());
        
        rate = new JLabel();
        rate1 = new JLabel();
        rate.setText("Medicine Rate :");
        rate1.setText(BillData.getDataOf(0).get(6).toString());
        
        company_name = new JLabel();
        company_name1 = new JLabel();        
        company_name.setText("Medicine Company Name :");
        company_name1.setText((String)BillData.getDataOf(0).get(7));
        
        
        category_name = new JLabel();       
        category_name1 = new JLabel();
        category_name.setText("Medicine Category :");
        category_name1.setText((String)BillData.getDataOf(0).get(8));
        
        getFormPanel().add(supplier_name);
        getFormPanel().add(supplier_name1);
        
        getFormPanel().add(supplier_email);
        getFormPanel().add(supplier_email1);
        
        getFormPanel().add(supplier_cno);
        getFormPanel().add(supplier_cno1);
        
        getFormPanel().add(batch_no);
        getFormPanel().add(batch_no1);
        
        getFormPanel().add(exp);
        getFormPanel().add(exp1);
        
        getFormPanel().add(rate);
        getFormPanel().add(rate1);
        
        getFormPanel().add(company_name);
        getFormPanel().add(company_name1);
        
        getFormPanel().add(category_name);
        getFormPanel().add(category_name1);
    }
     private void loadBillData() throws Exception {
         int id = newBillInfoViewFormBackend.getMyId();
         
        BillData = newBillInfoViewFormBackend.setBillInfoIntoDailog(id);
        //tableModel.setDataVector(null, stockData.getColumnNames());
        //tableModel.setColumnIdentifiers(column);
        //billEntriesTable.setModel(tableModel);
         //System.out.println(id);
         //System.out.println(BillData);
    }

}
