
// Hash Table with linear probing open addressing 
// collision avoidance.
import java.util.Arrays;

public class HashTableOpenAddressingQuadraticProbing {
    int[] hashTable;

    public HashTableOpenAddressingQuadraticProbing(int numOfRow) {
        this.hashTable = new int[numOfRow];
    }

    public void insert(int value) {
        int hashKey = value % this.hashTable.length;

        if (this.hashTable[hashKey] == 0){
            this.hashTable[hashKey] = value;
        } else {
            for (int i = 0; i < this.hashTable.length; i++){
                hashKey = (hashKey + i * i) % this.hashTable.length;

                if (this.hashTable[hashKey] == 0){
                    this.hashTable[hashKey] = value;
                    return;
                }
            }
            System.out.println("hash table is full.");
        }
    }

    public boolean search(int value){
        int hashKey = value % this.hashTable.length;

        if (this.hashTable[hashKey] == value){
            return true;
        } else {
            for (int i = 0; i < this.hashTable.length; i++){
                hashKey = (hashKey + i * i) % this.hashTable.length;
                if (this.hashTable[hashKey] == value){
                    return true;
                }
            }
            return false;
        }
    }

    public void print(){
        for (int i = 0; i < this.hashTable.length; i++){
            System.out.println("index " + i + ": " + this.hashTable[i]);
        }
    }

    public static void main(String[] args) {
        HashTableOpenAddressingQuadraticProbing hashTable = new HashTableOpenAddressingQuadraticProbing(10);
        hashTable.insert(10);
        hashTable.insert(11);
        hashTable.insert(12);
        hashTable.insert(20);
        hashTable.print();
        System.out.println(hashTable.search(13));
        System.out.println(hashTable.search(12));
    }
}
