/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bankapp;


import java.util.*;

/**
 *
 * @author mitas
 */

public class BankApp {
    // Instance variables
    public ArrayList<BankAccount> bankAccounts;

    // Constructor method
    public BankApp() {
        bankAccounts = new ArrayList<>();
    }

    // Method to add a bank account
    public void createBankAccount(BankAccount bankAccount) {
        if (existAccountNumber(bankAccount.getAccountNumber())){ // Check if this account number alredy exist 
            
            System.out.println("\nERROR: An account number alredy exist. Please use a differntnt number /n");
            return;
        }
        bankAccounts.add(bankAccount);
        System.out.println("New Bank Account created successfully.");
        System.out.println();
    }
    
    // Method to check if an account number already exists
    public boolean existAccountNumber(int accountNumber) {
        for (BankAccount account : bankAccounts) {
            if (account.getAccountNumber() == accountNumber) {
                return true; // Account number exists
            }
        }
        return false; // Account number does not exist
    }

    // Method to display bank accounts
    public void displayBankAccount() {
        System.out.println("In the Bank System are: " + bankAccounts.size() + " accounts");
        System.out.println();
        for (BankAccount bankAccount : bankAccounts) {
            System.out.println(bankAccount);
        }
        
    }
    
    
    // Displays the details of a specific bank account by its account number
    public void displayAccountByNumber(int accountNumber) {
        // Iterate through the list of bank accounts
        for (BankAccount account : bankAccounts) {
            // Check if the current account's account number matches the provided account number
            if (account.getAccountNumber() == accountNumber) {
                // Print the details of the matching account
                System.out.println("Bank Account Details:");
                System.out.println(account); // Assumes that the BankAccount class overrides the toString method
                return; // Exit the method after displaying the account details
            }
        }
        // Print a message if no account with the specified account number is found
        System.out.println("Account not found.");
    }

    
    // Deletes a bank account from the list if it exists, using the account ID
    public void deleteBankAccount(String accountNumber) {
        BankAccount deleteAccount = null; // Variable to hold the account to be deleted if found

        // Iterate through the list of bank accounts
        for (BankAccount bankAccount : bankAccounts) {
            // Check if the current account's ID matches the provided account number (converted to string for comparison)
            if (String.valueOf(bankAccount.getAccountNumber()).equals(accountNumber)) {
                deleteAccount = bankAccount; // Assign the matching account to deleteAccount
                break; // Exit the loop once the account is found
            }
        }

        // If a matching account was found
        if (deleteAccount != null) {
            // Remove the account from the list
            bankAccounts.remove(deleteAccount);
            // Print a success message
            System.out.println("ACCOUNT with account number: " + accountNumber + " has been successfully removed");
        } else {
            // Print an error message if no matching account was found
            System.out.println("No ACCOUNT with account number: " + accountNumber + " has been found");
        }
    }

    
    // Adds a transaction to a specific bank account.
     public void addTrToAccount(int accountNumber, String type, double amount) {
    // Iterate through the list of bank accounts
    for (BankAccount account : bankAccounts) {
        // Check if the current account matches the provided account number
        if (account.getAccountNumber() == accountNumber) {
            // Add a transaction to the account with the specified type, amount, and current date
            account.addTransaction(type, amount, new Date());
            return; // Exit the method after successfully adding the transaction
        }
    }
    // Print a message if no account with the specified account number is found
    System.out.println("Account not found.");
}


    
    //Displays all transactions for a specific bank account.
    public void displayAccountTransactions(int accountNumber) {
        // Iterate through the list of bank accounts
        for (BankAccount account : bankAccounts) {
            // Check if the current account matches the provided account number
            if (account.getAccountNumber() == accountNumber) {
                // Call the method to print all transactions for this account
                account.printTransactions();
                return; // Exit the method after displaying the transactions
            }
        }
        // Print a message if no account with the specified account number is found
        System.out.println("Account not found.");
    }

}




