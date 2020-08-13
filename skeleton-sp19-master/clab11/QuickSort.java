import edu.princeton.cs.algs4.Queue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     *
     * @param q1  A Queue of items
     * @param q2  A Queue of items
     * @return    A Queue containing the items of 
     *            q1 followed by the items of q2.
     */


    private static <T extends Comparable> List<T> catenate(List<T> q1, List<T> q2) {
        List<T> catenated = new ArrayList<T>();
        for (T item : q1) {
            catenated.add(item);
        }
        for (T item: q2) {
            catenated.add(item);
        }
        return catenated;
    }

    /**
     * Returns a random item from the given queue.
     *
     * @param items  A Queue of items
     * @return       A random item from items
     */
    private static <T extends Comparable> T getRandomT(List<T> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        T pivot = null;
        // Walk through the queue to find the item at the given index.
        for (T item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted  A Queue of unsorted items
     * @param pivot     The item to pivot on
     * @param less      An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are less than the given pivot.
     * @param equal     An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are equal to the given pivot.
     * @param greater   An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are greater than the given pivot.
     */
    private static <T extends Comparable> void partition(
            List<T> unsorted, T pivot,
            List<T> less, List<T> equal, List<T> greater) {
        // Your code here!
        for (T item : unsorted){
            if ( item.compareTo(pivot) < 0 ){
                less.add(item);
            }else if (item.compareTo(pivot) > 0){
                greater.add(item);
            }else {
                equal.add(item);
            }
        }
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     *
     * @param items  A Queue of possibly unsorted items
     * @return       A Queue of sorted items
     */
    public static <T extends Comparable> List<T> quickSort(
            List<T> items) {
        // Your code here!

        if (items.size() <= 1){
            return items;
        }


        T randomT = getRandomT(items);
        List<T> less = new ArrayList<>();
        List<T> equal = new ArrayList<>();
        List<T> greater = new ArrayList<>();
        partition(items,randomT,less,equal,greater);

        less = quickSort(less);
        greater = quickSort(greater);

        List<T> result = catenate(less,equal);
        result = catenate(result, greater);


        return result;
    }
}
