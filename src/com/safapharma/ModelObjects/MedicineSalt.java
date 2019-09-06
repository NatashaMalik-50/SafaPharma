package com.safapharma.ModelObjects;

public class MedicineSalt {

    private int saltId;
    private int medicineId;
    private String createdAt;
    private String updatedAt;

    public MedicineSalt(int saltId, int medicineId, String createdAt, String updatedAt) {
        this.saltId = saltId;
        this.medicineId = medicineId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public MedicineSalt() {
    }

    public int getSaltId() {
        return saltId;
    }

    public void setSaltId(int saltId) {
        this.saltId = saltId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
