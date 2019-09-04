
package com.safapharma.ModelObjects;



public class SaleEntry {
    
    private int id;
    private int saleId;
	private int stockEntryId;
	private double rate;
	private int quantity;
	private double discount;
	private double finalAmount;
	private boolean isReturnable;
	private String createdAt;
	private String updatedAt;
	

    public SaleEntry(int id, int saleId, int stockEntryId, double rate, int quantity, double discount, double finalAmount, boolean isReturnable, String createdAt, String updatedAt) {
        this.id = id;
        this.saleId = saleId;
		this.stockEntryId=stockEntryId;
		this.rate=rate;
		this.quantity=quantity;
		this.discount=discount;
		this.finalAmount=finalAmount;
		this.isReturnable=isReturnable;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
    }

    public SaleEntry() {
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getsaleId() {
        return saleId;
    }

    public void setsaleId(int saleId) {
        this.saleId = saleId;
    }
	
	public int getstockEntryId() {
        return stockEntryId;
    }

    public void setstockEntryId(int stockEntryId) {
        this.stockEntryId = stockEntryId;
    }
	
	public double getrate() {
        return rate;
    }

    public void setrate(double rate) {
        this.rate = rate;
    }
	
	public int getquantity() {
        return quantity;
    }

    public void setquantity(double quantity) {
        this.quantity = quantity;
    }
	
	public double getdiscount() {
        return discount;
    }

    public void setdiscount(double discount) {
        this.discount = discount;
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
