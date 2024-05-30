package DAO;

public class Deliver {
    private int deliveryId, billId;
    private String dname, dphone, dbusy;
    private boolean dworking, dpresent;

    public Deliver(int deliveryId, String dname, String dphone, boolean dworking, boolean dpresent, String dbusy, int billId) {
        this.deliveryId = deliveryId;
        this.dname = dname;
        this.dphone = dphone;
        this.dworking = dworking;
        this.dpresent = dpresent;
        this.dbusy = dbusy;
        this.billId = billId;
    }


    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDphone() {
        return dphone;
    }

    public void setDphone(String dphone) {
        this.dphone = dphone;
    }

    public String getDbusy() {
        return dbusy;
    }

    public void setDbusy(String dbusy) {
        this.dbusy = dbusy;
    }

    public boolean isDworking() {
        return dworking;
    }

    public void setDworking(boolean dworking) {
        this.dworking = dworking;
    }

    public boolean isDpresent() {
        return dpresent;
    }

    public void setDpresent(boolean dpresent) {
        this.dpresent = dpresent;
    }
}
