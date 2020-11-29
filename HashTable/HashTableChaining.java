// HashTable as array of LinkedList.
// use chaining to avoid collision.
import java.util.*;


public class HashTableChaining {
    ArrayList<ArrayList<Integer>> hashTable;
    
    public HashTableChaining(int numOfNodes){
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

    public boolean searchValue(int value){
        boolean isFound = false;
        int hashKey = hashFunction(value);
        ArrayList<Integer> row = this.hashTable.get(hashKey);
        for (int i = 0; i < row.size(); i++){
            if (row.get(i) == value){
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    public static void main (String[] args){
        HashTableChaining hashTable = new HashTableChaining(5);
        hashTable.insertToHashTable(110);
        hashTable.insertToHashTable(12);
        hashTable.insertToHashTable(1);
        hashTable.insertToHashTable(1200);
        hashTable.printHashTable();
        System.out.println(hashTable.searchValue(1));
        System.out.println(hashTable.searchValue(1200));
        System.out.println(hashTable.searchValue(100));
    }
    
}
