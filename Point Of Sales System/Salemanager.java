import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Salemanager {
        ArrayList<SaleData> saledata=new ArrayList<SaleData>();
        ArrayList<SaleItemData> saleitems=new ArrayList<SaleItemData>();
        int saleid=01;
        Scanner scanner = new Scanner(System.in);

    //SAVE SALEDATA TO FILE--------------------------------------------------------------------------------------------------------
    public void savetofileSALEDATA()
    {
        File file = new File("saledatastorage.txt");
        //save data from arraylist to file
        try {
            PrintWriter writer = new PrintWriter(file);
            for(int i=0;i<saledata.size();i++)
            {
                var id = (SaleData)saledata.get(i);
                writer.println(id.getSaleid()+","+id.getCustomerid()+","+id.getCustomername()+","+id.getSaledate()+","+id.getStatus()+","+id.getTotalamount()+","+id.getAmountpaid()+","+id.getRemainingamount());
            }
            writer.close();
        } catch (Exception e) {

            System.out.println("Error: "+e.getMessage());
        }
    }
    //LOAD SALEDATA FROM FILE------------------------------------------------------------------------------------------------------
    public void loadfromfileSALEDATA()
    {
        File file = new File("saledatastorage.txt");
        //load data from file to arraylist
        try {
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine())
            {
                String line = reader.nextLine();
                String[] data = line.split(",");
                int saleid = Integer.parseInt(data[0]);
                int customerid = Integer.parseInt(data[1]);
                String customername = data[2];
                LocalDate saledate = LocalDate.parse(data[3]);
                boolean status = Boolean.parseBoolean(data[4]);
                int totalamount = Integer.parseInt(data[5]);
                int amountpaid = Integer.parseInt(data[6]);
                int remainingamount = Integer.parseInt(data[7]);
                saledata.add(new SaleData(saleid, customerid, customername, saledate, status, totalamount, amountpaid, remainingamount));
            }
            reader.close();
        } catch (Exception e) {

            System.out.println("Error: "+e.getMessage());
        }
    }
        

    //SAVE SALEITEMDATA TO FILE--------------------------------------------------------------------------------------------------------
    public void savetofileSALEITEMDATA()
    {
        File file = new File("saleitemstorage.txt");
        //save data from arraylist to file
        try {
            PrintWriter writer = new PrintWriter(file);
            for(int i=0;i<saleitems.size();i++)
            {
                var id = (SaleItemData)saleitems.get(i);
                writer.println(id.getSaleid()+","+id.getItemId()+","+id.getItemName()+","+id.getQuantity()+","+id.getPrice()+","+id.getSaledate());
            }
            writer.close();
        } catch (Exception e) {

            System.out.println("Error: "+e.getMessage());
        }
    }
    //LOAD SALEITEMDATA FROM FILE------------------------------------------------------------------------------------------------------
    public void loadfromfileSALEITEMDATA()
    {
        File file = new File("saleitemstorage.txt");
        //load data from file to arraylist
        try {
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine())
            {
                String line = reader.nextLine();
                String[] data = line.split(",");
                int saleid = Integer.parseInt(data[0]);
                int itemid = Integer.parseInt(data[1]);
                String itemname = data[2];
                int quantity = Integer.parseInt(data[3]);
                int price = Integer.parseInt(data[4]);
                LocalDate saledate = LocalDate.parse(data[5]);
                saleitems.add(new SaleItemData(saleid, itemid, itemname, quantity, price, saledate));
            }
            reader.close();
        } catch (Exception e) {

            System.out.println("Error: "+e.getMessage());
        }
    }





    public void MakeNewSale(ItemManager im, CustomerManager cm)
    {
        Date date=new Date();
        int customerindex=0;

        //Get Customer ID
        String customersaleid=JOptionPane.showInputDialog("Enter Customer ID: ");
        int customerid=Integer.parseInt(customersaleid);
        //verify customer id
        for(int i=0;i<cm.customers.size();)
        {
            var id=(CustomerData)cm.customers.get(i);
            if(customerid==id.customerId)
            {   
                customerindex=i;
                break;  
            }
            else
                System.out.println("ERROR CUSTOMER NOT FOUND");
                return;
        }

        //check customer sales limit
        if(cm.customers.get(customerindex).salesLimit==0)
        {
            System.out.println("Customer Sales Limit Reached");
            return;
        }
        else
        {
            cm.customers.get(customerindex).salesLimit=cm.customers.get(customerindex).salesLimit-1;
        }


        System.out.println("Date: "+date);
        System.out.println("Customer ID: "+customerid);
        
    
    while(true)
    {
            System.out.println("1. Add New Item");
            System.out.println("2. Remove Item");
            System.out.println("3. End Sale");
        
            String choice=scanner.nextLine();
            if(choice.equals("1"))
            {
                AddNewItem(im, cm, customerindex, customerid);
                CurrentCart();
            }
            else if(choice.equals("2"))
            {
                RemoveItem();
                break;
            }
            else if(choice.equals("3"))
            {
                EndSale(customerindex, cm);
                break;
            }
            else
            {
                System.out.println("Invalid Choice");
            }
        }
    }
//REMOVE ITEM FROM SALEITEMS ARRAYLIST---------------------------------------------------------------------------------------------

    private void RemoveItem()
    {
        //Get Item ID
        String item=JOptionPane.showInputDialog("Enter Item ID: ");
        int itemid=Integer.parseInt(item);
        //Compare itemID with itemID in SaleItemData
        for(int i=0;i<saleitems.size();)
        {
            var id=(SaleItemData)saleitems.get(i);
            if(itemid==id.itemId&&saleitems.get(i).saleid==saleid)
            {
                break;
            }
            else
            {
                System.out.println("Item Not Found");
                return;
            }
        }
        String choice=JOptionPane.showInputDialog("Remove Item Y/N");

       //Remove Item from SaleItems Arraylist
        if(choice.equalsIgnoreCase("Y"))
        {
            for(int i=0;i<saleitems.size();i++)
            {
                var id=(SaleItemData)saleitems.get(i);
                if(itemid==id.itemId&&saleitems.get(i).saleid==saleid)
                {
                    saleitems.remove(i);
                    break;
                }
            }
        }
        else
        {
            System.out.println("Item Not Removed");
        }
    
    
    }

//ADD NEW ITEM---------------------------------------------------------------------------------------------------
    private void AddNewItem(ItemManager im, CustomerManager cm, int customerindex, int customerid)
    {
            int index=0;
        //Get Item ID
        String item=JOptionPane.showInputDialog("Enter Item ID: ");
        int itemid=Integer.parseInt(item);
        //Compare itemID with itemID in itemManager
        for(int i=0;i<im.items.size();i++)
        {
            var id=(ItemData)im.items.get(i);
            if(itemid==id.itemId)
            {   
                index=i;
                id.print();
                break;  
            }
        }
        //Get Quantity
        String quan=JOptionPane.showInputDialog("Enter Quantity: ");
        int quantity=Integer.parseInt(quan);

        String choice=JOptionPane.showInputDialog("Add Item Y/N");

        //Add Item in Cart
        if(choice.equalsIgnoreCase("Y"))
        {
            if(quantity<=im.items.get(index).quantity)
            {
                im.items.get(index).quantity=im.items.get(index).quantity-quantity;
                
                
                //Add Items in SaleItemData array list
            
            

                String itemname=im.items.get(index).descript;
                int price=im.items.get(index).Price;
                price=price*quantity;
                LocalDate saleitemadte;
                saleitemadte=LocalDate.now();
            
                //add item to saleitemdata arraylist  
                SaleItemData sid=new SaleItemData(saleid, itemid, itemname, quantity, price, saleitemadte);
                saleitems.add(sid);
            
            
                System.out.println("Item Added");
            }   
            else 
                System.out.println("Quantity is not available");
        

        }
        else
        {
            System.out.println("Item Not Added");
        }
        
    }

    private void CurrentCart()
    {
        System.out.println("Current Cart");
        

        System.out.println("--------------------------------------------------------------------");
            System.out.println("Item ID\t Description \tQuantity\t Amount");
        System.out.println("--------------------------------------------------------------------");
            for(int i=0;i<saleitems.size();i++)
        {
            var id=(SaleItemData)saleitems.get(i);
            
            System.out.println(id.getItemId()+"\t"+id.getItemName()+"\t"+id.getQuantity()+"\t"+id.getPrice());
        System.out.println("---------------------------------------------------  ---------------");
        }


        int totalamount=0;
        for(int i=0;i<saleitems.size();i++)
        {
            var id=(SaleItemData)saleitems.get(i);
            if(saleid==id.getSaleid())
            {   
                totalamount=totalamount+saleitems.get(i).getPrice();
            }
        }

    }
//END SALE--------------------------------------------------------------------------------------------------------
    private void EndSale(int customerindex, CustomerManager cm)
    {
        
        

        //check all the objects in saleitems array list who's item id match with the current saleid
        //calculate total amount of the current sale
        int totalamount=0;
        for(int i=0;i<saleitems.size();i++)
        {
            var id=(SaleItemData)saleitems.get(i);
            if(saleid==id.getSaleid())
            {   
                totalamount=totalamount+saleitems.get(i).getPrice();
            }
        }

        int amountpaid=0;
        int remainingamount=totalamount;
        //declare other variables to be stored in Saledata arraylist
        
        LocalDate saledate;
        saledate=LocalDate.now();
        boolean status=true;

        String customername=cm.customers.get(customerindex).name;
        int customerid=cm.customers.get(customerindex).customerId;

        //add sale details to saledata arraylist
        SaleData sd=new SaleData(saleid, customerid, customername, saledate, status, totalamount, amountpaid, remainingamount);
        saledata.add(sd);

        //set payable amount in customer arraylist
        cm.customers.get(customerindex).setPayableamount(amountpaid);
        
        //Display Sale Details-----------------------------------------------------

        System.out.println("Sale Details");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Sale ID: "+saleid);
        System.out.println("Date: "+saledate);

        System.out.println("\nCustomer ID: "+customerid);
        System.out.println("Customer Name: "+customername);
        
        //Display Items in Cart
        printfinalizeditemsinCart();

        System.out.println("Total Amount: "+totalamount);

        
        //increment saleid getting ready for new sale
        saleid++;
    System.out.println("Sale Ended");
    }  
    public void printfinalizeditemsinCart()
    {

    System.out.println("----------------------------------------------------------------------------------------------------------");
    System.out.printf("%-10s%-30s%-15s%-15s\n", "Item ID", "Name", "Quantity", "Price");
    System.out.println("----------------------------------------------------------------------------------------------------------");

    for (int i = 0; i < saleitems.size(); i++) {
        SaleItemData item = saleitems.get(i);
        System.out.printf("%-10s%-30s%-15s%-15s\n", i + 1, item.itemName, item.quantity, item.price);
    }

    System.out.println("----------------------------------------------------------------------------------------------------------");

    }

//MAKE PAYMENT----------------------------------------------------------------------------------------------------------------------------------



    public void MakePayment(CustomerManager cm)
    {
        int reqsaleid=Integer.parseInt(JOptionPane.showInputDialog("Enter Sale ID: "));

        //find sale id in SaleData list
        for(int i=0;i<saledata.size();i++)
        {
            var id=(SaleData)saledata.get(i);
            if(reqsaleid==id.getSaleid())
            {   
                //if sale id is found then check if the sale is already paid
                if(id.getStatus()==false)
                {
                    System.out.println("Sale is already paid");
                    break;
                }
                else
                {
                    //display sale data information
                    System.out.println("Customer ID: "+id.getCustomerid());
                    System.out.println("Customer Name: "+id.getCustomername());
                    System.out.println("Total Amount: "+id.getTotalamount());
                    System.out.println("Amount Paid: "+id.getAmountpaid());
                    System.out.println("Remaining Amount: "+id.getRemainingamount());


                    //if sale is not paid then ask for amount to be paid
                    String amount=JOptionPane.showInputDialog("Enter Amount to be Paid: ");
                    int amountpaid=Integer.parseInt(amount);
                    //check if amount is less than remaining amount
                    if(amountpaid<id.getRemainingamount())
                    {
                        //if amount is less than remaining amount then update the remaining amount
                        id.setRemainingamount(id.getRemainingamount()-amountpaid);
                        //update the amount paid
                        id.setAmountpaid(id.getAmountpaid()+amountpaid);
                        //update the payable amount in customer arraylist
                        for(int j=0;j<cm.customers.size();j++)
                        {
                            var cid=(CustomerData)cm.customers.get(j);
                            if(id.getCustomerid()==cid.customerId)
                            {
                                cid.setPayableamount(cid.getPayableamount()+amountpaid);
                                break;
                            }
                        }
                        System.out.println("Amount Paid");
                        break;
                    }
                    else if(amountpaid==id.getRemainingamount())
                    {
                        //if amount is equal to remaining amount then update the remaining amount
                        id.setRemainingamount(id.getRemainingamount()-amountpaid);
                        //update the amount paid
                        id.setAmountpaid(id.getAmountpaid()+amountpaid);
                        //update the payable amount in customer arraylist
                        for(int j=0;j<cm.customers.size();j++)
                        {
                            var cid=(CustomerData)cm.customers.get(j);
                            if(id.getCustomerid()==cid.customerId)
                            {
                                cid.setPayableamount(cid.getPayableamount()+amountpaid);
                                break;
                            }
                        }
                        //set status to false
                        id.setStatus(false);
                        System.out.println("Amount Paid");
                        break;
                    }
                    else
                    {
                        System.out.println("Amount is greater than remaining amount");
                        break;
                    }
                }
            }
        }
    }

//REPORT MENU----------------------------------------------------------------------------------------------------

//Stock In Hand--------------------------------------------------------------------------------------------------

    public void Stock(ItemManager im)
    {
        LocalDate date;
        date=LocalDate.now();

        System.out.println(date);

        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s%-30s%-15s%-15s\n", "Item ID", "Name", "Quantity", "Price");
        System.out.println("----------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < im.items.size(); i++) {
            ItemData item = im.items.get(i);
            System.out.printf("%-10s%-30s%-15s%-15s\n", item.getItemId(), item.getDescript(), item.getQuantity(), item.getPrice());
        }
        System.out.println("----------------------------------------------------------------------------------------------------------");
    }

//CUSTOMER BALANCE---------------------------------------------------------------------------------------------------

    public void CustomerBalance(CustomerManager cm)
    {
        //input Customer ID
        int customerid=Integer.parseInt(JOptionPane.showInputDialog("Enter Customer ID: "));

        //display customer details
        for(int i=0;i<cm.customers.size();i++)
        {
            var cid=(CustomerData)cm.customers.get(i);
            if(customerid==cid.customerId)
            {
                JOptionPane.showMessageDialog(null, "Customer ID: " + cid.customerId +
                "\nCustomer Name: " + cid.name +
                "\nCustomer Address: " + cid.address +
                "\nEmail: " + cid.email +
                "\nCustomer Phone: " + cid.phone +
                "\nCustomer Balance: " + cid.getPayableamount());

                break;
            }
        }
    }
//SALES REPORT-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void printSalesReport()
    {
        int totalamount=0;

        LocalDate date1;
        LocalDate date2;
        
        //input date using joptionpane
        String strdate1=JOptionPane.showInputDialog("Enter Date1: ");
        String strdate2=JOptionPane.showInputDialog("Enter Date2: ");

        //convert string to localdate
        date1=LocalDate.parse(strdate1);
        date2=LocalDate.parse(strdate2);

        System.out.println("Sales Report");
        System.out.println("From: "+date1+"\nTo: "+date2);

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("Item ID\tDescription\t\tQuantity Sold\t\tAmount");
        //check if sale date is between date1 and date2
        for(int i=0;i<saleitems.size();i++)
        {
            var id=(SaleItemData)saleitems.get(i);
            if(id.getSaledate().isAfter(date1) && id.getSaledate().isBefore(date2))
            {
                
            //create arraylist which have the following int id, string name, int quantity, int price
                ArrayList<SaleItemData> saleitem=new ArrayList<SaleItemData>();
                saleitem.add(id);
                //check if the item is already in the arraylist
                for(int j=0;j<saleitem.size();)
                {
                    var id1=(SaleItemData)saleitem.get(j);
                    if(id.getItemId()==id1.getItemId())
                    {
                        //if item is already in the arraylist then update the quantity and price
                        id1.setQuantity(id1.getQuantity()+id.getQuantity());
                        id1.setPrice(id1.getPrice()+id.getPrice());
                        break;
                    }
                    else
                    {
                        //if item is not in the arraylist then add the item to the arraylist
                        saleitem.add(id);
                        break;
                    }
                }
                //display the arraylist
                for(int k=0;k<saleitem.size();k++)
                {
                    var id2=(SaleItemData)saleitem.get(k);
                    System.out.println(id2.getItemId()+"\t"+id2.getItemName()+"\t\t"+id2.getQuantity()+"\t\t\t"+id2.getPrice());
                
                    
                    totalamount=totalamount+id2.getPrice();
                }         
                System.out.println("Total Amount : "+totalamount);
            }
        }

    }


//OUTSTANDING SALES----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void printOutstandingSales()
    {
        LocalDate date1;
        LocalDate date2;
        
        //input date using joptionpane
        String strdate1=JOptionPane.showInputDialog("Enter Date1: ");
        String strdate2=JOptionPane.showInputDialog("Enter Date2: ");

        //convert string to localdate
        date1=LocalDate.parse(strdate1);
        date2=LocalDate.parse(strdate2);

        System.out.println("\nOutstanding Sales Report");
        System.out.println("From: "+date1+"\nTo: "+date2);

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("Sale ID\tCustomer Name\t\tTotal Amount\t\tRemaining Amount");
        //check if sale date is between date1 and date2
        for(int i=0;i<saledata.size();i++)
        {
            var id=(SaleData)saledata.get(i);
            if(id.getSaledate().isAfter(date1) && id.getSaledate().isBefore(date2))
            {
                //display the arraylist
                System.out.println(id.getSaleid()+"\t"+id.getCustomername()+"\t\t"+id.getTotalamount()+"\t\t\t"+id.getRemainingamount());
            }
        }
    }    




















}
