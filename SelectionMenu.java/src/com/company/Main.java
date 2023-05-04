
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniella Alfred
 */
public class menu {
    boolean exit;


    public static void main(String[] args) {
        //menu menu = new menu();
        runmenu();

    }

    public  static void runmenu(){
        printHeader();

        printmenu();
        int choice = getInput();
        performAction(choice);



    }




    private static void printHeader() {
        System.out.println("+-----------------------------------+");
        System.out.println("|            Welcome to our         |");
        System.out.println("|        Retail Management System   |");
        System.out.println("+-----------------------------------+");
    }

    private static void printmenu(){
        System.out.println("\nPlease select your department");
        System.out.println("1- Sales");
        System.out.println("2- Stock management");
        System.out.println("3- Dispatch");
        System.out.println("0- Exit");
    }

    private static void printsalesmenu(){
        System.out.println("\nPlease select operation");
        System.out.println("1- place order");
        System.out.println("2- delete order");
        System.out.println("3- check order");
        System.out.println("4- edit order");
        System.out.println("5- view stock");
        System.out.println("0- Exit");
    }

    private static void printstockmenu(){
        System.out.println("\nPlease select operation");
        System.out.println("1- re-order stock");
        System.out.println("2- view stock warehouse");
    }

    private static void printdispatchmenu(){
        System.out.println("\nPlease select operation");
        System.out.println("1- confirm dispatch orders");
        System.out.println("2- view orders");
        System.out.println("2- find warehouse");
    }

    private static int getInput(){
        Scanner kb = new Scanner(System.in);
        int choice = -1;
        while (choice <0 || choice > 3 ){
            try{
                System.out.print("\nEnter a choice: ");
                choice = Integer.parseInt(kb.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Invalid selection please try again");

            }
        }
        return choice;

    }

    private static void performAction(int choice){
        switch(choice){
            case 0:
                System.out.println("\nThank you for using our system");
                System.out.println("see you soon\n");
                System.exit(0);
            case 1:
                printsalesmenu();
                break;
            case 2:
                printstockmenu();
                break;
            case 3:
                printdispatchmenu();
                break;
            default:
                System.out.println("You have selected an unknown choice");

        }
    }
}
