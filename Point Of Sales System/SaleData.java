import java.time.LocalDate;

public class SaleData {
    int Saleid;

    int customerid;
    String customername;
    
    LocalDate saledate;
    boolean status;

    int totalamount;
    int amountpaid;
    int remainingamount;



 

    public SaleData(int saleid, int customerid, String customername, LocalDate saledate, boolean status, int totalamount, int amountpaid, int remainingamount) {
        Saleid = saleid;
        this.customerid = customerid;
        this.customername = customername;
        this.saledate = saledate;
        this.status = status;
        this.totalamount = totalamount;
        this.amountpaid = amountpaid;
        this.remainingamount = remainingamount;
    }
    
    public int getSaleid() {
        return Saleid;
    }
    public void setSaleid(int saleid) {
        Saleid = saleid;
    }
    public int getCustomerid() {
        return customerid;
    }
    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }
    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public LocalDate getSaledate() {
        return saledate;
    }
    public void setSaledate(LocalDate saledate) {
        this.saledate = saledate;
    }
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(int totalamount) {
        this.totalamount = totalamount;
    }
    public int getAmountpaid() {
        return amountpaid;
    }

    public void setAmountpaid(int amountpaid) {
        this.amountpaid = amountpaid;
    }
    public int getRemainingamount() {
        return remainingamount;
    }

    public void setRemainingamount(int remainingamount) {
        this.remainingamount = remainingamount;
    }

    
}
