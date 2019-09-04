
package com.safapharma.ModelObjects;



public class MedicineLot {
    
    private int id;
    private int medicine_id;
	private String batchNo;
	private String expiry;
	private double rate;
	private String createdAt;
	private String updatedAt;

    public MedicineLot(int id, int medicine_id, String batchNo, String expiry, double rate, String createdAt, String updatedAt) {
        this.id = id;
        this.medicine_id = medicine_id;
		this.batchNo=batchNo;
		this.expiry=expiry;
		this.rate=rate;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
    }

    public MedicineLot() {
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getmedicine_id() {
        return medicine_id;
    }

    public void setmedicine_id(String medicine_id) {
        this.medicine_id = medicine_id;
    }
	
	public String getbatchNo() {
        return batchNo;
    }

    public void setbatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
	
	public String getexpiry() {
        return expiry;
    }

    public void setexpiry(String expiry) {
        this.expiry = expiry;
    }
	
	public double getrate() {
        return rate;
    }

    public void setrate(String rate) {
        this.rate = rate;
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
