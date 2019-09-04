
package com.safapharma.ModelObjects;



public class MedicineCategory {
    
    private int id;
    private String name;
	private String createdAt;
	private String updatedAt;

    public MedicineCategory(int id, String name, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
    }

    public MedicineCategory() {
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
