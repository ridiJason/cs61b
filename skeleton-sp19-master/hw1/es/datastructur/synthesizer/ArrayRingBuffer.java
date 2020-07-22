package es.datastructur.synthesizer;
import java.lang.reflect.Array;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */


    public int capacity(){
        return rb.length;
    }

    public int fillCount(){
        return fillCount;
    }


    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if(isFull()){
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity();
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()){
            throw  new RuntimeException("Ring Buffer underflow");
        }
        T deOne = rb[first];
        first = (first + 1) % capacity();
        fillCount -= 1;
        return deOne;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if(isEmpty()){
            return null;
        }
        return rb[first];
    }
    public Iterator<T> iterator(){
        return new ArrayBufferIterator();
    }

    private class ArrayBufferIterator implements Iterator<T>{
        private int curIndex;
        private int count;

        public ArrayBufferIterator(){
            curIndex = first;
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < fillCount();
        }

        @Override
        public T next(){
            T returnItem = rb[curIndex];
            curIndex = (curIndex + 1) % capacity();
            count += 1;
            return returnItem;
        }
    }


    @Override
    public boolean equals(Object o){
        if(this.getClass() != o.getClass()){
            return false;
        }

        ArrayRingBuffer ob = (ArrayRingBuffer) o;

        Iterator<T> thisIterator = this.iterator();
        Iterator<T> obIterator = ob.iterator();


        if(ob.capacity() != this.capacity()){
            return false;
        }else if(ob.fillCount() != this.fillCount()){
            return false;
        }else{
            while(thisIterator.hasNext() && obIterator.hasNext()){
                if(thisIterator.next() != obIterator.next()){
                    return false;
                }
            }
            }
        return true;


    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    // TODO: Remove all comments that say TODO when you're done.
}