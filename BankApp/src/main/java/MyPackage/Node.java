/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyPackage;

/**
 *
 * @author mitas
 */
// Generic Node class to represent an individual node in a linked list
public class Node<T> {

    T data;               // Holds the data of the node
    Node<T> nextNode;     // Reference to the next node in the list

    // Constructor creates a node that refers to the specified object and sets nextNode to null
    public Node(T object) {
        this(object, null);  // Call the two-argument constructor with nextNode as null
    } // End of one-argument constructor

    // Constructor creates a node that refers to the specified object and another node (next node)
    public Node(T object, Node<T> node) {
        data = object;       // Set the node's data
        nextNode = node;     // Set the next node reference
    } // End of two-argument constructor

    // Method to return the data stored in the node
    public T getData() {
        return data;  // Return the data in the node
    }

    // Method to return the next node in the list
    public Node<T> getNext() {
        return nextNode;  // Return the reference to the next node
    }
}

