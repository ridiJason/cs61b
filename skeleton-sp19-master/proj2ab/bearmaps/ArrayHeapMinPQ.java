package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{

    private ArrayList<PriorityNode> items;
    private HashMap<T,Integer> hashmap;


    private class PriorityNode implements Comparable<NaiveMinPQ.PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(NaiveMinPQ.PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((NaiveMinPQ.PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }

    public ArrayHeapMinPQ(){
        items = new ArrayList<PriorityNode>();
        hashmap = new HashMap<T,Integer>();
    }


    @Override
    public void add(T item, double priority) {
        if(contains(item)){
            throw new IllegalArgumentException("item already exits");
        }
        items.add(new PriorityNode(item,priority));
        hashmap.put(item, size() - 1);
        swim(size() - 1);
    }

    public void swim(int k){
        if (k > 0 && items.get(k).getPriority() < items.get(parent(k)).getPriority()){
            swap(k,parent(k));
            swim(parent(k));
        }
    }

    public void swap(int k1, int k2){
        PriorityNode temp = items.get(k1);
        items.set(k1,items.get(k2));
        items.set(k2,temp);
        hashmap.put(items.get(k1).item,k1);
        hashmap.put(items.get(k2).item,k2);
    }

    public int parent(int k){
        if (k == 0){
            return 0;
        }else {
            return k/2;
        }
    }

    public int leftchild(int k){
        return 2 * k + 1;
    }

    public int rightchild(int k){
        return 2 * k + 2;
    }


    @Override
    public boolean contains(T item) {
        return hashmap.containsKey(item);
    }

    @Override
    public T getSmallest() {
        return items.get(0).item;
    }

    @Override
    public T removeSmallest() {
        if (size() == 0){
            throw new IllegalCallerException("No Node can be removed");
        }
        PriorityNode removeOne = items.get(0);
        swap(0, size() - 1);
        items.remove(size() - 1);
        sink(0);
        return removeOne.getItem();
    }

    private void sink(int k) {
        int smallest = k;
        if (leftchild(k) <= size() - 1 && items.get(leftchild(k)).getPriority() < items.get(k).getPriority()) {
            smallest = leftchild(k);
        }
        if (rightchild(k) <= size() - 1 && items.get(rightchild(k)).getPriority() < items.get(smallest).getPriority()) {
            smallest = rightchild(k);
        }
        if (smallest != k) {
            swap(k, smallest);
            sink(smallest);
        }
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)){
            throw new IllegalArgumentException("No such item");
        }
        int index = hashmap.get(item);
        double oldPriority = items.get(index).getPriority();
        items.get(index).setPriority(priority);
        if (oldPriority < priority){
            sink(index);
        }else {
            swim(index);
        }
    }
}
