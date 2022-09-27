import java.util.*;
import java.util.ArrayList;

public class Solution1 {

    static void sort(ArrayList<Integer> list, Integer left, Integer right, Comparator<Integer> compSort) {

        if (right <= left)
            return;

        Integer s = part(list, left, right, compSort);

        sort(list, left, s - 1, compSort);

        sort(list, s + 1, right, compSort);

    }

    static Integer part(ArrayList<Integer> list, Integer left, Integer right, Comparator<Integer> partComp) {

        assert(left < right);

        Integer i = left - 1, j = right;

        for(;;) {

            while (partComp.compare(list.get(++i), list.get(right))==1)
                ;

            while (partComp.compare(list.get(right), list.get(--j))==1)
                if (j.equals(left))
                    break;

            if (i >= j)
                break;

            swap(list, i, j);

        }

        swap(list, i, right);

        return i;

    }

    // Compare() method commented since the objects are being compared using Comparator object
    /*    static boolean compare(Integer x, Integer y) {

        return x < y;

    }*/

    static void swap(ArrayList<Integer> list, Integer i, Integer j) {

        Integer h = list.get(i);

        list.set(i, list.get(j));

        list.set(j, h);

    }

    public static void main(String[] args) {

        // We test the sorting procedure with a list of random integer objects

        Integer n = 100000;

        ArrayList<Integer> list = new ArrayList<Integer>();

       // Comparator object "comp" created to compare and return two integers int1 and int2
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer int1, Integer int2) {

                return int1.compareTo(int2);

            }
        };

        for (int i = 0; i < n; i++)
            list.add((int) Math.ceil(Math.random() * n));

        // Comparator object "comp" passed in the sort function
        sort(list, 0, n-1, comp);

        for (int i = 0; i < n; i++) {

            System.out.print(list.get(i) + ", ");

            if(i > 0 && i % 20 == 0)
                System.out.println();

        }

    }
}