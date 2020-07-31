import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

        private Node root;

        private class Node{
                private K key;
                private V value;
                private Node left,right;
                private int size;

                public Node(K key, V value,int size){
                        this.key = key;
                        this.value = value;
                        this.size = size;
                }
        }

        public BSTMap(){

        }


        @Override
        public void clear() {
                root = null;
        }

        @Override
        public boolean containsKey(K key) {
                if (key == null){
                        throw  new IllegalArgumentException();
                }
                return get(key) != null;
        }

        @Override
        public V get(K key) {
                return get(root, key);
        }


        public V get(Node x, K key){
                if (key == null){
                        return null;
                }
                int cmp = key.compareTo(x.key);
                if(cmp == 0){
                        return x.value;
                }else if (cmp < 0){
                        return get(x.left,key);
                }else {
                        return get(x.right,key);
                }
        }


        @Override
        public int size() {
                return size(root);
        }

        public int size(Node x){
                if (x == null){
                        return 0;
                }else {
                        return x.size;
                }
        }

        @Override
        public void put(K key, V value) {
                if (key ==null){
                        throw new IllegalArgumentException();
                }
                else {
                        root = put(root,key,value);
                }
        }

        public Node put(Node x,K key,V value){
                if (x == null){
                        return new Node(key,value,1);
                }
                int cmp = key.compareTo(x.key);
                if (cmp == 0){
                        x.value = value;
                }else if (cmp < 0){
                        x.left = put(x,key,value);
                }else {
                        x.right = put(x,key,value);
                }
                x.size = 1 + size(x.left) + size(x.right);
                return x;
        }


        @Override
        public Set<K> keySet() {
                return null;
        }

        @Override
        public V remove(K key) {
                return null;
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
