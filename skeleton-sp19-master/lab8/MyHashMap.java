
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K,V> implements Map61B<K,V>{

    private static final int initialSize = 16;
    private static final double loadFactor = 0.75;

    private int pSizeThresh; // array size
    private int pSize; // number of K-V pairs
    private Bucket<K, V>[] buckets;

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
        buckets = new Bucket[initialSize];
        pSize = 0;
        pSizeThresh = (int)loadFactor * initialSize;
    }

    public MyHashMap(int initialSize){
        buckets = new Bucket[initialSize];
        pSize = 0;
        pSizeThresh = (int)loadFactor * initialSize;
    }

    public MyHashMap(int initialSize, double loadFactor){
        buckets = new Bucket[initialSize];
        pSize = 0;
        pSizeThresh = (int)loadFactor * initialSize;
    }



    @Override
    public void clear() {
        buckets = new Bucket[initialSize];
        pSize = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }


    private int hash(K key, int length) {
        return (key.hashCode() & 0x7fffffff) % length;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        int hash = hash(key,buckets.length);
        Bucket<K,V> bucket = get(key,hash);
       return bucket.getValue();
    }

    public Bucket<K,V> get(K key,int hashcode){
        Bucket bucket = buckets[hashcode];
        while(bucket != null){
            if(bucket.getHashcode() == hashcode && bucket.getKey() == key){
                return bucket;
            }
            bucket = bucket.next;
        }
        return null;
    }

    @Override
    public int size() {
        return pSize;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (value == null) {
            remove(key);
            return;
        }
        int hash = hash(key,buckets.length);
        Bucket<K,V> bucket = buckets[hash];
        while (bucket != null){
            if(bucket.getHashcode() == hash && bucket.getKey() == key){
                throw new IllegalArgumentException("the key exists");
            }
            bucket = bucket.next;
        }
        put(key,value,hash);
    }


    public void put(K key, V value, int hashcode){
        Bucket bucket = new Bucket(key,value,buckets[hashcode],hashcode); //为找到此结点只能插入数组后的第一位
        buckets[hashcode] = bucket;
        pSize += 1;
        if(pSize > pSizeThresh){
            resize();
        }
        return;
    }

    public void resize(){
        int newArraySize = buckets.length * 2;
        Bucket<K,V>[] newbuckets = new Bucket[newArraySize];
        for (int i = 0; i < buckets.length; i++){
            Bucket<K,V> bucket = buckets[i];
            while (bucket != null){
                Bucket<K,V> oldnext = bucket.next;
                int newhash = hash(bucket.getKey(),newArraySize);
                bucket.setHashcode(newhash);
                bucket.setNext(newbuckets[newhash]);
                newbuckets[newhash] = bucket;
                bucket = oldnext;
            }
        }
        buckets = newbuckets;
        pSizeThresh = (int)loadFactor * newbuckets.length;
    }


    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for(int i = 0; i < buckets.length; i++){
            Bucket<K,V> bucket = buckets[i];
            while (bucket != null){
                set.add(bucket.key);
                bucket = bucket.next;
            }
        }
        return set;
    }


    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }



    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }
}
