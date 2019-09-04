
package com.safapharma.ModelObjects;



public class Role {
    
    private int id;
    private String name;
	private String createdAt;
	private String updatedAt;

    public Role(int id, String name, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
    }

    public Role() {
    }
        
    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
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
