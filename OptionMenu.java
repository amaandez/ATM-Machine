package com.example.atm;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OptionMenu extends ATMProj {

    Scanner menuInput = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
    HashMap<Integer, Integer> data = new HashMap<>();

    public static HashMap<Integer, Integer> getMapFromCSV(final String filePath) throws IOException {
        HashMap<Integer, Integer> data = new HashMap<>();
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNext()) {
            int userID;
            int pin;
            scanner.useDelimiter("\\D");
            userID = scanner.nextInt();
            pin = scanner.nextInt();
            data.put(userID,pin);
        }
        scanner.close();
        return data;
    }

    public void getLogin() throws IOException {
        int x = 1;
        do {
            try{
                data = getMapFromCSV("/Users/amjacque/git/ATMProj/src/com/example/atm/ATMRepo");
                System.out.println("Welcome to ATM!");
                System.out.print("Enter your customer id: ");
                setCustomerNumber(menuInput.nextInt());

                System.out.print("Enter Your Pin Number: ");
                setPinNumber(menuInput.nextInt());

            } catch(Exception e){
                System.out.println("\n" + "Invalid character(s). Only numbers." + "\n");
                x = 2;
            }
            for(Map.Entry<Integer, Integer> entry: data.entrySet()){
                if (entry.getKey() == getCustomerNumber() && entry.getValue() == getPinNumber()){
                    getAccountType();
                }
            }
            System.out.println(" ");
        } while(x == 1);
    }
    public void getAccountType(){
        System.out.println("Select the Account you want to access: ");
        System.out.println("Type 1 - Checking Account");
        System.out.println("Type 2 - Saving Account");
        System.out.println("Type 3 - Exit");
        System.out.print("Choice: ");
        selection = menuInput.nextInt();

        switch (selection){
            case 1:
                getChecking();
                break;
            case 2:
                getSaving();
                break;
            case 3:
                System.out.println("Thank you for using this ATM, bye.");
                break;
            default:
                System.out.println("\n" + "Invalid Choice." + "\n");
                getAccountType();
        }
    }
    public void getChecking(){
        System.out.println("Checking Account: ");
        System.out.println("Type 1 - View Balance");
        System.out.println("Type 2 - Withdraw Funds");
        System.out.println("Type 3 - Deposit Funds");
        System.out.println("Type 4 - Exit");
        System.out.print("Choice: ");
        selection = menuInput.nextInt();
        switch(selection){
            case 1:
                System.out.println("Checking Account Balance: " + moneyFormat.format(getCheckingBalance()));
                getAccountType();
                break;
            case 2:
                getCheckingWithdrawInput();
                getAccountType();
                break;
            case 3:
                getCheckingDepositInput();
                getAccountType();
                break;
            case 4:
                System.out.println("Thank you for using this ATM, bye.");
                break;
            case 5:
                System.out.println("\n" + "Invalid choice." + "\n");
                getChecking();
        }
    }
    public void getSaving(){
        System.out.println("Saving Account: ");
        System.out.println("Type 1 - View Balance");
        System.out.println("Type 2 - Withdraw Funds");
        System.out.println("Type 3 - Deposit Funds");
        System.out.println("Type 4 - Exit");
        System.out.print("Choice: ");

        selection = menuInput.nextInt();
        switch (selection) {
            case 1:
                System.out.println("Saving Account Balance: " + moneyFormat.format(getSavingBalance()));
                getAccountType();
                break;
            case 2:
                getSavingWithdrawInput();
                getAccountType();
                break;
            case 3:
                getSavingDepositInput();
                getAccountType();
                break;
            case 4:
                System.out.println("Thank you for using this ATM, bye.");
                break;
            default:
                System.out.println("\n" + "Invalid choice." + "\n");
                getSaving();
        }
    }
    int selection;
}
