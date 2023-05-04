import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B {
    public static void main(String[] args) {
        int ans = 0;
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        try {
            line = sc.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String strN = "";
        for (int i = 0; i < 100000 && !Character.isWhitespace(line.charAt(i)); i++) {
            strN += line.charAt(i);
        }

        int n = Integer.parseInt(strN);
        Stack_B stalker = new Stack_B(n);
        int ball;
        int k = 0;
        for (int i = strN.length() + 1; k < n; i += 2) {
            //System.err.println(stalker.toString());
            k++;
            ball = Character.digit(line.charAt(i), 10);
            if (i == line.length() - 1 && ball == stalker.look() && stalker.getLength() >= 2) {
                ans += stalker.getLength() + 1;
                break;
            }
            if (stalker.empty() || stalker.look() == ball ||
                    (stalker.look() != ball && stalker.getLength() <= 2) ||
                    (i == line.length() - 1 && ball != stalker.look() && stalker.getLength() <= 2)) {
                stalker.push(ball);
            } else {
                int length = stalker.getLength();
                for (int j = 0; j < length; j++) {
                    stalker.pop();
                    ans++;
                }

                if (stalker.look() == ball && i == line.length() - 1 && stalker.getLength() == 2) {
                    ans += 1 + stalker.getLength();
                    break;
                }

                if (stalker.empty() || stalker.look() == ball || stalker.look() != ball && stalker.getLength() < 3) {
                    stalker.push(ball);
                }
            }
        }

        System.out.println(ans);
    }
}

class Stack_B {

    private ArrayList<Integer> stack;
    int[] que;
    private int lastIndex;

    public Stack_B(int size) {
        this.stack = new ArrayList<Integer>();
        this.lastIndex = -1;
        this.que = new int[size];
    }

    public int look() {
        if (!this.empty()) {
            return stack.get(lastIndex);
        } else {
            return -1;
        }
    }

    public void push(int x) {

        if (!this.empty() && stack.get(lastIndex) == x) {
            que[lastIndex + 1] = que[lastIndex] + 1;
        } else {
            que[lastIndex + 1] = 1;
        }
        lastIndex++;
        stack.add(x);
    }

    public int pop() {
        int x = stack.get(lastIndex);
        stack.remove(lastIndex);
        lastIndex--;
        return x;
    }

    public int getLength() {
        if (!this.empty()) {
            return que[lastIndex];
        } else {
            return 0;
        }
    }

    public boolean empty() {
        return stack.isEmpty();
    }

    public String toString() {
        return stack.toString();
    }
}