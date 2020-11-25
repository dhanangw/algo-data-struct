// HashTable as array of LinkedList.
// use chaining to avoid collision.
import java.util.*;


public class HashTable {
    ArrayList<ArrayList<Integer>> hashTable;
    
    public HashTable(int numOfNodes){
        this.hashTable = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < numOfNodes; i++){
            ArrayList<Integer> chain = new ArrayList<Integer>();
            this.hashTable.add(chain);
        }
    }
    
    public int hashFunction(int value){
        // a simple hash function.
        return value % 10;
    }

    public void insertToHashTable(int value){
        int index = hashFunction(value);
        ArrayList<Integer> chain = this.hashTable.get(index);
        chain.add(value);
    }

    public void printHashTable(){
        for (int i = 0; i < this.hashTable.size(); i++){
            ArrayList<Integer> currentChain = this.hashTable.get(i);
            System.out.println("index '" + i + "':" + currentChain.toString());
        }
    }

    public static void main (String[] args){
        HashTable hashTable = new HashTable(5);
        hashTable.insertToHashTable(110);
        hashTable.insertToHashTable(12);
        hashTable.insertToHashTable(1);
        hashTable.printHashTable();
    }
    
}
