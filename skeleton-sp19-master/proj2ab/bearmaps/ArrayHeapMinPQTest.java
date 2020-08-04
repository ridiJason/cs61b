package bearmaps;
import org.junit.Test;
import static org.junit.Assert.*;










public class ArrayHeapMinPQTest {

    @Test
    public void testAdd() {

        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();

        minHeap.add(1, 1);

        minHeap.add(2, 2);
        minHeap.add(3, 3);


        assertTrue(minHeap.size() == 3);

    }


    @Test
    public void testContains() {
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();

        minHeap.add(1, 1);
        minHeap.add(2, 2);
        minHeap.add(3, 3);

        assertTrue(minHeap.contains(2));
        assertFalse(minHeap.contains(0));
    }



    @Test

    public void testGetSmallest() {

        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();

        minHeap.add(1, 1);
        minHeap.add(2, 2);
        minHeap.add(3, 3);



        assertTrue(minHeap.getSmallest() == 1);

    }



    @Test

    public void testRemoveSmallest() {

        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();

        minHeap.add(1, 1);
        minHeap.add(2, 2);
        minHeap.add(3, 0);



        assertTrue(minHeap.removeSmallest() == 3);
        assertTrue(minHeap.getSmallest() == 1);

    }



    @Test

    public void testChangePriority() {

        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();

        minHeap.add(1, 1);
        minHeap.add(2, 2);
        minHeap.add(3, 3);


        minHeap.changePriority(1, 3);
        minHeap.changePriority(3, 0);


        assertTrue(minHeap.getSmallest() == 3);

    }



    public static void main(String[] args) {

    }

}