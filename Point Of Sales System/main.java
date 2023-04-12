import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;
class PointOfSales{

private static ItemManager im = new ItemManager(null, 1001);
private static CustomerManager cm= new CustomerManager(null, 2001);
private static Salemanager sm= new Salemanager();
private static Scanner scannerObj = new Scanner(System.in);

private static int mainmenu(){

    System.out.println("\t\t===Main Menu===\t\t\t");
    System.out.println("1. Manage Items");
    System.out.println("2. Manage Customers");
    System.out.println("3. Make New Sale");
    System.out.println("4. Make Payment");
    System.out.println("5. Print Reports");
    System.out.println("6. Exit");

    System.out.print("Choose An Option: ");
    int num = scannerObj.nextInt();
            return num;

    }
//MAIN FUNCTION----------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) throws FileNotFoundException {
        

        im.loadfromfile();
        cm.loadfromfile();
        sm.loadfromfileSALEDATA();
        sm.loadfromfileSALEITEMDATA();

        while(true){

            int choice=mainmenu();


            switch(choice)
            {
                case 1:
                    ItemMenu();
                break;

                case 2: 
                    CustomerMenu();
                break;
                    
                case 3:
                    sm.MakeNewSale(im, cm);
                break;

                case 4:
                    sm.MakePayment(cm);
                break;
                
                case 5:
                    ReportMenu();
                break;

                case 6:
                    im.savetofile();
                    cm.savetofile();
                    sm.savetofileSALEDATA();
                    sm.savetofileSALEITEMDATA();

                    System.exit(0);
                break;    
            }

        }

    }
//ITEM MENU----------------------------------------------------------------------------------------------------------------
    private static void ItemMenu()
    {
            
        while(true)
        {
            System.out.println("\t\t===Item Menu===\t\t\t");
            
            
            System.out.println("1. Add New Item");
            System.out.println("2. Update Item Details");
            System.out.println("3. Find Items");
            System.out.println("4. Remove Existing Item");
            System.out.println("5. Back To Main");
            
            System.out.println("Chose An Option: ");
            int num = scannerObj.nextInt();

            switch(num)
                {
                    case 1:

                        im.addItem();
                    break;

                    case 2: 
                        int updatereq= Integer.parseInt(JOptionPane.showInputDialog("Enter Item ID"));
                        im.updateitem(updatereq);
                    break;
                        
                    case 3:
                        im.finditem();
                    break;

                    case 4:
                        int delreq= Integer.parseInt(JOptionPane.showInputDialog("Enter Item ID"));
                        im.removeitem(delreq);
                    break;
                    
                    case 5:
                        return;
                }
        }
        
    }
//CUSTOMER MENU----------------------------------------------------------------------------------------------------------------
    private static void CustomerMenu()
    {
           
            while(true)
        {
            System.out.println("\t\t===Customer Menu===\t\t\t");
            
            
            System.out.println("1. Add New Customer");
            System.out.println("2. Update Customer Details");
            System.out.println("3. Find Customer");
            System.out.println("4. Remove Existing Customer");
            System.out.println("5. Back To Main");
            
            System.out.println("Chose An Option: ");
            int num = scannerObj.nextInt();

            switch(num)
                {
                    case 1:

                        cm.addCustomer();                    break;

                    case 2: 
                        int reqid= Integer.parseInt(JOptionPane.showInputDialog("Enter Customer ID"));
                        cm.updateCustomer(reqid);
                    break;
                        
                    case 3:
                         reqid= Integer.parseInt(JOptionPane.showInputDialog("Enter Customer ID"));
                        cm.findCustomer(reqid);
                    break;

                    case 4:
                        reqid= Integer.parseInt(JOptionPane.showInputDialog("Enter Customer ID"));
                        cm.deleteCustomer(reqid);
                    break;
                    
                    case 5:
                        return;
                }
        }
        
    }

//REPORT MENU----------------------------------------------------------------------------------------------------------------
    private static void ReportMenu()
    {
           
            while(true)
        {
            System.out.println("\t\t===Report Menu===\t\t\t");
            
            
            System.out.println("1. Stock In Hand");
            System.out.println("2. Customer Balance");
            System.out.println("3. Print Sales Report (by Date)");
            System.out.println("4. Outstanding Sales (by Date)");
            System.out.println("5. Back To Main");
            
            System.out.println("Chose An Option: ");
            int num = scannerObj.nextInt();

            switch(num)
                {
                    case 1:

                        sm.Stock(im);
                    break;

                    case 2: 
                      sm.CustomerBalance(cm);
                    break;
                        
                    case 3:
                        sm.printSalesReport();
                    break;

                    case 4:
                      sm.printOutstandingSales();
                    break;
                    
                    case 5:
                        return;
                }
        }
        
    }


}