package DAO;

public class LaborDAO {
    private int laborID, billID;
    private String lname, lphone, lbusy;
    private boolean lworking;

    public LaborDAO(int laborID, int billID, String lname, String lphone, String lbusy, boolean lworking) {
        this.laborID = laborID;
        this.billID = billID;
        this.lname = lname;
        this.lphone = lphone;
        this.lbusy = lbusy;
        this.lworking = lworking;
    }


    public int getLaborID() {
        return laborID;
    }

    public void setLaborID(int laborID) {
        this.laborID = laborID;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLphone() {
        return lphone;
    }

    public void setLphone(String lphone) {
        this.lphone = lphone;
    }

    public String getLbusy() {
        return lbusy;
    }

    public void setLbusy(String lbusy) {
        this.lbusy = lbusy;
    }

    public boolean isLworking() {
        return lworking;
    }

    public void setLworking(boolean lworking) {
        this.lworking = lworking;
    }
}
