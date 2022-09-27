import java.util.*;
import java.util.ArrayList;


public class New_Sort_Integer_Sequential {

    static void sort(ArrayList<Integer> list, Integer left, Integer right, Comparator<Integer> objC) {

        if (right <= left)
            return;

        Integer s = part(list, left, right, objC);

        sort(list, left, s - 1, objC);

        sort(list, s + 1, right, objC);

    }

    static Integer part(ArrayList<Integer> list, Integer left, Integer right, Comparator<Integer> c) {

        assert(left < right);

        Integer i = left - 1, j = right;

/*        System.out.println(c.compare(1,2));
        System.out.println(c.compare(3,2));


        System.out.println(c.compare(2,2));*/

        for(;;) {

            while (c.compare(list.get(++i), list.get(right))== -1)
                ;

            while (c.compare(list.get(right), list.get(--j))== -1)
                if (j.equals(left))
                    break;

            if (i >= j)
                break;

            swap(list, i, j);

        }

        swap(list, i, right);

        return i;

    }

  /*  static boolean compare(Integer x, Integer y) {

        return x < y;

    }*/

    static void swap(ArrayList<Integer> list, Integer i, Integer j){

        Integer h = list.get(i);

        list.set(i, list.get(j));

        list.set(j, h);

    }

    public static void main(String[] args) {

        // We test the sorting procedure with a list of random integer objects

        Integer n = 100000;

        ArrayList<Integer> list = new ArrayList<Integer>();

       /* Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {

                return i1.compareTo(i2);
            }
        };
*/
        Comparator<Integer> c = (Integer i21, Integer i22) -> i21.compareTo(i22);

        for (int i = 0; i < n; i++)
            list.add((int) Math.ceil(Math.random() * n));

        sort(list, 0, n-1, c);

        for (int i = 0; i < n; i++) {

            System.out.print(list.get(i) + ", ");

            if(i > 0 && i % 20 == 0)
                System.out.println();

        }

    }
}