package DAO;

public class DeliverQueueDAO {
    private int billId;
    private String dtime;


    public DeliverQueueDAO(int billId, String dtime) {
        this.billId = billId;
        this.dtime = dtime;
    }


    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }
}
