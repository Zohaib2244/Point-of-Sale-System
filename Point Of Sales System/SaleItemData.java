import java.time.LocalDate;

public class SaleItemData {
    
    int saleid;
    int itemId;

    String itemName;
    int quantity;
    int price;
    LocalDate saledate;
    


    public SaleItemData(int saleid, int itemId, String itemName, int quantity, int price, LocalDate saledate) {
        this.saleid = saleid;
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.saledate = saledate;
    }

    public int getSaleid() {
        return saleid;
    }

    public void setSaleid(int saleid) {
        this.saleid = saleid;
    }
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public LocalDate getSaledate() {
        return saledate;
    }

    public void setSaledate(LocalDate saledate) {
        this.saledate = saledate;
    }
}
