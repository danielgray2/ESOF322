/*
  Written by Daniel Gray and Tysen Radovich
*/

import java.util.Scanner;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;

/*
  This is the class that holds the main method. This code will ask the user
  for an input value, and then select a data storage method based off of the
  input. It will then write a short message to a "./data.txt" file with
  information about what data was stored and how.
*/
public class Main{
  public static void main(String[] args) throws IOException{
    Scanner scanner = new Scanner(System.in);
    Integer input;
    Random rand = new Random();
    File file = new File("./data.txt");
    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
    bw.write("We are inside the main method.\n");
    Database database = new Database(bw);
    Boolean invalidInput = true;

    while(invalidInput){

      try{
        System.out.print("Please choose a data storage method\nRelational: 1\nNoSQL: 2\nGraph: 3\nYour input: ");
        input = scanner.nextInt();

        if(input > 0 && input < 4){
          invalidInput = false;  
          database.storeData(rand.nextInt(2500), bw);
          handleData(database, input, rand.nextInt(2500), bw);
        }else{
          System.out.println("That was an invalid input. Please enter 1, 2, or 3.\n");
          scanner.nextLine();
        }

      }catch(IOException exc){
        System.out.println("That is an invalid input. Please enter 1, 2, or 3.\n"); 
        scanner.nextLine();
      }catch(InputMismatchException exc){
        System.out.println("That is an invalid input. Please enter 1, 2, or 3.\n"); 
        scanner.nextLine();
      }
    }

    bw.close();
  }

/*
  This method is responsible for translating the user input into a data storage
  selection.
*/
  private static void handleData(Database database, Integer selection, Integer data, BufferedWriter bw) throws IOException{
    switch(selection){
      case 1:
        bw.write("Creating a new RelationalDb\n");
        database.setStorageMethod(new RelationalDb());
        database.storeData(data, bw);
        break;
      case 2:
        bw.write("Creating a new NoSQLDb\n");
        database.setStorageMethod(new NoSQLDb());
        database.storeData(data, bw);
        break;
      case 3:
        bw.write("Creating a new GraphDb\n");
        database.setStorageMethod(new GraphDb());
        database.storeData(data, bw);
        break;
      default:
        System.out.println("That is not a valid input. Please enter 1, 2, or 3");
    }
  }
}

/*
  This interface is responsible for defining the methods for all storage methods.
  This allows us to implement the Strategy Pattern.
*/
public interface StorageMethod{
  public void store(Integer data, BufferedWriter bw) throws IOException;
}

/*
  Relational database implementation of StorageMethod
*/
public class RelationalDb implements StorageMethod{
  @Override
  public void store(Integer data, BufferedWriter bw) throws IOException{
    bw.write("The following line was stored with table store: ");
    bw.write(data + "\n");
  }

  @Override
  public String toString(){
    return "RelationalDb";
  }
}

/*
  NoSQL implementation of StorageMethod
*/
public class NoSQLDb implements StorageMethod{
  @Override
  public void store(Integer data, BufferedWriter bw) throws IOException{
    bw.write("The following line was stored with document store: ");
    bw.write(data + "\n");
  }

  @Override
  public String toString(){
    return "NoSQLDb";
  }
}

/*
  GraphDB implementation of StorageMethod
*/
public class GraphDb implements StorageMethod{
  @Override
  public void store(Integer data, BufferedWriter bw) throws IOException{
    bw.write("The following line was stored with node store (default): ");
    bw.write(data + "\n");
  }

  @Override
  public String toString(){
    return "GraphDb";
  }
}

/*
  This class is responsible for defining a database. Each database must
  have a storage method. The default storage method is GraphDB.
*/
public class Database{
  private StorageMethod storageMethod;
  private BufferedWriter bw;

  public Database(BufferedWriter bw) throws IOException{
    this.storageMethod = new GraphDb();
    this.bw = bw;
    bw.write("Creating a generic database.\n");
  }

  public void setStorageMethod(StorageMethod storageMethod) throws IOException{
    this.storageMethod = storageMethod;
    bw.write("Setting the storage method to: " + this.storageMethod + "\n");
  }

  public void storeData(Integer data, BufferedWriter bw) throws IOException{
    bw.write("Storing data.\n");
    this.storageMethod.store(data, bw);
  }
}
