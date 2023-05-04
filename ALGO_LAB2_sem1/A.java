import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class A {
    public static void main(String[] args) {
        Stack stalker = new Stack();
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(sc.readLine());
            int operation;
            for (int i = 0; i < n; i++) {
                String line = sc.readLine();
                operation = Integer.parseInt(line.substring(0, 1));
                switch (operation) {
                    case (1):
                        stalker.push(Integer.parseInt(line.substring(2)));
                        break;
                    case (2):
                        stalker.pop();
                        break;
                    case (3):
                        System.out.println(stalker.getMin());
                        break;
                }
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Stack {
    public Stack(int[] arr) { // без реализации минимума
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

    public Stack() {
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
        }
        else {
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