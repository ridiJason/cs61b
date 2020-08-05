import java.util.*;

public class MyTrieSet implements TrieSet61B{
    private static final int R = 256;

    private Node root;


    private static class Node{
        private char NodeChar;
        private boolean isKey;
        private Hashtable<Character ,Node> hashtable;

        public Node(char NodeChar, boolean isKey){
            this.NodeChar = NodeChar;
            this.isKey = isKey;
            hashtable = new Hashtable<>();
        }
    }

    public MyTrieSet(){
        root = new Node('\0',false);
    }

    @Override
    public void clear() {

        root = null;
    }

    @Override
    public boolean contains(String key) {
        if (key == null || root ==null) {
            throw new IllegalArgumentException("the key does not exist");
        }
        Node curNode = root;
        Node nextNode = null;

        for (int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            nextNode = curNode.hashtable.get(c);
            if (nextNode == null){
                return false;
            }
            curNode = nextNode;
        }
        return curNode.isKey;
    }




    @Override
    public void add(String key) {
        if (key == null){
            throw new IllegalArgumentException("the key can not add");
        }
        Node curNode = root;
        for (int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            if (!curNode.hashtable.containsKey(c)){
                curNode.hashtable.put(c,new Node(c,false));
            }
            curNode = curNode.hashtable.get(c);
        }
        curNode.isKey = true;
    }


    public List<String> Collect(){
        List<String> list = new ArrayList<>();

        for(Character character : root.hashtable.keySet()){
            colHelp("\0",list,root.hashtable.get(character));
        }
        return list;
    }

    public void colHelp(String s, List list , Node node){
        if (node.isKey){
            list.add(s + node.NodeChar);
        }
        for (Character character : node.hashtable.keySet()){
            colHelp(s + node.NodeChar, list, node.hashtable.get(character));
        }
    }


    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> list = new ArrayList<>();
        if (prefix == null){
            return null;
        }
        Node curNode = root;
        for (int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            curNode = curNode.hashtable.get(c);
        }
        if (curNode.isKey){
            list.add(prefix);
        }

        for(Character character : curNode.hashtable.keySet()) {
            if (curNode != null) {
                colHelp(prefix, list, curNode.hashtable.get(character));
            }
        }
        return list;
    }


    @Override
    public String longestPrefixOf(String key) {
        return null;
    }
}
