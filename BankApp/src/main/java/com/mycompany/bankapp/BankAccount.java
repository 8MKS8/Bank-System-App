/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankapp;


import MyPackage.*;
import java.text.*; // import text package to change from a type to another easely
import java.util.*; // import all the utility package to manipulate with Date format


/**
 *
 * @author mitas
 */

//Create variables inside the bankAccount class
public class BankAccount {
    private String AccountName;
    private int AccountNumber;
    private Date OpeningDate;
    private double CurrentBalance;
    private MyLinkedList<Transaction> transactions;
    
   
    //Create constructor method
    public BankAccount(String aName, int aNumber, Date opDate, double curBalance){
        AccountName = aName;
        AccountNumber = aNumber;
        OpeningDate = opDate;
        CurrentBalance = curBalance;
        transactions = new MyLinkedList<>();   
    }
    
    public String getAccountNume(){
        return AccountName;
    }
    
    public int getAccountNumber(){
        return AccountNumber;
    }
    
    public Date getOpeningDate(){
        return OpeningDate;
    }
    
    public double getCurrentBalance(){
        return CurrentBalance;
    }
    
    public MyLinkedList<Transaction> getTransaction(){
        return transactions;
    }
    
    //Create a method to print Account details
    @Override
    public String toString(){
        SimpleDateFormat trDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        return
            "Account Name: " + AccountName + "\n" +
            "Account Number: " + AccountNumber + "\n" +
            "Opening Date: " + trDateFormat.format(OpeningDate) + "\n" +
            "Your Current Balance: " + CurrentBalance + " £" + "\n";
    }  
    
    
        
     /*This method adds a transaction to the account's transaction history.
     * It updates the current balance based on the transaction type and amount,
     * and ensures that withdrawals do not exceed the available balance.
     */
    public void addTransaction(String tType, double tAmount, Date tDate) {
        // Check if the transaction type is "withdraw" and if the withdrawal amount exceeds the current balance.
        if (tType.equals("withdraw") && tAmount > CurrentBalance) {
            System.out.println("Insufficient Balance"); // Notify the user of insufficient funds.
            return; // Exit the method to prevent further execution.
        }

        // Handle the case when the transaction type is "deposit".
        if (tType.equals("deposit")) {
            CurrentBalance += tAmount; // Increase the current balance by the deposit amount.
            System.out.println("Your current balance is: " + getCurrentBalance() + " £");
        } 
        // Handle the case when the transaction type is "withdraw".
        else if (tType.equals("withdraw")) {
            CurrentBalance -= tAmount; // Decrease the current balance by the withdrawal amount.
            System.out.println("Your current balance is: " + CurrentBalance + " £");
        } 
        // Handle invalid transaction types.
        else {
            System.out.println("Unknown type of transaction. Please choose 'Deposit' or 'Withdraw'.");
            return; // Exit the method if the transaction type is invalid.
        }

        // Create a new transaction object and insert it at the front of the transaction history list.
        transactions.insertAtFront(new Transaction(tType, tAmount, tDate));
        if (transactions.size() > 4){
            transactions.removeFromBack();
        }
        System.out.println("Transaction added successfully."); // Confirm the transaction has been added.
}

      
     /*This method prints all the transactions associated with the account.
     * It iterates through the transaction history and displays each transaction.
     * If there are no transactions, it notifies the user.
     */
    public void printTransactions() {
        // Check if the transaction history is empty.
        if (transactions.isEmpty()) {
            System.out.println("No transactions available."); // Notify the user that there are no transactions.
            return; // Exit the method as there's nothing to print.
        }

        // Print the account number header for the transaction list.
        System.out.println("Transactions for account " + AccountNumber + ":");

        // Get the first node in the transaction history linked list.
        Node<Transaction> current = transactions.getFirstNode();

        // Iterate through the linked list of transactions.
        while (current != null) {
            // Print the data of the current transaction node.
            System.out.println(current.getData());
            // Move to the next node in the list.
            current = current.getNext();
        }
    }
    
        
    /* This method retrieves the last four transactions from the transaction history,
    * adds dummy transactions if there are fewer than four, sorts them by amount using MergeSort,
    * and then displays the sorted transactions.
    */
    public void sortLastFourTransactionsByAmount() {
        // Convert the linked list to an ArrayList to simplify extraction of transactions.
        ArrayList<Transaction> transactionList = new ArrayList<>();
        Node<Transaction> current = transactions.getFirstNode();
        while (current != null) {
            transactionList.add(current.getData()); // Add each transaction to the ArrayList.
            current = current.getNext(); // Move to the next node in the linked list.
        }

        // Determine the size of the transaction list.
        int size = transactionList.size();
        List<Transaction> lastFourTransactions;

        // If there are fewer than four transactions, add dummy transactions with amount 0.
        if (size < 4) {
            lastFourTransactions = new ArrayList<>(transactionList); // Copy existing transactions to the new list.
            while (lastFourTransactions.size() < 4) {
                // Add a dummy transaction with type "Deposit", amount 0, and the current date.
                lastFourTransactions.add(new Transaction("Deposit", 0, new Date()));
            }
        } else {
            // Extract the last four transactions from the list.
            lastFourTransactions = transactionList.subList(size - 4, size);
        }

        // Convert the extracted transactions into an array for sorting.
        Transaction[] transactionArray = lastFourTransactions.toArray(new Transaction[0]);

        // Sort the transaction array by amount using MergeSort.
        mergeSort(transactionArray);

        // Display the sorted transactions.
        System.out.println("Last transactions sorted by amount:");
        for (Transaction t : transactionArray) {
            System.out.println(t); // Print each transaction.
        }
    }
    
    /* Sorts an array of `Transaction` objects using the MergeSort algorithm.
    * This is a recursive divide-and-conquer algorithm that splits the array into halves,
    * sorts each half, and then merges them back together in sorted order
    */
    private void mergeSort(Transaction[] array) {
        // Get the length of the input array.
        int inputLength = array.length;

        // Base case: if the array has fewer than two elements, it's already sorted.
        if (inputLength < 2) {
            return;
        }

        // Find the midpoint of the array to divide it into two halves.
        int midIndex = inputLength / 2;

        // Create arrays for the left and right halves.
        Transaction[] leftHalf = new Transaction[midIndex];
        Transaction[] rightHalf = new Transaction[inputLength - midIndex];

        // Copy the left half of the array into `leftHalf`.
        System.arraycopy(array, 0, leftHalf, 0, midIndex);

        // Copy the right half of the array into `rightHalf`.
        System.arraycopy(array, midIndex, rightHalf, 0, inputLength - midIndex);

        // Recursively sort the left and right halves.
        mergeSort(leftHalf);
        mergeSort(rightHalf);

        // Merge the sorted halves back into the original array.
        merge(array, leftHalf, rightHalf);
    }
  
    
    /* Merges two sorted subarrays (`leftHalf` and `rightHalf`) into a single sorted array.
    * This is part of the MergeSort algorithm, which ensures that the final merged array
    * maintains the sorted order of its elements
    */
    private void merge(Transaction[] array, Transaction[] leftHalf, Transaction[] rightHalf) {
        // Get the sizes of the left and right subarrays.
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;

        // Initialize pointers for traversing the leftHalf, rightHalf, and the main array.
        int i = 0, j = 0, k = 0;

        // Merge elements from both halves into the original array in sorted order.
        while (i < leftSize && j < rightSize) {
            // Compare the transaction amounts from both halves and choose the smaller one.
            if (leftHalf[i].getTrAmount() <= rightHalf[j].getTrAmount()) {
                array[k] = leftHalf[i]; // Copy from the left half.
                i++; // Move to the next element in the left half.
            } else {
                array[k] = rightHalf[j]; // Copy from the right half.
                j++; // Move to the next element in the right half.
            }
            k++; // Move to the next position in the merged array.
        }

        // Copy any remaining elements from the left half into the original array.
        while (i < leftSize) {
            array[k] = leftHalf[i];
            i++;
            k++;
        }

        // Copy any remaining elements from the right half into the original array.
        while (j < rightSize) {
            array[k] = rightHalf[j];
            j++;
            k++;
        }
    }    
}
