// Hash Table with Double hashing open addressing 
// collision avoidance.
import java.util.Arrays;

public class HashTableOpenAddressingDoubleHashing {
    int[] hashTable;
    int hashTableSize;
    int prime;

    public HashTableOpenAddressingDoubleHashing(int hashTableSize, int prime){
        this.hashTable = new int[hashTableSize];
        this.hashTableSize = hashTableSize;
        this.prime = prime;
    }

    public int hashFunction1(int value){
        return value % this.hashTableSize;
    }

    public int hashFunction2(int value){
        return (this.prime - (value % this.prime));    
    }

    public void insertToHashTable(int value){
        int key = hashFunction1(value);
        if (this.hashTable[key] == 0){
            this.hashTable[key] = value;
        } else {
            // collision occurs
            int key2 = hashFunction2(value);
            
            for (int i = 0; i < this.hashTableSize; i++){
                int index = (key + i * key2) % this.hashTableSize;

                if (this.hashTable[index] == 0){
                    this.hashTable[index] = value;
                    break;
                }
            }
        }
    }

    public boolean search(int value){
        int key1 = hashFunction1(value);
        int key2 = hashFunction2(value);

        for (int i = 0; i < this.hashTableSize; i++){
            if (key1 > this.hashTableSize - 1){
                break;
            }
            else if (this.hashTable[key1] == value){
                return true;
            } else {
                key1 = (key1 + i * key2) % this.hashTableSize;
            }
        }
        return false;
    }

    public void printHashTable(){
        System.out.println(Arrays.toString(this.hashTable));
    }

    public static void main (String[] args){
        HashTableOpenAddressingDoubleHashing hashTable = new HashTableOpenAddressingDoubleHashing(10, 3);
        hashTable.insertToHashTable(1);
        hashTable.insertToHashTable(20);
        hashTable.insertToHashTable(31);
        hashTable.printHashTable();
        hashTable.insertToHashTable(122);
        hashTable.printHashTable();
        System.out.println(hashTable.search(100));
    }
    
}
