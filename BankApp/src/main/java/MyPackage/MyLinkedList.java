/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyPackage;

/**
 *
 * @author mitas
 */

// Generic linked list class definition
public class MyLinkedList<Obj> {
    private Node<Obj> firstNode;  // Reference to the first node in the list
    private Node<Obj> lastNode;   // Reference to the last node in the list
    private String name;          // Name of the list, used in printing

    // Constructor creates an empty list with the default name "list"
    public MyLinkedList() {
        this("list");  // Call the constructor with a name argument
    } // End of constructor with no argument

    // Constructor that allows specifying the name of the list
    public MyLinkedList(String listName) {
        name = listName;            // Set the name of the list
        firstNode = lastNode = null; // Initialize the list as empty
    } // End of constructor with one argument

    // Getter method to return the first node
    public Node<Obj> getFirstNode() {
        return firstNode;
    }

    // Method to insert a node at the front of the list
    public void insertAtFront(Obj insertItem) {
        if (isEmpty())
            // If the list is empty, create a new node and set it as both the first and last node
            firstNode = lastNode = new Node<Obj>(insertItem);
        else
            // Insert a new node at the front, and update firstNode to point to the new node
            firstNode = new Node<Obj>(insertItem, firstNode);
    } // End of insertAtFront method

    // Method to insert a node at the back of the list
    public void insertAtBack(Obj insertItem) {
        if (isEmpty())
            // If the list is empty, create a new node and set it as both the first and last node
            firstNode = lastNode = new Node<Obj>(insertItem);
        else
            // Add a new node at the back of the list, updating lastNode's nextNode reference
            lastNode = lastNode.nextNode = new Node<Obj>(insertItem);
    } // End of insertAtBack method

    // Method to remove the first node from the list
    public Obj removeFromFront() throws EmptyListException {
        if (isEmpty())  // If the list is empty, throw an exception
            throw new EmptyListException(name);

        Obj removedItem = firstNode.data; // Retrieve the data of the node being removed

        // Update references: if there is only one node, set firstNode and lastNode to null
        if (firstNode == lastNode)
            firstNode = lastNode = null;
        else
            // Otherwise, update firstNode to point to the next node
            firstNode = firstNode.nextNode;

        return removedItem; // Return the data of the removed node
    } // End of removeFromFront method

    // Method to remove the last node from the list
    public Obj removeFromBack() throws EmptyListException {
        if (isEmpty())  // If the list is empty, throw an exception
            throw new EmptyListException(name);

        Obj removedItem = lastNode.data; // Retrieve the data of the node being removed

        // Update references: if there is only one node, set firstNode and lastNode to null
        if (firstNode == lastNode)
            firstNode = lastNode = null;
        else {
            // Locate the second-to-last node
            Node<Obj> current = firstNode;

            // Loop through the list until the second-to-last node is found
            while (current.nextNode != lastNode)
                current = current.nextNode;

            lastNode = current;         // Update lastNode to the second-to-last node
            current.nextNode = null;    // Set its nextNode reference to null
        } // End else statement

        return removedItem; // Return the data of the removed node
    } // End of removeFromBack method

    // Method to check if the list is empty
    public boolean isEmpty() {
        return firstNode == null; // Return true if firstNode is null (i.e., list is empty)
    }
    
    
    // Method to return the size of the list (i.e., the number of elements in the list)
    public int size() {
        int count = 0; // Initialize the counter to 0
        Node<Obj> current = firstNode; // Start from the first node

        // Traverse the list and count the nodes
        while (current != null) {
            count++; // Increment the counter for each node
            current = current.nextNode; // Move to the next node
        }

        return count; // Return the final count
    }


    // Method to print the entire list
    public void print() {
        if (isEmpty()) {
            System.out.printf("Empty %s\n", name); // Print message if list is empty
            return;
        } // End if statement

        System.out.printf("The %s is: ", name); // Print the name of the list
        Node<Obj> current = firstNode;          // Start from the first node

        // While loop to display the list, excluding the last element
        while (current != null) {
            System.out.printf("%s ,", current.data); // Print the data of the current node
            current = current.nextNode; // Move to the next node
        } // End while loop
        System.out.println("\n"); // Print a newline after displaying the list
    } // End of print method
} // End of MyLinkedList class

 
