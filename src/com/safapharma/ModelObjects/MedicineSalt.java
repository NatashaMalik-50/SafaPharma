
package com.safapharma.ModelObjects;



public class MedicineSalt {
    
    private int saltId;
    private int medicineId;
	private String createdAt;
	private String updatedAt;

    public MedicineSalt(int saltId, int medicineId, String createdAt, String updatedAt) {
        this.saltId = saltId;
        this.medicineId = medicineId;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
    }

    public MedicineSalt() {
    }
        
    public int getsaltId() {
        return saltId;
    }

    public void setsaltId(int saltId) {
        this.saltId = saltId;
    }

    public int getmedicineId() {
        return medicineId;
    }

    public void setmedicineId(int medicineId) {
        this.medicineId = medicineId;
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
