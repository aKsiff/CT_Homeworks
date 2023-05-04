import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class E {

    public static void main(String[] args) {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        Stack_E exp = new Stack_E();
        try {
            String line = sc.readLine();
            char operation;
            for (int i = 0; i < line.length(); i += 2) {
                operation = line.charAt(i);
                if (Character.isDigit(operation)) {
                    exp.push(Character.digit(operation, 10));
                }
                else {
                    int a = exp.pop();
                    int b = exp.pop();
                    switch (operation) {
                        case('+'):
                            exp.push(a + b);
                            break;
                        case('-'):
                            exp.push(b - a);
                            break;
                        case('*'):
                            exp.push(a * b);
                            break;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(exp.pop());
    }
}

class Stack_E {
    public Stack_E(int[] arr) { // без реализации минимума
        this.lastIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            stack.add(arr[i]);
            lastIndex++;
        }
    }

    private ArrayList<Integer> stack = new ArrayList<Integer>();

    private ArrayList<Integer> mins = new ArrayList<Integer>();
    private int lastIndex;
    private int lastMin;

    public Stack_E() {
        this.stack = stack;
        this.lastIndex = -1;
        this.lastMin = -1;
    }

    private int look() {
        return stack.get(lastIndex);
    }

    public void push(int x) {
        stack.add(x);
        lastIndex++;
        if (lastMin > -1) {
            if (x <= mins.get(lastMin)) {
                mins.add(x);
                lastMin++;
            }
        } else {
            mins.add(x);
            lastMin = 0;
        }
    }

    public int pop() {
        int x = stack.get(lastIndex);
        stack.remove(lastIndex);
        if (x == mins.get(lastMin)) {
            mins.remove(lastMin);
            lastMin--;
        }
        lastIndex--;
        return x;
    }

    public int getMin() {
        return mins.get(lastMin);
    }
}
