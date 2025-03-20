/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankapp;

import java.text.*;
import java.util.*;

/**
 *
 * @author mitas
 */

// Trasaction class with the atributes
public class Transaction {
    private String trType;
    private double trAmount;
    private Date trDate;
    
    
    //Constructor method for transaction class
    public Transaction(String tType, double tAmount, Date tDate){
        trType = tType;
        trAmount = tAmount;
        trDate = tDate;
    }
    
    public String getTrType(){
        return trType;
    }
    
    public double getTrAmount(){
        return trAmount;
    }
    
    public Date getTrDate(){
        return trDate;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return "Type: " + trType + 
               ", Amount: " + trAmount + 
               ", Date: " + dateFormat.format(trDate);
    }

}
