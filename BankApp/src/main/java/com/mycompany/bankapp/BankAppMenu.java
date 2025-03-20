/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankapp;

/**
 *
 * @author mitas
 */
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class BankAppMenu {
    public static void main(String[] args) throws ParseException {
        BankApp bankApp = new BankApp();
        Scanner scanner = new Scanner(System.in);
       
        boolean exit = false;

        while (!exit) {
           try{ 
            System.out.println("\n===== Bank App Menu =====");
            System.out.println("1. Create Bank Account");
            System.out.println("2. Display a single account details");
            System.out.println("3. Display All Bank Accounts");
            System.out.println("4. Delete Bank Account");
            System.out.println("5. Add Transaction to Account");
            System.out.println("6. Display Transactions for an Account");
            System.out.println("7. Sort Last Four Transactions");
            System.out.println("8. Exit");
            System.out.println("==========================\n");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println();
            
            switch (choice) {
               
                case 1: //Create Bank Account
                    String accountName;
                    while (true) { // Loop until a valid account name is entered
                        System.out.print("Enter Account Name: ");
                        accountName = scanner.nextLine();
                        accountName = accountName.toUpperCase();
                        // Validate that the account name does not contain numbers
                        if (accountName.matches("[a-zA-Z ]+")) {
                            break; // Exit the loop if the name is valid
                        }
                        System.out.println("Account Name should contain only letters. Please try again.");
                    }
                    int accountNumber;
                    while (true) { // Loop until a valid account number is entered
                         System.out.print("Enter Account Number: ");
                        try {
                            accountNumber = scanner.nextInt(); // Try to read an integer
                            // Validate that the account number is greater than 999
                            if (accountNumber > 999) {
                                break; // Exit the loop if the account number is valid
                            } 
                            else {
                                    System.out.println("Account Number must be at least 4 digits and greater than 0. Please try again.");
                            }
                                
                        } 
                        catch (Exception e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                            scanner.next(); // Clear the invalid input
                        }  
                    }
                    double openingBalance;
                    while(true){ // Loop until opening Balance is greater than 0
                        System.out.print("Enter Opening Balance: ");
                        try {
                            openingBalance = scanner.nextDouble();
                        
                            // Validate that the opening balance is greater than 0
                            if (openingBalance >= 0) {
                                break; // Exit the loop if the name is valid
                            }
                            System.out.println("Opening Balance must be greater than 0. Please try again.");
                        }
                        catch (Exception e){
                            System.out.println("Please enter a number greater than '0'");
                            scanner.next();
                        }
                    }
                    scanner.nextLine(); // Consume newline
                    Date openingDate = new Date();
                    BankAccount account = new BankAccount(accountName, accountNumber, openingDate, openingBalance);
                    bankApp.createBankAccount(account);
                    bankApp.displayAccountByNumber(accountNumber);
                    break;
                case 2: //Display a single account details
                    try{
                        System.out.print("Enter Account Number to display account details: ");
                        accountNumber = scanner.nextInt();
                        bankApp.displayAccountByNumber(accountNumber);
                    }
                    catch(Exception e){
                        System.out.println("Plese enter an existing account number ");
                        scanner.next();
                    }
                    break;
                    
                case 3: //Display All Bank Accounts
                    bankApp.displayBankAccount();
                    break;

                case 4: //Delete Bank Account
                    try{
                        System.out.print("Enter Account Number to delete: ");
                        String accountToDelete = scanner.nextLine();
                        bankApp.deleteBankAccount(accountToDelete);
                    }
                    catch(Exception e){
                        System.out.println("Plese enter an existing account number ");
                        scanner.next();
                    }
                    break;

                case 5: //Add Transaction to Account
                    while(true) {
                        try{
                            System.out.print("Enter Account Number: ");
                            int accNumberForTransaction = scanner.nextInt();
                            System.out.print("Enter Transaction Type (Deposit/Withdraw): ");
                            String transactionType = scanner.next();
                            transactionType = transactionType.toLowerCase();
                            System.out.print("Enter Transaction Amount: ");
                            double transactionAmount = scanner.nextDouble();
                            bankApp.addTrToAccount(accNumberForTransaction, transactionType,  transactionAmount);
                            break;
                        }
                        catch(Exception e){
                            System.out.println("Plese use digits to insert the amount");   
                            scanner.next();
                        }     
                    }
                    break;
                case 6: //Display Transactions for an Account
                    
                    System.out.print("Enter Account Number: ");
                    int accountNumForTransactions = scanner.nextInt();
                    bankApp.displayAccountTransactions(accountNumForTransactions);
                    break;

                case 7:
                    System.out.print("Enter Account Number to sort transactions: ");
                    int sortAccountNumber = scanner.nextInt(); // Changed variable name
                    boolean accountFound = false;
                    for (BankAccount acc : bankApp.bankAccounts) {
                        if (acc.getAccountNumber() == sortAccountNumber) {
                            acc.sortLastFourTransactionsByAmount();
                            System.out.println("-----------------------------------------");
                            acc.printTransactions(); // Display sorted transactions
                            accountFound = true;
                            break;
                        }
                    }
                    if (!accountFound) {
                        System.out.println("Account not found.");
                    }
                    break;

                case 8:
                    exit = true;
                    System.out.println("Exiting the application. Goodbye!");
                    break;

                default:
                    System.out.println();
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println();
            }
          }
           catch (Exception e){
               System.out.println("Please follow the menu instructions");
               scanner.next();
            } 
        } 
        scanner.close();
    }
}
