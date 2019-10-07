/**
 * Code for Exercise 2G
 * This is the EmployeeRecord class.
 */
public class EmployeeRecord{
    private String name;
    private String ssn;
    private Integer salary;

    public EmployeeRecord(String name, String ssn, Integer salary){
        this.name = name;
        this.ssn = ssn;
        this.salary = salary;
    }
    
    public String getName(){
        return this.name;
    }

    public String getSsn(){
        return this.ssn;
    }

    public Integer salary(){
        return this.salary;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSsn(String ssn){
        this.ssn = ssn;
    }

    public void setSalary(Integer salary){
        this.salary = salary;
    }

    @Override
    public String toString(){
        return "Name: " + this.name + "\n" + "SSN: " + this.ssn + "\n" + "Salary: " + this.salary;
    }
}

/**
 * This is the Node class. Nodes make up the LinkedList and
 * contain an EmployeeRecord
 */
public class Node{
    private EmployeeRecord data;
    private Node next;

    public Node(EmployeeRecord data){
        this.data = data;
    }

    public EmployeeRecord getData(){
        return this.data;
    }

    public Node getNext(){
        return this.next;
    }

    public void setData(EmployeeRecord data){
        this.data = data;
    }

    public void setNext(Node next){
        this.next = next;
    }

    public Boolean hasNext(){
        if(this.next != null){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return this.getData().toString();
    }
}

/**
 * This is the LinkedList class. It is composed of
 * Nodes.
 */
public class LinkedList{
    private Node lastNode;

    public LinkedList(Node lastNode){
        this.lastNode = lastNode;
    }

    public void addNode(Node nodeToAdd){
        Node beforeNewNode = this.lastNode.getNext();
        this.lastNode.setNext(nodeToAdd);
        nodeToAdd.setNext(beforeNewNode);
    }

    public void removeNode(Node nodeToRemove, Node afterRemovedNode){
        afterRemovedNode.setNext(nodeToRemove.getNext());
    }

    public Node getLastNode(){
        return this.lastNode;
    }
}