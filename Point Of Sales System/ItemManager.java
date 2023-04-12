import javax.swing.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemManager {
    ArrayList<ItemData>items;
    int currentId;
    Scanner scanner = new Scanner(System.in);
    
//LOAD FROM FILE------------------------------------------------------------------------------------------------------
public void loadfromfile() throws FileNotFoundException
{
    File file = new File("itemstorage.txt");
    //load data from file to arraylist
    Scanner scanner = new Scanner(file);
    while(scanner.hasNextLine())
    {
        String line = scanner.nextLine();
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0]);
        String descript = parts[1];
        int price = Integer.parseInt(parts[2]);
        int quantity = Integer.parseInt(parts[3]);
        LocalDate date = LocalDate.parse(parts[4]);
        ItemData idata = new ItemData(id, descript, price, quantity, date);
        items.add(idata);
    }
    scanner.close();
}
//SAVE TO FILE--------------------------------------------------------------------------------------------------------
public void savetofile()
{
    File file = new File("itemstorage.txt");
    //save data from arraylist to file
    try {
        PrintWriter writer = new PrintWriter(file);
        for(int i=0;i<items.size();i++)
        {
            var id = (ItemData)items.get(i);
            writer.println(id.itemId+","+id.descript+","+id.Price+","+id.quantity+","+id.creationDate);
        }
        writer.close();
    } catch (Exception e) {

        System.out.println("Error: "+e.getMessage());
    }
}
  
//CONSTUCTOR-------------------------------------------------------------------------------------------------------
    public ItemManager(ArrayList<ItemData> items, int currentId) {
       this.items = items;
        this.currentId = currentId;
        
        if(this.items==null)
        {
            this.items=new ArrayList<ItemData>();
        }
    
    }

//ADD ITEM---------------------------------------------------------------------------------------------------------
    public void addItem()
    {   
        int lasindex=items.size()-1;
        int newid=items.get(lasindex).itemId+1;

        //get input
        String descript = JOptionPane.showInputDialog(null, "Enter Item Description:");
        String price = JOptionPane.showInputDialog(null, "Enter Item Price:");
        int itemprice = Integer.parseInt(price);
        String quantity = JOptionPane.showInputDialog(null, "Enter Item Quantity:");
        int itemquan = Integer.parseInt(quantity);
        
       
        LocalDate currentDate = LocalDate.now();
        JOptionPane.showMessageDialog(null, "Item Added\nItem ID: "+newid);
      
 
        //store data
                
        ItemData id=new ItemData(newid, descript, itemprice, itemquan,currentDate );
        items.add(id);
        currentId++;
    }
//UPDATE------------------------------------------------------------------------------------------------
    public void updateitem(Integer req){
        for(int i=0;i<items.size();i++)
            {
                var id=(ItemData)items.get(i);
                if(req==id.itemId)
                {   
                    System.out.println("Enter Updated Item Description: ");
                    String descript = scanner.nextLine();
                    
                    System.out.println("Enter Updated Item Price: ");
                    String price = scanner.nextLine();
                    if(price=="")
                            price="0";
                    
                    System.out.println("Enter Updated Item Quantity: ");
                    String quantity = scanner.nextLine();
                    if(quantity=="")
                        quantity="0";
                    
                
                    id.setDescript(descript);
                    id.setPrice(price);
                    id.setQuantity(quantity);        
                    
                    //find the index
                    int index = items.indexOf(id);
                    // get the object at the index that we found
                    ItemData itemdata=items.get(index);
                    
                    // update the object
                    itemdata.setDescript(descript);
                    itemdata.setPrice(price);
                    itemdata.setQuantity(quantity);
                    // set the object back into the arraylist
                    items.set(index, itemdata);
                    
                    JOptionPane.showMessageDialog(null, "Item Updated");

                }
            }
    }
//FIND----------------------------------------------------------------------------------------------------
    public void finditem()
    {

        System.out.println("1. Find Induvidual Item");
        System.out.println("2. Show All Items");
        int choice=scanner.nextInt();


        if(choice==1)
        {
            
            //Find Induvidual Item BY EITHER NAME OR PRICE
            System.out.println("1. Find By Item ID");
            System.out.println("2. Find By Item Description");
            System.out.println("3. Find By Item Price");
            int choice2=scanner.nextInt();
            if(choice2==1)
            {
                System.out.println("Enter Item ID: ");
                int req=scanner.nextInt();
                for(int i=0;i<items.size();i++)
                {
                    var id=(ItemData)items.get(i);
                    if(req==id.itemId)
                    {
                        id.print();
                        System.out.println("--------------------------------------");
                    }
                }
            }
            else if(choice2==2)
            {
                System.out.println("Enter Item Description: ");
                String req=scanner.nextLine();
                for(int i=0;i<items.size();i++)
                {
                    var id=(ItemData)items.get(i);
                    if(req==id.descript)
                    {
                        id.print();
                        System.out.println("--------------------------------------");
                    }
                }
            }
            else if(choice2==3)
            {
                System.out.println("Enter Item Price: ");
                int req=scanner.nextInt();
                for(int i=0;i<items.size();i++)
                {
                    var id=(ItemData)items.get(i);
                    if(req==id.Price)
                    {
                        id.print();
                        System.out.println("--------------------------------------");
                    }
                }
            }
           
        }
        else
        {
            //Show All Items
            for(int i=0;i<items.size();i++)
            {
                var id=(ItemData)items.get(i);
                {
                    id.print();
                }
            }
        }
    }
//DELETE-------------------------------------------------------------------------------------------------
    public void removeitem(Integer req)
    {
        for(int i=0;i<items.size();i++)
        {
            var id=(ItemData)items.get(i);
            if(req==id.itemId)
            {
               items.remove(i);
                JOptionPane.showMessageDialog(null, "Item Deleted");
            }
        }
    }
}