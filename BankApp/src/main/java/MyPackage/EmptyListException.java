/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyPackage;

/**
 *
 * @author mitas
 */
public class EmptyListException extends RuntimeException {
    //no argument constructor
    public EmptyListException() {
        this("MyLinkedList"); //call other Empty list exception constructor
    }
    //Create one argument constructor
    public EmptyListException(String name){
        super(name + "is empty"); //call super class constructor
    } 
}
    

