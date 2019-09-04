
package com.safapharma.ModelObjects;



public class Medicine {
    
    private int id;
    private int companyId;
	private int categoryId;
	private String name;
	private String otherDetails;
	private String createdAt;
	private String updatedAt;	

    public Medicine(int id, int companyId,int categoryId, String name, String otherDetails, String createdAt, String updatedAt) {
        this.id = id;
        this.companyId = companyId;
		this.categoryId=categoryId;
		this.name = name;
		this.otherDetails=otherDetails;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
    }

    public Medicine() {
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getcompanyId() {
        return companyId;
    }

    public void setcompanyId(String companyId) {
        this.companyId = companyId;
    }
	
	public int getcategoryId() {
        return categoryId;
    }

    public void setcategoryId(String categoryId) {
        this.categoryId = categoryId;
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
