import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {
    public static void main(String[] args) {
        StringBuilder ans = new StringBuilder();
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(sc.readLine());
            Queue qu = new Queue(n);
            int operation;
            for (int i = 0; i < n; i++) {
                String line = sc.readLine();
                operation = Integer.parseInt(line.substring(0, 1));
                switch (operation) {
                    case (1):
                        qu.push(Integer.parseInt(line.substring(2)));
                        break;
                    case (2):
                        qu.popFront();
                        break;
                    case (3):
                        qu.popBack();
                        break;
                    case (4):
                        ans.append(qu.gelLength(Integer.parseInt(line.substring(2)))).append("\n");
                        break;
                    case (5):
                        ans.append(qu.getFirst()).append("\n");
                        break;
                }
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(ans.toString());
    }
}

class Queue{

    private int[] que;
    private int[] indexes;
    private int lastIndex;
    private int firstIndex;
    private int removed;
    Queue(int size) {
        this.que = new int[size];
        this.indexes = new int[100000 + 1];
        this.lastIndex = 0;
        this.firstIndex = 0;
    }

    public void push(int x) {
        que[lastIndex] = x;
        indexes[x] = lastIndex - firstIndex;
        lastIndex++;
    }

    public void popFront() {
        for (int i = firstIndex; i < lastIndex; i++) {
            indexes[que[i]]--;
        }
        firstIndex++;
    }

    public void popBack() {
        lastIndex--;
    }

    public int gelLength(int q) {
        return indexes[q];
    }

    public int getFirst() {
        return que[firstIndex];
    }
}
