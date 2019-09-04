
package com.safapharma.ModelObjects;



public class AlertType {
    
    private int id;
    private String type;
	private int level;
	private String createdAt;
	private String updatedAt;

    public AlertType(int id, String type, int level, String createdAt, String updatedAt) {
        this.id = id;
        this.type = type;
		this.level=level;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
    }

    public AlertType() {
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String gettype() {
        return type;
    }

    public void setName(String type) {
        this.type = type;
    }
	
	public int getlevel() {
        return level;
    }

    public void setlevel(int level) {
        this.level = level;
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
