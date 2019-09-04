
package com.safapharma.ModelObjects;



public class Company {
    
    private int id;
    private String name;
	private String otherDetails;
	private String createdAt;
	private String updatedAt;

    public Company(int id, String name, String otherDetails, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
		this.otherDetails=otherDetails;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
    }

    public Company() {
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	
	public String getotherDetails() {
        return otherDetails;
    }

    public void setotherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
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
