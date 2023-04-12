import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class CustomerManager
{
ArrayList<CustomerData>customers;
int currentId;
          Scanner scannerObj = new Scanner(System.in);

//CONSTRUCTOR-------------------------------------------------------------
    public CustomerManager(ArrayList<CustomerData> customers, int currentId) {
    this.customers = customers;
    this.currentId = currentId;
    
    if(this.customers==null)
    {
        this.customers=new ArrayList<CustomerData>();
    }
    }

//LOAD DATA TO FILE---------------------------------------------------------------------------------

    public void loadfromfile() throws FileNotFoundException
    {
        File file = new File("customerstorage.txt");
        //load data from file to arraylist
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            String address = parts[2];
            String phone = parts[3];
            String email = parts[4];
            int salesLimit = Integer.parseInt(parts[5]);
            int payableAmount = Integer.parseInt(parts[6]);
            CustomerData cdata = new CustomerData(id, name, address, phone, email, salesLimit, payableAmount);
            customers.add(cdata);
        }
        scanner.close();
    }


//SAVE DATA TO FILE---------------------------------------------------------------------------------
    public void savetofile()
    {
        File file = new File("customerstorage.txt");
        //save data from arraylist to file
        try {
            PrintWriter writer = new PrintWriter(file);
            for(int i=0;i<customers.size();i++)
            {
                var cd = (CustomerData)customers.get(i);
                writer.println(cd.getCustomerId()+","+cd.getName()+","+cd.getAddress()+","+cd.getPhone()+","+cd.getEmail()+","+cd.getSalesLimit()+","+cd.getPayableamount());
            }
            writer.close();
        } catch (Exception e) {

            System.out.println("Error: "+e.getMessage());
        }
    }
//ADD CUSTOMER--------------------------------------------------------------
    public void addCustomer()
    {

        int lasindex=customers.size()-1;
        int newid=customers.get(lasindex).customerId+1;

        String name = JOptionPane.showInputDialog("Enter Customer Name: ");

        String address = JOptionPane.showInputDialog("Enter Customer Address: ");
        
        String phone = JOptionPane.showInputDialog("Enter Customer Phone: ");
        
        String email = JOptionPane.showInputDialog("Enter Customer Email: ");
        
        int salesLimit = Integer.parseInt(JOptionPane.showInputDialog("Enter Customer Sales Limit: "));
        

    int payableAmount = 0;

  String option=JOptionPane.showInputDialog("Confirm To Add Customer (Y/N)");
        if(option.equals("Y"))
        {
            CustomerData cd=new CustomerData(newid, name, address, phone, email, salesLimit, payableAmount);
            customers.add(cd);
            currentId++;
           JOptionPane.showMessageDialog(null, "Customer Added \nCustomer ID : "+cd.getCustomerId());
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Customer Not Added");
        }
    }
//UPDATE CUSTOMER-----------------------------------------------------------
    public void updateCustomer(int reqid)
    {
        int index=-1;
        for(int i=0;i<customers.size();i++)
        {
            if(customers.get(i).getCustomerId()==reqid)
            {
                index=i;
                break;
            }
        }
        if(index==-1)
        {
            JOptionPane.showMessageDialog(null, "Customer Not Found");
        }
        else
        {
            CustomerData cd=customers.get(index);
            cd.print();

                System.out.print("Enter Updated Customer Name: ");
                String name = scannerObj.nextLine();

                System.out.print("Enter Updated Customer Address: ");
                String address = scannerObj.nextLine();

                System.out.print("Enter Updated Customer Phone: ");
                String phone = scannerObj.nextLine();

                System.out.print("Enter Updated Customer Email: ");
                String email = scannerObj.nextLine();

                System.out.print("Enter UpdatedCustomer Sales Limit: ");
                String salesLimit = scannerObj.nextLine();


            String option=JOptionPane.showInputDialog("Confirm To Save Customer(Y/N)");
            if(option=="Y")
            {
                cd.setName(name);
                cd.setAddress(address);
                cd.setPhone(phone);
                cd.setEmail(email);
                cd.setSalesLimit(salesLimit);
                JOptionPane.showMessageDialog(null, "Customer Updated");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Customer Not Updated");
            }
        }
    }
//FIND CUSTOMER-------------------------------------------------------------
    public void findCustomer(int reqid)
    {    

        int index=-1;
        for(int i=0;i<customers.size();i++)
        {
            if(customers.get(i).getCustomerId()==reqid)
            {
                index=i;
                break;
            }
        }
        
        if(index==-1)
        {
            JOptionPane.showMessageDialog(null, "Customer Not Found");
        }
        else
        {
            CustomerData cd=customers.get(index);
            cd.print();
        }
    }


//DELETE CUSTOMER-----------------------------------------------------------------
    public void deleteCustomer(int reqid)
    {
        int index=-1;
        for(int i=0;i<customers.size();i++)
        {
            if(customers.get(i).getCustomerId()==reqid)
            {
                index=i;
                break;
            }
        }
        if(index==-1)
        {
            JOptionPane.showMessageDialog(null, "Customer Not Found");
        }
        else
        {
            CustomerData cd=customers.get(index);
            cd.print();
            String option=JOptionPane.showInputDialog("Confirm To Delete Customer");
            if(option=="yes")
            {
                customers.remove(index);
                JOptionPane.showMessageDialog(null, "Customer Deleted");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Customer Not Deleted");
            }
        }
    }
}