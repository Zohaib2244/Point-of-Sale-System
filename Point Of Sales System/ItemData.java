import java.time.LocalDate;
public class ItemData
{
    int itemId;
    String descript;
    int Price;
    int quantity;
    LocalDate creationDate;
   
    public ItemData(int itemId, String descript, int price, int quantity, LocalDate creationDate) {
        this.itemId = itemId;
        this.descript = descript;
        Price = price;
        this.quantity = quantity;
        this.creationDate = creationDate;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        if(descript!="")
        {
        this.descript = descript;
        }

    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        int intprice = Integer.parseInt(price);
            if(intprice!=0)
                Price=intprice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {

        int intquantity = Integer.parseInt(quantity);
        if(intquantity!=0)
        {
            this.quantity=intquantity;
        }
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    
    public void print() {
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s%-30s%-15s%-15s\n", "Item ID", "Description", "Price", "Quantity");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s%-30s%-15s%-15s\n", itemId, descript, Price, quantity);
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }
}