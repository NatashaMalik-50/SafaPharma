
package com.safapharma.ModelObjects;



public class SupplyBill {
    
    private int id;
    private String billNo;
	private int supplierId;
	private String billDate;
	private double amount;
	private double discount;
	private double tax;
	private double netAmount;
	private String createdAt;
	private String updatedAt;

    public SupplyBill(int id, String billNo, int supplierId, String billDate, double amount, double discount, double tax, double netAmount, String createdAt, String updatedAt) {
        this.id = id;
        this.billNo = billNo;
		this.supplierId =supplierId;
		this.billDate=billDate;
		this.amount=amount;
		this.discount=discount;
		this.tax=tax;
		this.netAmount=netAmount;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
    }

    public SupplyBill() {
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getbillNo() {
        return billNo;
    }

    public void setbillNo(String billNo) {
        this.billNo = billNo;
    }
	
	public int getsupplierId() {
        return supplierId;
    }

    public void setsupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
	
	public String getbillDate() {
        return billDate;
    }

    public void setbillDate(String billDate) {
        this.billDate = billDate;
    }
	
	public double getamount() {
        return amount;
    }

    public void setamount(double amount) {
        this.amount = amount;
    }
	
	public double getdiscount() {
        return discount;
    }

    public void setdiscount(double discount) {
        this.discount = discount;
    }
	
	public double gettax() {
        return tax;
    }

    public void settax(double tax) {
        this.tax = tax;
    }
	
	public double getnetAmount() {
        return netAmount;
    }

    public void setnetAmount(double netAmount) {
        this.netAmount = netAmount;
    }
	
		public String getcreatedAt() {
        return createdAt;
    }

    public void setcreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
	
	public String getupdatedAt() {
        return updatedAt;
    }

    public void setupdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
       
}
