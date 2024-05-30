package DAO;

public class FoodQueueDAO {
    private int billId;
    private String ftime;

    public FoodQueueDAO(int billId, String ftime) {
        this.billId = billId;
        this.ftime = ftime;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }
}
