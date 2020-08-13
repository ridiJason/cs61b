import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import org.junit.Test;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<Integer> queue1 = new Queue<>();
        queue1.enqueue(1);
        queue1.enqueue(3);
        queue1.enqueue(2);
        queue1.enqueue(7);
      //  System.out.println(isSorted(queue1));


        QuickSort.quickSort(queue1);
        System.out.println(isSorted(queue1));



        Queue<Integer> queue2 = new Queue<>();
        queue2.enqueue(1);
        queue2.enqueue(3);
        queue2.enqueue(4);
        queue2.enqueue(7);
  //      System.out.println(isSorted(queue2));


    }

    @Test
    public void testMergeSort() {
        Queue<Integer> queue1 = new Queue<>();
        queue1.enqueue(0);
        queue1.enqueue(2);
        queue1.enqueue(8);
        queue1.enqueue(9);



      //  System.out.println(isSorted(queue1));

        Queue<Integer> queue2 = new Queue<>();
        queue2.enqueue(8);
        queue2.enqueue(3);
        queue2.enqueue(1);
        queue2.enqueue(7);
        //System.out.println(isSorted(queue2));

        System.out.println(MergeSort.mergeSort(queue2));



    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
