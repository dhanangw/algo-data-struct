import java.lang.ArrayIndexOutOfBoundsException;

public class ArrayCharStack {
    int top = -1;
    int stackSize;
    char[] stack;

    public ArrayCharStack(int size) {
        stackSize = size;
        stack = new char[stackSize];
    }

    public char top() {
        return stack[top];
    }

    public void push(char value) {
        try {
            top = top + 1;
            stack[top] = value;
        } catch (ArrayIndexOutOfBoundsException e) {
            char[] newStack = new char[stackSize * 2];
            for (int i = 0; i < top; i++) {
                newStack[i] = stack[i];
            }
            newStack[top] = value;
            stack = newStack;
        }
    }

    public char pop() {
        char result = stack[top];
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
}