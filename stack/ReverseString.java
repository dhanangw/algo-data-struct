public class ReverseString {
    private ArrayCharStack stack = new ArrayCharStack(10);
    public static void main(String[] args){
        ReverseString reverse = new ReverseString();
        String input = "Indonesia";
        String output  = reverse.reverseString(input);
        System.out.println(input);
        System.out.println(output);
    }

    public String reverseString(String input){
        char[] charactersArray = input.toCharArray();
        int arrayLength = charactersArray.length;
        for (int i = 0; i < arrayLength; i++){
            stack.push(charactersArray[i]);
        }
        for (int i = 0; i < arrayLength; i++){
            charactersArray[i] = stack.pop();
        }
        String output = new String(charactersArray);
        return output;
    }
}