
package com.safapharma.ModelObjects;




public class Supplier {
    
    private int id;
    private String name;
    private String address;
	private String contactNo;
    private String email;
	private String createdAt;
	private String updatedAt;

    public Supplier(int id, String name, String address, String contactNo,String email, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.address = address;
		this.contactNo = contactNo;
        this.email = email;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
    }

    public Supplier() {
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

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
