import java.util.ArrayList;
import java.util.Comparator;

public class Solution3 extends Thread {

    static ArrayList<Integer> list = new ArrayList<Integer>();
    Integer left;
    Integer right;

    // Initializing the left and right integer
    public Solution3(Integer left,Integer right) {
        this.left = left;
        this.right = right;
    }

    static void sort(ArrayList<Integer> list, Integer left, Integer right) throws InterruptedException {
        if (right <= left) {
            return;
        }
        Integer s = part(list, left, right);

        Solution3 sol1 = new Solution3(left,s-1);
        Solution3 sol2 = new Solution3(s + 1,right);

      /*  Two threads "sortThread1" and "sortThread2" created to perform parallel computing.
          join() is used to block the one thread until the other thread terminates. */
        Thread sortThread1 = new Thread(sol1);
        Thread sortThread2 = new Thread(sol2);
        sortThread1.start();
        sortThread2.start();
        sortThread1.join();
        sortThread2.join();
    }

    static Integer part(ArrayList<Integer> list, Integer left, Integer right) {
        assert (left < right);
        Integer i = left - 1, j = right;

        // Comparator object "comp" provided as lambda expression to compare integer objects int1 and int2
        Comparator<Integer> comp = (Integer int1, Integer int2)-> int1< int2 ? 1 :0;

        for (;;) {
            while (comp.compare(list.get(++i), list.get(right)) == 1) ;

            while (comp.compare(list.get(right), list.get(--j)) == 1) {
                if (j.equals(left)) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swap(list, i, j);
        }
        swap(list, i, right);
        return i;
    }


    static void swap(ArrayList<Integer> list, Integer i, Integer j) {
        Integer h = list.get(i);
        list.set(i, list.get(j));
        list.set(j, h);
    }

    public void run() {
        // System.out.println("Thread " + Thread.currentThread().getId()+ " is running");
        try {
            sort(list, left, right);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // We test the sorting procedure with a list of random integer objects

        Integer n = 100000;

        for (int i = 0; i < n; i++) {
            list.add((int) Math.ceil(Math.random() * n));
        }
        sort(list, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(list.get(i) + ", ");
            if (i > 0 && i % 20 == 0) {
                System.out.println();
            }
        }
    }
}
