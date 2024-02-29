

public class SinglyLinkedList<T> {

    // Inner Node class.
    public class Node<T> {
        // Properties of the Node class.
        // The two properties should be:
        // 1. data (the data stored in the node).
        // 2. next (a reference (also known as a pointer) to the next node.
        private T data;
        private Node<T> next;

        // Constructor of the Node class.
        // The constructor should set the data property of the Node to be the value passed as a parameter.
        // The constructor should set the next property of the Node to be null.
        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    // Properties of the Singly Linked List class.
// The three properties should be:
// 1. size (records the number of nodes in our Singly Linked List)
// 2. head (a reference to the first (also known as the head) node in our Singly Linked List).
// 3. tail (a reference to the last (also known as the tail) node in our Singly Linked List.
    private int size;
    private Node<T> head;
    private Node<T> tail;

    // Constructor.
// Creates a Singly Linked List with a head node.
    public SinglyLinkedList(T value) {
        Node<T> newNode = new Node<>(value);
        this.head = newNode;
        this.tail = newNode;
        this.size = 1;
    }

// Methods

    // size
// returns the size of the Singly Linked List.
    public int size() {
        return size;
    }

    // isEmpty
// returns whether the Singly Linked List is empty.
    public boolean isEmpty() {
        return size == 0;
    }

    // peekFirst
// returns the data stored in the head node.
// throws a run time exception if the Singly Linked List is empty.
    public T peekFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Singly Linked List is empty.");
        }
        return head.data;
    }

    // peekLast
// returns the data stored in the tail node.
// throws a run time exception if the Singly Linked List is empty.
    public T peekLast() {
        if (isEmpty()) {
            throw new RuntimeException("Singly Linked List is empty.");
        }
        return tail.data;
    }

    // addFirst
// Adds a node to the front of the Singly Linked List.
    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    // addLast
// Adds a node to the back of the Singly Linked List.
    public void addLast(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // insertAt
// Inserts a node at a specific index.
// If the index is equal to 0, then we can invoke the addFirst method.
// If the index is equal to size, then we can invoke the addLast method.
// throws an illegal argument exception if the index is invalid.
    public void insert(T value, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Invalid index.");
        }
        if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            addLast(value);
        } else {
            Node<T> newNode = new Node<>(value);
            Node<T> prevNode = getNodeAt(index - 1);
            newNode.next = prevNode.next;
            prevNode.next = newNode;
            size++;
        }
    }

    // removeFirst
// Removes the first (also known as the head node) from the Singly Linked List.
// Throws a run time exception if the Singly Linked List is empty.
// Returns the data stored in the head node.
// If the size of the Singly Linked List becomes 0, need to set the tail to null.
    public T removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Singly Linked List is empty.");
        }
        T removedData = head.data;
        head = head.next;
        size--;
        if (isEmpty()) {
            tail = null;
        }
        return removedData;
    }

    // removeLast
// Removes the last (also known as the tail node) from the Singly Linked List.
// Throws a run time exceptionif the Singly Linked List is empty.
// Returns the data stored in the tail node.
// If the size of the Singly Linked List becomes 0, need to set the tail to null.
    public T removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("Singly Linked List is empty.");
        }
        T removedData;
        if (size == 1) {
            removedData = head.data;
            head = null;
            tail = null;
        } else {
            Node<T> prevNode = getNodeAt(size - 2);
            removedData = tail.data;
            prevNode.next = null;
            tail = prevNode;
        }
        size--;
        return removedData;
    }

    // removeAt
// Removes a node at a specific index.
// Returns the data stored in the removed node.
// If the index is equal to 0, then we can invoke the removeFirst method.
// If the index is equal to size-1, then we can invoke the removeLast method.
// throws an illegal argument exception if the index is invalid.
    public T removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid index.");
        }
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<T> prevNode = getNodeAt(index - 1);
            Node<T> removedNode = prevNode.next;
            T removedData = removedNode.data;
            prevNode.next = removedNode.next;
            removedNode.next = null;
            size--;
            return removedData;
        }
    }

    // contains
// Determines whether the Singly Linked List contains a node that holds data equivalent to the value passed.
// Returns a boolean.
    public boolean contains(T value) {
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (currentNode.data.equals(value)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    // valueAt
// Returns the data held in the node at a specified index.
// Throws an illegal argument exception if the index is invalid.
    public T valueAt(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid index.");
        }
        Node<T> node = getNodeAt(index);
        return node.data;
    }

    // reverse
// Reverses the Singly Linked List.
    public void reverse() {
        Node<T> previousNode = null;
        Node<T> currentNode = head;
        Node<T> nextNode = null;
        while (currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        tail = head;
        head = previousNode;
    }

    // toString
// Returns a String representation of the Singly Linked List.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> currentNode = head;
        while (currentNode != null) {
            sb.append(currentNode.data).append(" -> ");
            currentNode = currentNode.next;
        }
        sb.append("null");
        return sb.toString().trim();
    }

    // Helper method to get the node at a specific index.
    private Node<T> getNodeAt(int index) {
        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

}
