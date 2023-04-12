public class CustomerData {
    int customerId;
    String name;
    String address;
    String phone;
    String email;
    int salesLimit;
    int noofsales;
    int payableamount;

 

    public CustomerData(int customerId, String name, String address, String phone, String email, int salesLimit,int payableamount) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.salesLimit = salesLimit;
        this.payableamount=payableamount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSalesLimit() {
        return salesLimit;
    }

    public void setSalesLimit(String salesLimitInt) {
        int intsalelimit = Integer.parseInt(salesLimitInt);
        if(intsalelimit!=0)
        this.salesLimit=intsalelimit;
    }

    public int getPayableamount() {
        return payableamount;
    }

    public void setPayableamount(int payableamount) {
        this.payableamount = payableamount;
    }

    public void print()
    {
        System.out.println("Customer ID: "+customerId);
        System.out.println("Customer Name: "+name);
        System.out.println("Customer Address: "+address);
        System.out.println("Customer Phone: "+phone);
        System.out.println("Customer Email: "+email);
        System.out.println("Customer Sales Limit: "+salesLimit);
        System.out.println("Customer Payable Amount: "+payableamount);
    }
}
