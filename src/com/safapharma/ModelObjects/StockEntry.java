
package com.safapharma.ModelObjects;



public class StockEntry {
    
    private int id;
    private int billId;
	private int medicineLotId;
	private int quantity;
	private double discountPercentage;
	private double gstPercentage;
	private double amount;
	private String createdAt;
	private String updatedAt;
	

    public StockEntry(int id, int billId, int medicineLotId, int quantity, double discountPercentage, double gstPercentage, double amount, String createdAt, String updatedAt) {
        this.id = id;
        this.billId = billId;
		this.medicineLotId=medicineLotId;
		this.rate=rate;
		this.quantity=quantity;
		this.discountPercentage=discountPercentage;
		this.gstPercentage=gstPercentage;
		this.amount=amount;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
    }

    public StockEntry() {
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getbillId() {
        return billId;
    }

    public void setbillId(int billId) {
        this.billId = billId;
    }
	
	public int getmedicineLotId() {
        return medicineLotId;
    }

    public void setmedicineLotId(int medicineLotId) {
        this.medicineLotId = medicineLotId;
    }
	
	public int getquantity() {
        return quantity;
    }

    public void setquantity(double quantity) {
        this.quantity = quantity;
    }
	
	public double getdiscountPercentage() {
        return discountPercentage;
    }

    public void setdiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
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
