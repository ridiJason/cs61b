
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K,V> implements Map61B<K,V>{

    private static final int initialSize = 16;
    private static final double loadFactor = 0.75;

    private int aSizeThresh; // array size
    private int pSize; // number of K-V pairs
    private Bucket<K, V>[] bucket;

    private class Bucket<K,V>{
        private K key;
        private V value;
        private Bucket next;
        private int hashcode;


        public Bucket(K key, V value, Bucket next, int hashcode){
            this.key = key;
            this.value = value;
            this.next = next;
            this.hashcode = hashcode;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Bucket getNext() {
            return next;
        }

        public void setNext(Bucket next) {
            this.next = next;
        }

        public int getHashcode() {
            return hashcode;
        }

        public void setHashcode(int hashcode) {
            this.hashcode = hashcode;
        }
    }


    public MyHashMap(){
        bucket = new Bucket[initialSize];
        pSize = 0;
    }

    public MyHashMap(int initialSize){
        this.aSizeThresh = initialSize;
    }



    @Override
    public void clear() {

    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void put(K key, V value) {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
