package ALGO_LAB;

import java.util.Arrays;
import java.util.Scanner;

public class D {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Heap ourHip = new Heap(n * 2);
        int command;
        int x;
        for (int i = 0; i < n; i++) {
            command = scanner.nextInt();
            if (command == 0) {
                x = scanner.nextInt();
                ourHip.insert(x);
                //System.out.println(ourHip.heapToString());
            } else {
                System.out.println(ourHip.getElement(0));
                ourHip.extract();
            }
        }
    }
}

class Heap {

    int[] hip;

    public Heap(int size) {
        this.hip = new int[size];
    }

    int lastIndex = 0; //храним индекс последнего ненулевого элемента

    public void siftDown(int root) { // done
        int maxChild;
        boolean childrenExist_l = 2 * root + 1 < lastIndex;
        boolean childrenExist_r = 2 * root + 2 < lastIndex;

        if (childrenExist_l ^ childrenExist_r) {
            maxChild = 2 * root + 1;
        } else if (childrenExist_l) {
            if (hip[2 * root + 1] > hip[2 * root + 2]) {
                maxChild = 2 * root + 1;
            }
            else {
                maxChild = 2 * root + 2;
            }
        }
        else {
            maxChild = root;
        }

        if (hip[root] < hip[maxChild]) {
                swap(root, maxChild);
                siftDown(maxChild);
        }
    }

    public void siftUp(int traveler) { // done
        int parent;
        if (traveler == 0) {
            parent = 0;
        } else {
            parent = (int) (traveler - 1) / 2;
        }
        if (hip[traveler] > hip[parent]) {
            swap(parent, traveler);
            siftUp(parent);
        }
    }

    public void insert(int incomingNumber) { // done
        hip[lastIndex] = incomingNumber;
        siftUp(lastIndex);
        lastIndex++;
    }

    public void extract() { // done
        swap(0, lastIndex - 1);
        hip[lastIndex - 1] = 0;
        lastIndex--;
        siftDown(0);
    }

    public void swap(int a, int b) { //индексы и массив // done
        int c = hip[a];
        hip[a] = hip[b];
        hip[b] = c;
    }

    public String heapToString() {
        return Arrays.toString(hip);
    }

    public int getElement(int i) {
        return hip[i];
    }
}
