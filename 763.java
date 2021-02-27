import java.util.*;


class Solution {
    public List<Integer> partitionLabels(String S) {
        char[] characters = S.toCharArray();

       // get character last position in array.
        HashMap<Character, Integer> lastOccurences = new HashMap<Character, Integer>();
        for(int i = 0; i < characters.length; ++i){
            lastOccurences.put(characters[i], i);
        }
        
        // define partition
        List<Integer> answer = new ArrayList<Integer>();
        int startPartitionIndex = 0;
        int endPartitionIndex = 0;
        for (int i = 0; i < characters.length; ++i){
            endPartitionIndex = Math.max(endPartitionIndex, lastOccurences.get(characters[i]));

            if (i == endPartitionIndex){
                answer.add((i + 1) - startPartitionIndex);
                startPartitionIndex = i + 1;
            } 
        }
        return answer;
    }
}