// Hash Table with linear probing open addressing 
// collision avoidance.
import java.util.Arrays;


public class HashTableOpenAddressingLinearProbing {
    int[] hashTable;
    int probingConstant;
    
    public HashTableOpenAddressingLinearProbing(int numOfRow, int probingConstant){
        this.hashTable = new int[numOfRow];
        this.probingConstant = probingConstant;
    }

    public int hashFunction(int value){
        return value % 10;
    }

    public void insert(int value){
        int hashKey = hashFunction(value);
        
        if (this.hashTable[hashKey] == 0){
            this.hashTable[hashKey] = value;
        } else {
            hashKey += this.probingConstant;
            while(this.hashTable[hashKey] != 0){
                hashKey += this.probingConstant;
                if (hashKey >= this.hashTable.length){
                    System.out.println("hash table is full.");
                    return;
                }
            }
            this.hashTable[hashKey] = value;
        }
    }

    public boolean search(int value){
        int hashKey = hashFunction(value);
        while(hashKey < this.hashTable.length - 1){
            if (this.hashTable[hashKey] == value){
                return true;
            }
            hashKey += this.probingConstant;
        }
        return false;
    }

    public static void main (String[] args){
        HashTableOpenAddressingLinearProbing hashTable = new HashTableOpenAddressingLinearProbing(3, 1);
        hashTable.insert(10);
        hashTable.insert(11);
        hashTable.insert(12);
        System.out.println("hash table: " + Arrays.toString(hashTable.hashTable));
        hashTable.insert(20);
        System.out.println(hashTable.search(11));
        System.out.println(hashTable.search(10));
    }
}
