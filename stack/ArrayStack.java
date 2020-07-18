import java.lang.ArrayIndexOutOfBoundsException;

public class ArrayStack {
    int top = -1;
    int stackSize;
    int[] stack;

    public ArrayStack(int size) {
        stackSize = size;
        stack = new int[stackSize];
    }

    public int top() {
        return stack[top];
    }

    public void push(int value) {
        try {
            top = top + 1;
            stack[top] = value;
        } catch (ArrayIndexOutOfBoundsException e) {
            int[] newStack = new int[stackSize * 2];
            for (int i = 0; i < top; i++) {
                newStack[i] = stack[i];
            }
            newStack[top] = value;
            stack = newStack;
        }
    }

    public int pop() {
        int result = stack[top];
        top = top - 1;
        return result;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void print() {
        for (int i = 0; i < top + 1; i++) {
            System.out.println(" " + stack[i]);
        }
    }

    public static void main(String[] args) {
        ArrayStack stackObj = new ArrayStack(10);
        stackObj.push(1);
        stackObj.push(2);
        stackObj.push(3);
        stackObj.pop();
        stackObj.push(3);
        stackObj.push(4);
        stackObj.push(5);
        stackObj.push(6);
        stackObj.push(7);
        stackObj.push(8);
        stackObj.push(9);
        stackObj.push(10);
        stackObj.push(11);
        stackObj.push(12);
        stackObj.push(13);
        stackObj.push(14);
        stackObj.pop();
        stackObj.print();
        System.out.println(stackObj.isEmpty());
    }

}