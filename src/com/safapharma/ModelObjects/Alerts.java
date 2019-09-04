
package com.safapharma.ModelObjects;




public class Alerts {
    
    private int id;
    private int stockEntryId;
    private int alertType;
	private String startDate;
    private int repeat;
	private String endDate;
	private String createdAt;
	private String updatedAt;

    public Alerts(int id, int stockEntryId, int alertType, String startDate,int repeat, String endDate, String createdAt, String updatedAt) {
        this.id = id;
        this.stockEntryId = stockEntryId;
        this.alertType = alertType;
		this.startDate = startDate;
        this.repeat = repeat;
		this.endDate=endDate;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
    }

    public Alerts() {
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getstockEntryId() {
        return stockEntryId;
    }

    public void setstockEntryId(int stockEntryId) {
        this.stockEntryId = stockEntryId;
    }

	public int getalertType() {
        return alertType;
    }

    public void setalertType(int alertType) {
        this.alertType = alertType;
    }

    public String getstartDate() {
        return startDate;
    }

    public void setContactNo(String startDate) {
        this.startDate = startDate;
    }    

    public int getrepeat() {
        return repeat;
    }

    public void setrepeat(int repeat) {
        this.repeat = repeat;
    }
    
	public String getendDate() {
        return endDate;
    }

    public void setendDate(String endDate) {
        this.endDate = endDate;
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
